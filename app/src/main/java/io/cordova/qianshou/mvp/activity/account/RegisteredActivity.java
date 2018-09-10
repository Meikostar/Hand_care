package io.cordova.qianshou.mvp.activity.account;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.LoginContract;
import io.cordova.qianshou.mvp.present.LoginPresenter;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;

public class RegisteredActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_save)
    TextView tvSave;


    private TimeCount timeCount;
    private String jobId;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        //计时器
        timeCount = new TimeCount(60000, 1000);
        tvSave.setEnabled(false);
    }

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
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkCode(jobId,etCode.getText().toString());

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etCode.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(TextUtil.isNotEmpty(s.toString())&&s.toString().length()==6){
                    isSelect(true);
                }else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etPhone.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(s.toString().length()<11){
                    etCode.setText("");
                }
            }

            @Override
            public void changeText(CharSequence s) {

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
                tvSave.setEnabled(true);
                tvSave.setBackground(getResources().getDrawable(R.drawable.login_selector));

        } else {
            tvSave.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
            tvSave.setEnabled(false);
        }

    }

    @Override
    public <T> void toEntity(T entity,int type) {
        timeCount.start();
        tvGetcode.setBackground(getResources().getDrawable(R.drawable.send_hui_rectangle));
        BASE base = (BASE) entity;
        jobId = base.jobId;
    }

    @Override
    public void toNextStep(int type) {
        if (type == 0) {
            etCode.setText("");
        } else if (type == 1) {

            Intent intent = new Intent(RegisteredActivity.this, RegisteredSecondActivity.class);
            intent.putExtra("phone",etPhone.getText().toString().trim());
            startActivity(intent);
            finish();
        } else if (type == 2) {
            showToasts("注册成功");
            finish();
        }
    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
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

            tvGetcode.setText(R.string.chongxinhuoqu);
            tvGetcode.setBackground(getResources().getDrawable(R.drawable.login_selector));
            tvGetcode.setEnabled(true);
        }
    }
}
