package io.cordova.qianshou.mvp.activity.mine;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.OtherContract;
import io.cordova.qianshou.mvp.present.OtherPresenter;
import io.cordova.qianshou.util.PwdCheckUtil;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.util.PwdCheckUtil;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.NavigationBar;

/**
 * 修改密码
 */
public class EditorPwsActivity extends BaseActivity implements OtherContract.View{
    @Inject
    OtherPresenter presenter;
    @BindView(R.id.navigationbar)
    NavigationBar navigationbar;
    @BindView(R.id.et_old_psw)
    ClearEditText etOldPsw;
    @BindView(R.id.et_psw)
    ClearEditText etPsw;
    @BindView(R.id.et_sure_psw)
    ClearEditText etSurePsw;
    @BindView(R.id.tv_save)
    TextView tvSave;


    @Override
    public void initViews() {
        setContentView(R.layout.activity_editor_psw);
        ButterKnife.bind(this);
        navigationbar.setNavigationBarListener(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);



    }
     private Pws pws=new Pws();
    @Override
    public void bindEvents() {
     tvSave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(TextUtil.isEmpty(etOldPsw.getText().toString())){
                 showToasts("请填写旧密码");
             }
             if(TextUtil.isEmpty(etPsw.getText().toString())){
                 showToasts("请填写新密码");
             }
             if(TextUtil.isEmpty(etSurePsw.getText().toString())){
                 showToasts("请填写确认密码");
             }
             if(!etSurePsw.getText().toString().equals(etPsw.getText().toString())){
                 showToasts("两次密码不一致");
             }
             if(!PwdCheckUtil.isContainAll(etPsw.getText().toString())){
                 showToasts("密码至少6位数且包含数字，大小写字母");
                 return;
             }
             pws.confirmPassword=etSurePsw.getText().toString();
             pws.newPassword=etPsw.getText().toString();
             pws.oldPassword=etOldPsw.getText().toString();
             presenter.editorPsd(pws);

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
        showToasts("修改成功");
        SpUtil.getInstance().clearData();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.FINISH,""));
        finish();
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
    }
}
