package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.fragment.BloodRecordFragment;
import com.canplay.medical.fragment.ChartPressFragment;
import com.canplay.medical.mvp.activity.health.BloodChartRecordActivity;
import com.canplay.medical.mvp.adapter.BloodMeasureRecordAdapter;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;
import com.canplay.medical.view.OnChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 血压测量记录/血糖测量记录
 */
public class BloodMeasureRecordActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;

    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;

    private int type = 0;//0 血压测量记录 ,1 血糖测量记录
    private BloodMeasureRecordAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_blood_mesure);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 0);
        navigationBar.setNavigationBarListener(this);
        adapter = new BloodMeasureRecordAdapter(this);

        if (type != 0) {
            navigationBar.setNaviTitle("血糖测量记录");
        }

    }


    @Override
    public void bindEvents() {


        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                startActivity(new Intent(BloodMeasureRecordActivity.this, AddBloodDataActivity.class));
            }

            @Override
            public void navigationimg() {

            }
        });

    }


    @Override
    public void initData() {
        addFragment();
        mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewpagerMain.setAdapter(mainViewPagerAdapter);
        viewpagerMain.setOffscreenPageLimit(1);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(0);
        viewpagerMain.setScanScroll(false);

    }


    private BloodRecordFragment bloodRecordFragment;

    private void addFragment() {
        bloodRecordFragment = new BloodRecordFragment();
        bloodRecordFragment.setType(type);
        mFragments.add(bloodRecordFragment);

    }


}
