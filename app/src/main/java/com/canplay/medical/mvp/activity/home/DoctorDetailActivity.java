package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseAllActivity;
import com.canplay.medical.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 医生详情
 */
public class DoctorDetailActivity extends BaseAllActivity {


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
    @Override
    public void initViews() {
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);


    }

    @Override
    public void bindEvents() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
