package io.cordova.qianshou.mvp.activity.account;


import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.RecoveryPsw;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.LoginContract;
import io.cordova.qianshou.mvp.present.LoginPresenter;
import io.cordova.qianshou.util.PwdCheckUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.RecoveryPsw;
import io.cordova.qianshou.util.PwdCheckUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
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
    private String displayName;
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
        displayName = getIntent().getStringExtra("username");
        recoveryPsw=new RecoveryPsw();
        recoveryPsw.passwordResetToken=passwordResetToken;
        recoveryPsw.username=displayName;

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
                    showToasts("密码至少6位数且包含数字，小写字母");
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
        showToasts(msg);
    }
}
