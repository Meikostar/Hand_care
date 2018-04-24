package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.mvp.adapter.MedicalDetailAdapter;
import com.canplay.medical.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 药品详情
 */
public class MedicalDetailActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.listview)
    ListView listview;
    private MedicalDetailAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_medical_detail);
        ButterKnife.bind(this);

        navigationBar.setNavigationBarListener(this);

        adapter = new MedicalDetailAdapter(this);
        listview.setAdapter(adapter);

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
