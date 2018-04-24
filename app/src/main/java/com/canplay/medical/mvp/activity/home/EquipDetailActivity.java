package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备详情
 */
public class EquipDetailActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_equip_detail);
        ButterKnife.bind(this);

        navigationBar.setNavigationBarListener(this);


    }

    @Override
    public void bindEvents() {

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
