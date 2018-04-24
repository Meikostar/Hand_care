package com.canplay.medical.mvp.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.fragment.MeasureRemindFragment;
import com.canplay.medical.fragment.RemindMedicatFragment;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 健康提醒
 */
public class RemindHealthActivity extends BaseActivity {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;
    @BindView(R.id.tv_use)
    TextView tvUse;
    @BindView(R.id.tv_merure)
    TextView tvMerure;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.ivline)
    ImageView ivline;
    @BindView(R.id.ivlines)
    ImageView ivlines;
    private int type;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_remind_health);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        type = getIntent().getIntExtra("type", 0);
        if (type != 0) {
            tvAdd.setVisibility(View.VISIBLE);
        }

    }


    private int status;

    @Override
    public void bindEvents() {

        tvUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvUse.setTextColor(getResources().getColor(R.color.blue));
                tvMerure.setTextColor(getResources().getColor(R.color.slow_black));
                ivline.setVisibility(View.VISIBLE);
                ivlines.setVisibility(View.INVISIBLE);
                viewpagerMain.setCurrentItem(0);
                tvAdd.setText("添加用药提醒");
                status = 0;
            }
        });

        tvMerure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpagerMain.setCurrentItem(1);
                ivlines.setVisibility(View.VISIBLE);
                ivline.setVisibility(View.INVISIBLE);
                tvUse.setTextColor(getResources().getColor(R.color.slow_black));
                tvMerure.setTextColor(getResources().getColor(R.color.blue));
                tvAdd.setText("添加测量提醒");
                status = 1;
            }
        });
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                if (type == 0) {
                    startActivity(new Intent(RemindHealthActivity.this, RemindSettingActivity.class));
                } else {
                    if(status==0){
                        startActivity(new Intent(RemindHealthActivity.this, TakeMedicineActivity.class));
                    }else {
                        startActivity(new Intent(RemindHealthActivity.this, BloodPressRecordActivity.class));

                    }

                }

            }

            @Override
            public void navigationimg() {

            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == 0) {
                    startActivity(new Intent(RemindHealthActivity.this, RemindSettingActivity.class));
                } else {
                    startActivity(new Intent(RemindHealthActivity.this, MeasureActivity.class));
                }

            }
        });

    }


    @Override
    public void initData() {
        addFragment();
        mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewpagerMain.setAdapter(mainViewPagerAdapter);
        viewpagerMain.setOffscreenPageLimit(2);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(0);
        viewpagerMain.setScanScroll(false);
    }

    private RemindMedicatFragment remindMedicatFragment;
    private MeasureRemindFragment measureRemindFragment;

    private void addFragment() {
        mFragments = new ArrayList<>();
        remindMedicatFragment = new RemindMedicatFragment();
        measureRemindFragment = new MeasureRemindFragment();
        mFragments.add(remindMedicatFragment);
        mFragments.add(measureRemindFragment);

    }



}
