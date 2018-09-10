package io.cordova.qianshou.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseDailogManager;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.bean.unBind;
import io.cordova.qianshou.mvp.activity.home.SmartKitActivity;
import io.cordova.qianshou.mvp.adapter.EuipmentAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MarkaBaseDialog;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.PhotoPopupWindow;
import io.cordova.qianshou.view.RegularListView;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import com.google.zxing.client.android.activity.CaptureActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseDailogManager;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.bean.unBind;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MarkaBaseDialog;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.PhotoPopupWindow;
import io.cordova.qianshou.view.RegularListView;
import io.cordova.qianshou.view.loadingView.LoadingPager;

/**
 * 我的设备
 */
public class MineEuipmentActivity extends BaseActivity implements HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_menu)
    RegularListView rlMenu;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    private EuipmentAdapter adapter;
    private PhotoPopupWindow mWindowAddPhoto;
    private String titles;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_equipment);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        showProgress("加载中...");
        presenter.getSmartList();
        titles = getIntent().getStringExtra("name");
        if (TextUtil.isNotEmpty(titles)) {
            navigationBar.setNaviTitle(titles);
        }
        if(BaseApplication.isOwn){
            llBg.setVisibility(View.VISIBLE);

        }else {
            llBg.setVisibility(View.GONE);
        }
        llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MineEuipmentActivity.this, SmartKitActivity.class));
            }
        });
        user_id = SpUtil.getInstance().getUserId();
        navigationBar.setNavigationBarListener(this);
        adapter = new EuipmentAdapter(this);
        rlMenu.setAdapter(adapter);
        mWindowAddPhoto = new PhotoPopupWindow(this);
        mWindowAddPhoto.setCont("解除绑定", "取消");

        mWindowAddPhoto.setColor(R.color.red_pop, 0);
    }

    @Override
    public void bindEvents() {
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                PermissionGen.with(MineEuipmentActivity.this)//动态权限
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
        adapter.setClickListener(new EuipmentAdapter.ItemCliks() {
            @Override
            public void getItem(Euipt menu, int type) {
                euipt = menu;
                if (type == 1) {//点击事件
                    mWindowAddPhoto.showAsDropDown(line);
//                    startActivity(new Intent(MineEuipmentActivity.this, SmartEquitActivity.class));
                } else {//长按事件

                    mWindowAddPhoto.showAsDropDown(line);
                }
            }
        });

        mWindowAddPhoto.setSureListener(new PhotoPopupWindow.ClickListener() {
            @Override
            public void clickListener(int type) {
                if (type == 0) {//解除绑定
                    unbind.patientDeviceId = euipt.patientDeviceId;
                    unbind.userId = user_id;
                    presenter.UnbindDevice(unbind);
                    showProgress("正在解除绑定");
                } else {//取消

                }
            }
        });
    }

    private String user_id;
    private unBind unbind = new unBind();
    private Bind bind = new Bind();
    private Euipt euipt;
    private int REQUEST_CODE_SCAN = 6;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("scan_result");
                showPopwindow(content);
//                result.setText("扫描结果为：" + content);
            }
        }
    }

    private View views = null;
    private TextView sure = null;
    private TextView cancel = null;
    private TextView title = null;
    private EditText reson = null;

    public void showPopwindow(final String content) {

        views = View.inflate(this, R.layout.add_euip, null);
        sure = (TextView) views.findViewById(R.id.txt_sure);
        cancel = (TextView) views.findViewById(R.id.txt_cancel);
        title = (TextView) views.findViewById(R.id.tv_title);
        reson = (EditText) views.findViewById(R.id.edit_reson);
        title.setText("设备号：" + content + "添加到到设备吗?");
        final MarkaBaseDialog dialog = BaseDailogManager.getInstance().getBuilder(this).setMessageView(views).create();
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final EditText finalReson = reson;
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.serialNo = content;
                bind.userId = user_id;
                presenter.bindDevice(bind);

                dialog.dismiss();
            }
        });


    }

    @Override
    public void initData() {

    }

    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Intent intent = new Intent(MineEuipmentActivity.this, CaptureActivity.class);
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

    public String path;
    private List<Euipt> list;

    @Override
    public <T> void toEntity(T entity, int type) {
        dimessProgress();
        list = (List<Euipt>) entity;
        if (list.size() == 0) {

            loadingView.showPager(LoadingPager.STATE_EMPTY);
            loadingView.setContent("暂无设备");
        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
        adapter.setData(list);
    }

    @Override
    public void toNextStep(int type) {
        dimessProgress();
        if (type == 1) {
            showToasts("绑定成功");
            presenter.getSmartList();
            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.EUIP_REFASH, ""));
        } else if (type == 2) {
            showToasts("移除成功");
            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.EUIP_REFASH, ""));
            presenter.getSmartList();
        }
    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
        dimessProgress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
