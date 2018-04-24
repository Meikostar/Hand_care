package com.canplay.medical.mvp.activity.health;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.fragment.BloodRecordFragment;
import com.canplay.medical.fragment.ChartFragment;
import com.canplay.medical.mvp.activity.home.AddBloodDataActivity;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;
import com.canplay.medical.view.OnChangeListener;
import com.canplay.medical.view.TwoNevgBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 血糖测量记录
 */
public class SugarChartRecordActivity extends BaseActivity {


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
        setContentView(R.layout.activity_sugar_chart);
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
                Intent intent = new Intent(SugarChartRecordActivity.this, AddBloodDataActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
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

    private ChartFragment chartFragment;
    private BloodRecordFragment bloodRecordFragment;

    private void addFragment() {
        mFragments = new ArrayList<>();
        chartFragment = new ChartFragment();
        bloodRecordFragment = new BloodRecordFragment();
        bloodRecordFragment.setType(1);
        mFragments.add(chartFragment);
        mFragments.add(bloodRecordFragment);

    }



}
