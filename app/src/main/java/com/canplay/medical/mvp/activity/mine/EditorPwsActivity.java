package com.canplay.medical.mvp.activity.mine;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Pws;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.OtherContract;
import com.canplay.medical.mvp.present.OtherPresenter;
import com.canplay.medical.util.PwdCheckUtil;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
