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
import com.canplay.medical.bean.BASE;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.LoginContract;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.util.PwdCheckUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.gsonUtils;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.TimeSelectorDialog;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * 注册2
 */
public class RegisteredSecondActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.et_fist)
    ClearEditText etFist;
    @BindView(R.id.et_last)
    ClearEditText etLast;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.et_pwss)
    ClearEditText etPwss;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private String phone;
    private Subscription mSubscription;

    private LinearLayoutManager mLinearLayoutManager;
    private int type;
    private boolean is_time;
    private boolean is_right;

    private String jobId;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_registered2);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        phone = getIntent().getStringExtra("phone");
        //计时器
        tvSave.setEnabled(false);
    }

    @Override
    public void bindEvents() {

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!PwdCheckUtil.isContainAll(etPws.getText().toString())||etPws.getText().toString().length()<6){
                    showToasts("密码至少6位数且包含数字，大小写字母");
                    return;
                }
                if(!etPws.getText().toString().equals(etPwss.getText().toString())){
                    showToasts("两次密码输入不一致");
                    return;
                }
                presenter.register(etName.getText().toString(),etFist.getText().toString(),etLast.getText().toString(),etPws.getText().toString(),phone);

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etFist.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (s.toString().length() == 6) {
                        presenter.checkCode(jobId, s.toString());
                    }
                    if (a == 0) {
                        ++count;
                        a = 1;
                    }
                } else {
                    --count;
                    a = 0;
                }
                if (count == 5) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etName.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
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
                if (count == 5) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etLast.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (c == 0) {
                        ++count;
                        c = 1;
                    }
                } else {
                    --count;
                    c = 0;
                }
                if (count == 5) {
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
                if (count ==5) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etPwss.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (e == 0) {
                        ++count;
                        e = 1;
                    }
                } else {
                    --count;
                    e = 0;
                }
                if (count == 5) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
    }

    private int count;
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

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
    }

    @Override
    public void toNextStep(int type) {
        if (type == 0) {
        } else if (type == 1) {
            is_right = true;
        } else if (type == 2) {
            showToasts("注册成功");
            finish();
        }
    }

    @Override
    public void showTomast(String msg) {

    }




}
