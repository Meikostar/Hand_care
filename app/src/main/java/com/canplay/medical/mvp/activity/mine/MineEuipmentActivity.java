package com.canplay.medical.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Bind;
import com.canplay.medical.bean.Euip;
import com.canplay.medical.bean.Euipt;
import com.canplay.medical.bean.unBind;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.activity.home.SmartEquitActivity;
import com.canplay.medical.mvp.adapter.EuipmentAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PhotoPopupWindow;
import com.canplay.medical.view.RegularListView;
import com.google.zxing.client.android.activity.CaptureActivity;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private EuipmentAdapter adapter;
    private PhotoPopupWindow mWindowAddPhoto;
    private String title;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_equipment);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        presenter.getSmartList();
        title = getIntent().getStringExtra("name");
        if (TextUtil.isNotEmpty(title)) {
            navigationBar.setNaviTitle(title);
        }
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
                    startActivity(new Intent(MineEuipmentActivity.this, SmartEquitActivity.class));
                } else {//长按事件

                    mWindowAddPhoto.showAsDropDown(line);
                }
            }
        });

        mWindowAddPhoto.setSureListener(new PhotoPopupWindow.ClickListener() {
            @Override
            public void clickListener(int type) {
                if (type == 1) {//解除绑定
                    unbind.patientDeviceId = euipt.patientDeviceId;
                    unbind.userId = user_id;
                    presenter.UnbindDevice(unbind);
                    showProgress("正在绑定");
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
                bind.serialNo = content;
                bind.userId = user_id;
                presenter.bindDevice(bind);
//                result.setText("扫描结果为：" + content);
            }
        }
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
    public <T> void toEntity(T entity) {
        list = (List<Euipt>) entity;

        adapter.setData(list);
    }

    @Override
    public void toNextStep(int type) {
       if(type==1){
           showToasts("绑定成功");
           presenter.getSmartList();
       }else  if(type==2){
           showToasts("移除成功");
           presenter.getSmartList();
       }
    }

    @Override
    public void showTomast(String msg) {
       showToasts(msg);
        dimessProgress();
    }
}
