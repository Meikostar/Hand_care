package io.cordova.qianshou.mvp.activity.mine;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.mvp.activity.account.ChangePhoneActivity;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.OtherContract;
import io.cordova.qianshou.mvp.present.OtherPresenter;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.mvp.activity.account.ChangePhoneActivity;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;

/**
 * 绑定手机号
 */
public class BindPhoneActivity extends BaseActivity implements OtherContract.View{
    @Inject
    OtherPresenter presenter;
    @BindView(R.id.navigationbar)
    NavigationBar navigationbar;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_change)
    TextView tvChange;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        navigationbar.setNavigationBarListener(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
       if(TextUtil.isNotEmpty(BaseApplication.phone)){
           tvPhone.setText(BaseApplication.phone);
       }
    }
   private Phone phone=new Phone();
    @Override
    public void bindEvents() {

        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.userId= SpUtil.getInstance().getUserId();
                phone.mobile=BaseApplication.phone;
                showProgress("加载中...");
                presenter.editorPhone(phone);

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public <T> void toEntity(T entity, int type) {
        dimessProgress();
        startActivity(new Intent(BindPhoneActivity.this,ChangePhoneActivity.class));
        finish();
    }

    @Override
    public void toNextStep(int type) {
     dimessProgress();
    }

    @Override
    public void showTomast(String msg) {
        dimessProgress();
    }
}
