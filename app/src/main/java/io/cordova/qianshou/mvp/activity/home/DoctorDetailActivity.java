package io.cordova.qianshou.mvp.activity.home;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseAllActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.CircleTransform;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseAllActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.CircleTransform;

/**
 * 医生详情
 */
public class DoctorDetailActivity extends BaseAllActivity implements HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.topview_right_layout)
    FrameLayout topviewRightLayout;
    private Friend data;
    private String id;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);
        data = (Friend) getIntent().getSerializableExtra("data");
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        if(data==null){

            id=getIntent().getStringExtra("id");
            presenter.getDoctorInfo(id);
        }

    }

    @Override
    public void bindEvents() {
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){
                    presenter.DelDoctor(data.familyDoctorId);
                }else {
                    presenter.AddDoctor(data.participant.id);
                }
            }
        });
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int type;
    @Override
    public void initData() {
        if (data != null) {

            if (TextUtil.isNotEmpty(data.participant.user.displayName)) {
                tvName.setText(data.participant.user.displayName);
            }
            if (TextUtil.isNotEmpty(data.participant.user.mobile)) {
                tvPhone.setText(data.participant.user.mobile);
            }
            if (TextUtil.isNotEmpty(data.participant.hospital)) {
                tvHospital.setText(data.participant.hospital);
            }
            if (TextUtil.isNotEmpty(data.participant.position)) {
                tvJob.setText(data.participant.position);
            }
            if (TextUtil.isNotEmpty(data.status)) {
                if (data.status.equals("Active")) {
                    tvAdd.setText("移除医生");
                    type=1;
                    tvAdd.setVisibility(View.INVISIBLE);
                } else if (data.status.equals("Pending")) {
                    tvAdd.setVisibility(View.INVISIBLE);
                } else if (data.status.equals("Waiting")) {
                    tvAdd.setVisibility(View.INVISIBLE);
                }else {
                    tvAdd.setVisibility(View.VISIBLE);
                }
            }
            tvDepartment.setText((data.participant.lineManager == null ? "" : data.participant.lineManager));
            if (TextUtil.isNotEmpty(data.participant.user.gender)) {
                if (data.participant.user.gender.equals("male")) {
                    tvSex.setText("男");
                } else if (data.participant.user.gender.equals("female")) {
                    tvSex.setText("女");
                } else {
                    tvSex.setText(data.participant.user.gender);
                }
            }
            Glide.with(this).load(BaseApplication.avatar + data.participant.user.avatar).asBitmap().transform(new CircleTransform(this)).placeholder(R.drawable.dingdantouxiang).into(ivImg);

        }
    }


    @Override
    public <T> void toEntity(T entity, int type) {
        Friend friend= (Friend) entity;
        data.participant=friend;
        if (TextUtil.isNotEmpty(data.participant.user.displayName)) {
            tvName.setText(data.participant.user.displayName);
        }
        if (TextUtil.isNotEmpty(data.participant.user.mobile)) {
            tvPhone.setText(data.participant.user.mobile);
        }
        if (TextUtil.isNotEmpty(data.participant.hospital)) {
            tvHospital.setText(data.participant.hospital);
        }
        if (TextUtil.isNotEmpty(data.participant.position)) {
            tvJob.setText(data.participant.position);
        }

        tvAdd.setVisibility(View.VISIBLE);

        tvDepartment.setText((data.participant.lineManager == null ? "" : data.participant.lineManager));
        if (TextUtil.isNotEmpty(data.participant.user.gender)) {
            if (data.participant.user.gender.equals("male")) {
                tvSex.setText("男");
            } else if (data.participant.user.gender.equals("female")) {
                tvSex.setText("女");
            } else {
                tvSex.setText(data.participant.user.gender);
            }
        }
        Glide.with(this).load(BaseApplication.avatar + data.participant.user.avatar).asBitmap().placeholder(R.drawable.dingdantouxiang).into(ivImg);

    }

    @Override
    public void toNextStep(int type) {
        if(type==2){
            showToasts("移除成功");
        }else {
            showToasts("添加成功");
        }
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.DOCTOR,""));
    }

    @Override
    public void showTomast(String msg) {

    }
}
