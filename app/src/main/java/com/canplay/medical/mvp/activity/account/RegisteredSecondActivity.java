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
    TextView etFist;
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
    private String name;
    private String date;
    private String psw;

    private  TimeSelectorDialog selectorDialog;
    private int potions=85;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_registered2);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        selectorDialog = new TimeSelectorDialog(RegisteredSecondActivity.this);
        selectorDialog.setDate(new Date(System.currentTimeMillis()))
                .setBindClickListener(new TimeSelectorDialog.BindClickListener() {
                    @Override
                    public void time(String time,int potion,String times) {
                        potions=potion;
                      if(TextUtil.isNotEmpty(time)){
                          etFist.setText(time);
                          if(TextUtil.isNotEmpty(psw)&&TextUtil.isNotEmpty(name)){
                              isSelect(true);
                          }else {
                              isSelect(false);
                          }
                      }else {
                          isSelect(false);
                      }
                    }
                });
        phone = getIntent().getStringExtra("phone");
        //计时器
        tvSave.setEnabled(false);
    }

    @Override
    public void bindEvents() {
        etFist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard();
                selectorDialog.show(findViewById(R.id.tv_save));
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!PwdCheckUtil.isContainAll(etPws.getText().toString())){
                    showToasts("密码至少6位数且包含数字，大小写字母");
                    return;
                }
                if(TextUtil.isEmpty(etFist.getText().toString())){
                    showToasts("请选择出生日期");
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
//
        etName.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(TextUtil.isNotEmpty(s.toString())){
                    name=s.toString();
                    if(TextUtil.isNotEmpty(psw)&&TextUtil.isNotEmpty(etFist.getText().toString())){
                        isSelect(true);
                    }else {
                        isSelect(false);
                    }
                }else {
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

                if(TextUtil.isNotEmpty(s.toString())){
                    psw=s.toString();
                    if(TextUtil.isNotEmpty(name)&&TextUtil.isNotEmpty(etFist.getText().toString())){
                        isSelect(true);
                    }else {
                        isSelect(false);
                    }
                }else {
                    isSelect(false);
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
       showToasts(msg);
    }




}
