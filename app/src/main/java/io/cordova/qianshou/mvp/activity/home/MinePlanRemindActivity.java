package io.cordova.qianshou.mvp.activity.home;

import android.support.v4.app.Fragment;
import android.view.View;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.fragment.RemindMedicatFragment;
import io.cordova.qianshou.mvp.adapter.FragmentViewPagerAdapter;
import io.cordova.qianshou.mvp.adapter.RemindMedicatAdapter;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.fragment.RemindMedicatFragment;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollViewPager;

import static io.cordova.qianshou.R.layout.activity_mine_remind;

/**
 * 我的用药提醒
 */
public class MinePlanRemindActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;

   private RemindMedicatFragment fragment;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;
    @Override
    public void initViews() {
        setContentView(activity_mine_remind);
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
