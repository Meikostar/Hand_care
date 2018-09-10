package io.cordova.qianshou.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Add;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.mvp.activity.home.DoctorDetailActivity;
import io.cordova.qianshou.mvp.adapter.recycle.HealthCenterAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.DivItemDecoration;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.PhotoPopupWindow;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import com.google.zxing.client.android.activity.CaptureActivity;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Add;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.mvp.activity.home.DoctorDetailActivity;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.DivItemDecoration;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.PhotoPopupWindow;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 添加亲友/添加医生
 */
public class AddFriendActivity extends BaseActivity implements HomeContract.View {
    @Inject
    HomePresenter presenter;


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private HealthCenterAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    private PhotoPopupWindow mWindowAddPhoto;
    private int type;//0是添加医生 1,qingyou
    private Subscription mSubscription;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        type = getIntent().getIntExtra("type", 0);
        mLinearLayoutManager = new LinearLayoutManager(this);
        loadingView.showPager(8);
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.CLOSE) {
                    finish();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);

        if (type == 1) {
            navigationBar.setNaviTitle("亲友添加");
            adapter = new HealthCenterAdapter(this, 1);
        } else {
            etSearch.setHint("搜索医生手机号码");
            adapter = new HealthCenterAdapter(this, 0);
        }
        mSuperRecyclerView.setAdapter(adapter);
        adapter.setStatus(1);
        // mSuperRecyclerView.setRefreshing(false);

    }


    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Intent intent = new Intent(AddFriendActivity.this, CaptureActivity.class);
         /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
         * 也可以不传这个参数
         * 不传的话  默认都为默认不震动  其他都为true
         * */

        //ZxingConfig config = new ZxingConfig();
        //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
        //config.setPlayBeep(true);//是否播放提示音
        //config.setShake(true);//是否震动
        //config.setShowAlbum(true);//是否显示相册
        //config.setShowFlashLight(true);//是否显示闪光灯
        //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void bindEvents() {


        etSearch.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    String content = s.toString();
                    if(content.length()>=2){
                        if(isInteger(content.substring(0,2))){
                            if(content.length()>=3){
                                search();
                            }
                        }else {
                            search();
                        }
                    }else {
                        if(!isInteger(content)){
                            search();
                        }
                    }



                } else {
                    if (list != null) {
                        list.clear();
                    }
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etSearch.getText().toString();
                if (TextUtil.isNotEmpty(content)) {
                    state = 1;
                    if(content.length()>=2){
                        if(isInteger(content.substring(0,2))){
                            if(content.length()>=3){
                                search();
                            }else {
                                showToasts("搜索内容太少...");
                            }
                        }else {
                            search();
                        }
                    }else {
                        if(!isInteger(content)){
                                search();
                        }else {
                            showToasts("搜索内容太少...");
                        }
                    }


                } else {
                    showToasts("请输入搜索内容");
                }


            }
        });
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                PermissionGen.with(AddFriendActivity.this)//动态权限
                        .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                        .permissions(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
            }

            @Override
            public void navigationimg() {

            }
        });
        adapter.setClickListener(new HealthCenterAdapter.OnItemClickListener() {
            @Override
            public void clickListener(int poiston, Friend data) {
//                presenter.getDoctorInfo(id);

                if (type == 1 && poiston == 1) {
                    Add add = new Add();
                    add.familyAndFriendsUserId = data.userId;
                    add.familyAndFriendsUserName = data.displayName;
                    add.userId = SpUtil.getInstance().getUserId();
                    add.name = SpUtil.getInstance().getUser();
                    showProgress("添加中...");
                    presenter.addFriend(add);
                } else {
                    showProgress("添加中...");
                    presenter.AddDoctor(data.id);
                }
            }
        });


    }

    public void search(){
        if (type == 0) {
            presenter.searchDoctor(etSearch.getText().toString());
        } else {
            presenter.SearFriend(etSearch.getText().toString());
        }
        loadingView.showPager(BaseLoadingPager.STATE_LOADING);
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    private int REQUEST_CODE_SCAN = 6;
    private String id;
    private int state;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra("scan_result");

                state = 1;
                if (type == 0) {
                    id = content;
                    showProgress("搜索中...");
                    presenter.getDoctorInfo(id);

                } else {
                    String[] split = content.split("###");
                    if (split == null || split.length != 2) {
                        return;
                    }
                    showProgress("搜索中...");
                    presenter.getFriendInfo(split[1]);
                    id = split[1];


                }

//                result.setText("扫描结果为：" + content);
            }
        }
    }


    @Override
    public void initData() {

    }

    private int sta;
    private List<Friend> list;

    @Override
    public <T> void toEntity(T entity, int types) {
        dimessProgress();
        if (types == 23) {

            Friend friend = (Friend) entity;
            if (friend == null) {
                if (state == 1) {
                    state = 0;

                    loadingView.showPager(LoadingPager.STATE_EMPTY);
                    loadingView.setContent("没有找到相应用户");
                }

                return;
            }
            Intent intent = new Intent(this, DoctorDetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        } else if (types == 1) {
            Friend friend = (Friend) entity;
            if (friend == null) {
                if (state == 1) {
                    state = 0;

                    loadingView.showPager(LoadingPager.STATE_EMPTY);

                    loadingView.setContent("没有找到相应医生");
                }

                return;
            }
        } else {



            list = (List<Friend>) entity;
            adapter.setDatas(list);
            adapter.notifyDataSetChanged();

            if (list.size() == 0) {
                if (state == 1) {
                    state = 0;
                    if (list.size() == 0) {

                        loadingView.showPager(LoadingPager.STATE_EMPTY);
                        if (type == 0) {
                            loadingView.setContent("没有找到相应医生");

                        } else {
                            loadingView.setContent("没有找到相应用户");

                        }

                    } else {
                        loadingView.showPager(LoadingPager.STATE_SUCCEED);
                    }

                }else {
                    loadingView.showPager(LoadingPager.STATE_SUCCEED);
                }

            }else {
                loadingView.showPager(LoadingPager.STATE_SUCCEED);
            }

        }

    }

    @Override
    public void toNextStep(int type) {
        dimessProgress();

        if (type == 6) {
            dimessProgress();
            adapter.setStatus(2);
//            adapter.notifyDataSetChanged();
            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.CLOSE, ""));
            showToasts("添加成功");
            finish();
        } else if (type == 7) {
            dimessProgress();
            showToasts("添加成功");
            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.DOCTOR, ""));
            finish();
        } else {
            dimessProgress();
            adapter.setStatus(2);
            adapter.notifyDataSetChanged();
            showToasts("添加成功");
            finish();
        }

    }

    @Override
    public void showTomast(String msg) {

        dimessProgress();
    }



}
