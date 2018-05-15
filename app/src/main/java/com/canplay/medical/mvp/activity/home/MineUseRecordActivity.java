package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.fragment.RemindMedicatFragment;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.mvp.adapter.UserRecordAdapter;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的用药记录
 */
public class MineUseRecordActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;

    private RemindMedicatFragment fragment;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;
    private UserRecordAdapter adapter;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_remind);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        mFragments=new ArrayList<>();
        addFragment();
        mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewpagerMain.setAdapter(mainViewPagerAdapter);
        viewpagerMain.setOffscreenPageLimit(1);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(0);
        viewpagerMain.setScanScroll(false);

    }

    private void addFragment() {
        fragment = new RemindMedicatFragment();

        mFragments.add(fragment);

    }

    @Override
    public void bindEvents() {


    }


    @Override
    public void initData() {

    }



}
