package com.canplay.medical.mvp.activity.account;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.RecoveryPsw;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.LoginContract;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.util.PwdCheckUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * 忘记密码
 */

public class ForgetPswActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_psw)
    TextView tvPsw;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.et_npws)
    ClearEditText etNpws;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private Subscription mSubscription;
    private LinearLayoutManager mLinearLayoutManager;
    private int type;
    private String username;
    private String passwordResetToken;

    private boolean is_time;
    private RecoveryPsw recoveryPsw;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        type = getIntent().getIntExtra("type", 0);
        passwordResetToken = getIntent().getStringExtra("passwordResetToken");
        username = getIntent().getStringExtra("username");
        recoveryPsw=new RecoveryPsw();
        recoveryPsw.passwordResetToken=passwordResetToken;
        recoveryPsw.username=username;

    }

    private int count;
    private int a;
    private int b;
    private int c;
    private int d;

    @Override
    public void bindEvents() {

        etNpws.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (b == 0) {
                        ++count;
                        b = 1;
                    }
                } else {
                    --count;
                    b = 0;
                }
                if (count == 2) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });

        etPws.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (d == 0) {
                        ++count;
                        d = 1;
                    }
                } else {
                    --count;
                    d = 0;
                }
                if (count == 4) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!PwdCheckUtil.isContainAll(etPws.getText().toString())||etPws.getText().toString().length()<6){
                    showToasts("密码至少6位数且包含数字，大小写字母");
                    return;
                }
                if(!etPws.getText().toString().equals(etNpws.getText().toString())){
                    showToasts("两次密码输入不一致");
                    return;
                }
                recoveryPsw.newPassword=etPws.getText().toString();
                presenter.recoveryPsw(recoveryPsw);
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

    public void isSelect(boolean choose) {
        if (choose) {

            tvLogin.setBackground(getResources().getDrawable(R.drawable.login_selector));
        } else {
            tvLogin.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
        }

    }


    @Override
    public <T> void toEntity(T entity, int type) {

    }

    @Override
    public void toNextStep(int type) {
        showToasts("密码修改成功");
        finish();
    }

    @Override
    public void showTomast(String msg) {

    }
}
