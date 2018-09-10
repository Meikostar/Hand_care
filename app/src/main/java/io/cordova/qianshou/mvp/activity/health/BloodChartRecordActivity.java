package io.cordova.qianshou.mvp.activity.health;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.fragment.ChartFragment;
import io.cordova.qianshou.fragment.ChartPressFragment;
import io.cordova.qianshou.fragment.MeasureRemindFragment;
import io.cordova.qianshou.fragment.RemindMedicatFragment;
import io.cordova.qianshou.mvp.activity.home.AddBloodDataActivity;
import io.cordova.qianshou.mvp.activity.mine.RemindSettingActivity;
import io.cordova.qianshou.mvp.adapter.FragmentViewPagerAdapter;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollViewPager;
import io.cordova.qianshou.view.OnChangeListener;
import io.cordova.qianshou.view.TwoNevgBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.fragment.ChartPressFragment;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollViewPager;
import io.cordova.qianshou.view.OnChangeListener;
import io.cordova.qianshou.view.TwoNevgBar;

/**
 * 血压测量记录
 */
public class BloodChartRecordActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.ne_bar)
    TwoNevgBar neBar;
    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_blood_chart);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);


    }


    @Override
    public void bindEvents() {
         neBar.setOnChangeListener(new OnChangeListener() {
             @Override
             public void onChagne(int currentIndex) {
                 viewpagerMain.setCurrentItem(currentIndex);
             }
         });

        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                startActivity(new Intent(BloodChartRecordActivity.this, AddBloodDataActivity.class));
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
        viewpagerMain.setOffscreenPageLimit(2);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(0);

        viewpagerMain.setScanScroll(false);

    }

    private ChartPressFragment chartFragment;
    private BloodRecordFragment bloodRecordFragment;

    private void addFragment() {
        mFragments = new ArrayList<>();
        chartFragment = new ChartPressFragment();
        bloodRecordFragment = new BloodRecordFragment();
        mFragments.add(chartFragment);
        mFragments.add(bloodRecordFragment);

    }



}
