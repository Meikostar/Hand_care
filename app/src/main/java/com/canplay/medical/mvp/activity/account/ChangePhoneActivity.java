package com.canplay.medical.mvp.activity.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Phone;
import com.canplay.medical.bean.Phones;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.LoginContract;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 更换手机号
 */

public class ChangePhoneActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    private TimeCount timeCount;
    private boolean is_time;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        //计时器
        timeCount = new TimeCount(60000, 1000);
        tvPhone.setText(BaseApplication.phone);
    }


    private int REQUEST_CODE_SCAN = 6;

    @Override
    public void bindEvents() {

        tvGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                if (TextUtil.isNotEmpty(phone) && phone.length() == 11) {
                    presenter.getCode(phone);

                }
            }
        });
        etPhone.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {

            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtil.isEmpty(etPhone.getText().toString())){
                    showToasts("请输入手机号");
                    return;
                }else if(etPhone.getText().toString().length()!=11){
                    etCode.setText("");
                }
                if(TextUtil.isEmpty(etCode.getText().toString())){
                    showToasts("请输入验证码");
                 return;
                }
                phones.jobId=jobId;
                phones.mobileNumber=etPhone.getText().toString();
                phones.verificationCode=etCode.getText().toString();
                presenter.changePhone(phones);
            }
        });

    }
    private Phones phones=new Phones();

    @Override
    public void initData() {

    }

    private String jobId="";

    @Override
    public <T> void toEntity(T entity, int type) {
        if(type==66){
            timeCount.start();
            tvGetcode.setBackground(getResources().getDrawable(R.drawable.send_hui_rectangle));
            BASE base = (BASE) entity;
            jobId = base.jobId;
        }else {
            BASE base = (BASE) entity;
            if(base.isSucceeded){
                showTomast("修改成功");
                BaseApplication.phone=etPhone.getText().toString();
                finish();
            }else {
                showToasts(base.message);
            }
        }

    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }


    //计时器
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvGetcode.setEnabled(false);
            tvGetcode.setText(millisUntilFinished / 1000 + getString(R.string.schongxinhuoqu));
        }

        @Override
        public void onFinish() {
            is_time = false;
            tvGetcode.setText(R.string.chongxinhuoqu);
            tvGetcode.setBackground(getResources().getDrawable(R.drawable.login_selector));
            tvGetcode.setEnabled(true);
        }
    }


}
