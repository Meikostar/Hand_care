package io.cordova.qianshou.mvp.activity.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.fragment.ChartPressFragment;
import io.cordova.qianshou.mvp.activity.health.BloodChartRecordActivity;
import io.cordova.qianshou.mvp.adapter.BloodMeasureRecordAdapter;
import io.cordova.qianshou.mvp.adapter.FragmentViewPagerAdapter;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollViewPager;
import io.cordova.qianshou.view.OnChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollViewPager;

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
        mFragments=new ArrayList<>();
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
                Intent intent = new Intent(BloodMeasureRecordActivity.this, AddBloodDataActivity.class);
                intent.putExtra("type",type);
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
