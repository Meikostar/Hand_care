package com.canplay.medical.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.mvp.adapter.recycle.HealthCenterAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.DivItemDecoration;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PhotoPopupWindow;
import com.google.zxing.client.android.activity.CaptureActivity;
import com.malinskiy.superrecyclerview.SuperRecyclerView;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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

         if(type==1){
             navigationBar.setNaviTitle("亲友添加");
             adapter = new HealthCenterAdapter(this, 1);
         }else {
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
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void bindEvents() {


        etSearch.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(TextUtil.isNotEmpty(s.toString())){
                    if(type==0){
                        presenter.searchDoctor(s.toString());
                    }else {
                        presenter.SearFriend(s.toString());
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
                if(sta==0){
                    if (TextUtil.isNotEmpty(etSearch.getText().toString())){

                        if(type==0){
                            presenter.searchDoctor(etSearch.getText().toString());
                        }else {
                            presenter.SearFriend(etSearch.getText().toString());
                        }
                    }
                    search.setText("取消");
                }else {
                    search.setText("搜索");
                    etSearch.setText("");
                    sta=0;

                }

            }
        });
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
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

                if(type==1&&poiston==1){
                    Add add = new Add();
                    add.familyAndFriendsUserId=data.userId;
                    add.familyAndFriendsUserName=data.displayName;
                    add.userId= SpUtil.getInstance().getUserId();
                    add.name=SpUtil.getInstance().getUser();
                    presenter.addFriend(add);
                }
            }
        });


    }


    private int REQUEST_CODE_SCAN = 6;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra("scan_result");


                if(type==0){
                   presenter.getDoctorInfo(content);
               }else {
                    String[] split = content.split("###");
                    if(split==null||split.length!=2){
                        return;
                    }
                    Intent intent = new Intent(AddFriendActivity.this, FriendDetailActivity.class);
                    intent.putExtra("id",split[1]);
                    intent.putExtra("status","add");
                    startActivity(intent);

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
    public <T> void toEntity(T entity,int type) {
        list= (List<Friend>) entity;
        sta=1;

        search.setText("取消");
        list= (List<Friend>) entity;
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void toNextStep(int type) {
        if(type==6){

        }else {
            adapter.setStatus(2);
            adapter.notifyDataSetChanged();
            showToasts("添加成功");
        }

    }

    @Override
    public void showTomast(String msg) {

    }


}
