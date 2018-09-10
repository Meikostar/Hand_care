package io.cordova.qianshou.mvp.activity.account;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.mvp.activity.MainActivity;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.LoginContract;
import io.cordova.qianshou.mvp.present.LoginPresenter;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionFail;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;

import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.StringUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionFail;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;


public class LoginActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.tv_logo)
    ImageView tvLogo;
    @BindView(R.id.et_user)
    ClearEditText etUser;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_registered)
    TextView tvRegistered;

   private int status;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        PermissionGen.with(this)
                .addRequestCode(PermissionConst.REQUECT_DATE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
        String userId = SpUtil.getInstance().getUserId();
        status=getIntent().getIntExtra("type",0);
        if (TextUtil.isNotEmpty(userId)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("type",status);
            startActivity(intent);
            finish();
        }
    }

    @PermissionSuccess(requestCode = PermissionConst.REQUECT_DATE)
    public void requestSdcardSuccess() {
        BaseApplication.getInstance().locationUtil.startLocation();
    }

    @PermissionFail(requestCode = PermissionConst.REQUECT_DATE)
    public void requestSdcardFailed() {
        showToasts("获取权限失败");
    }

    private int REQUEST_CODE_SCAN =6;
    @Override
    public void bindEvents() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPws.getText().toString();

                if (TextUtil.isEmpty(user)) {
                    showToasts(getString(R.string.qingshurusjh));
                    return;
                }
                if (TextUtil.isEmpty(password)) {
                    showToasts(getString(R.string.mimanull));
                    return;
                }
                showProgress("登录中...");
                presenter.goLogin(user, password);
            }
        });


        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//忘记密码
                Intent intent=new Intent(LoginActivity.this,ForgetFirstActivity.class);
                startActivity(intent);
            }
        });

        tvRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//注册
                Intent intent=new Intent(LoginActivity.this,RegisteredActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public <T> void toEntity(T entity,int type) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("type",88);
        startActivity(intent);
        finish();
        dimessProgress();
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
        dimessProgress();
    }


}
