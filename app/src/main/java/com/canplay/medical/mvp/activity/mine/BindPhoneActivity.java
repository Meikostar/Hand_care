package com.canplay.medical.mvp.activity.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.mvp.activity.account.ChangePhoneActivity;
import com.canplay.medical.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 绑定手机号
 */
public class BindPhoneActivity extends BaseActivity {


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


    }

    @Override
    public void bindEvents() {
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(BindPhoneActivity.this,ChangePhoneActivity.class));
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


}
