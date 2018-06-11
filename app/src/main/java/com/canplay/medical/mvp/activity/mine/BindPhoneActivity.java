package com.canplay.medical.mvp.activity.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Phone;
import com.canplay.medical.mvp.activity.account.ChangePhoneActivity;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.OtherContract;
import com.canplay.medical.mvp.present.OtherPresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
