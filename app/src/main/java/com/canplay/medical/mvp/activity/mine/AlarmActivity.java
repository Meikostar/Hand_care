package com.canplay.medical.mvp.activity.mine;


import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 闹铃
 */
public class AlarmActivity extends BaseActivity {

    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private int sex = 0;
    private String name = "";

    @Override
    public void initViews() {
        setContentView(R.layout.alarm_pop);
        ButterKnife.bind(this);

    }

    @Override
    public void bindEvents() {
    }

    @Override
    public void initData() {

    }



}
