package com.canplay.medical.mvp.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseAllActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.EditorNameDialog;
import com.canplay.medical.view.PhotoPopupWindow;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 亲友详情
 */
public class FriendDetailActivity extends BaseAllActivity implements View.OnClickListener, HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_bind)
    TextView tvBind;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_blood_press)
    LinearLayout llBloodPress;
    @BindView(R.id.ll_blood_sugar)
    LinearLayout llBloodSugar;
    @BindView(R.id.ll_Medical_plan)
    LinearLayout llMedicalPlan;
    private String userId;
    private String status;
    private int type;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_friend_detail);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        userId=getIntent().getStringExtra("id");
        status=getIntent().getStringExtra("status");
        if(TextUtil.isNotEmpty(status)){
            if(status.equals("Waiting")){
                tvBind.setText("同意");
            }else if(status.equals("Pending")) {
                tvBind.setVisibility(View.INVISIBLE);
            }else if(status.equals("Active")) {
                tvBind.setText("解除绑定");
            }else {
                tvBind.setText("绑定");
            }
        }
        type=getIntent().getIntExtra("type",0);
        presenter.getFriendInfo(userId);
    }

    @Override
    public void bindEvents() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llBloodPress.setOnClickListener(this);
        llBloodSugar.setOnClickListener(this);
        llMedicalPlan.setOnClickListener(this);
        tvBind.setOnClickListener(this);


    }


    @Override
    public void initData() {

    }

    private boolean isFriend;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_blood_press://血压记录
                Intent intent = new Intent(FriendDetailActivity.this, BloodPressRecordActivity.class);
                intent.putExtra("id",userId);
                startActivity(intent);
                break;
            case R.id.ll_blood_sugar://血糖记录
                Intent intent1 = new Intent(FriendDetailActivity.this, BloodSugarRecordActivity.class);
                intent1.putExtra("id",userId);
                startActivity(intent1);
                break;
            case R.id.ll_Medical_plan://用药计划
                startActivity(new Intent(FriendDetailActivity.this,UseMedicalRecordActivity.class));
                break;
            case R.id.tv_bind://解除绑定
                if(friend==null){
                    return;
                }
                Add add = new Add();
                add.familyAndFriendsUserId=friend.userId;
                add.familyAndFriendsUserName=friend.displayName;
                add.userId= SpUtil.getInstance().getUserId();
                add.name=SpUtil.getInstance().getUser();
                if(type==0){
                    presenter.addFriend(add);
                }else {
                    String userId = SpUtil.getInstance().getUserId();
                    if(TextUtil.isNotEmpty(status)){
                        if(status.equals("Waiting")){
                            presenter.agree(friend.userId);
                        }else if(status.equals("Active")) {
                            presenter.disAgree(friend.userId);
                        }
                    }

                }

                break;


        }
    }

    private Friend friend;
    @Override
    public <T> void toEntity(T entity,int type) {
     friend= (Friend) entity;
        Glide.with(this).load(friend.avatar).asBitmap().placeholder(R.drawable.moren).into(ivAvatar);
        if(TextUtil.isNotEmpty(friend.displayName)){
            tvName.setText(friend.displayName);
        }if(TextUtil.isNotEmpty(friend.address)){
            tvAddress.setText(friend.address);
        }    if(TextUtil.isNotEmpty(friend.phone)){
            tvPhone.setText(friend.phone);
        }  if(TextUtil.isNotEmpty(friend.dob)){
            String[] split = friend.dob.split("//");
            String birth=split[0]+"."+split[1]+"."+split[2];
            tvBirth.setText(birth);
        }
        if(friend.gender.equals("male")){//男

        }else {

        }
    }

    @Override
    public void toNextStep(int type) {
       if(type==8){
           finish();
           showToasts("解除成功");
       }else if(type==6){
           showToasts("已发送添加好友请求");
           RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.CLOSE,""));
           finish();
       }else if(type==4){
           showToasts("已同意");
           RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.CLOSE,""));
           finish();
       }
    }

    @Override
    public void showTomast(String msg) {
     showToasts(msg);
    }
}
