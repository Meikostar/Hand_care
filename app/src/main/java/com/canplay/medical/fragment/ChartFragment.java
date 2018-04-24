package com.canplay.medical.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 图表
 */
public class ChartFragment extends BaseFragment {



    Unbinder unbinder;
    @BindView(R.id.tv_press)
    TextView tvPress;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_cone)
    TextView tvCone;
    @BindView(R.id.tv_ctwo)
    TextView tvCtwo;
    @BindView(R.id.tv_cthree)
    TextView tvCthree;
    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;

    private LineCharFragment fragment1;
    private LineCharFragment fragment2;
    private LineCharFragment fragment3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_line, null);
        unbinder = ButterKnife.bind(this, view);

        initView();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private List<Fragment> mFragments;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private void initView() {
        addFragment();
        mainViewPagerAdapter = new FragmentViewPagerAdapter(getChildFragmentManager(), mFragments);
        viewpagerMain.setAdapter(mainViewPagerAdapter);
        viewpagerMain.setOffscreenPageLimit(2);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(0);
        viewpagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                choose(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tvCone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              choose(0);
            }
        });
        tvCtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose(1);
            }
        });
        tvCthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose(2);
            }
        });


    }

    public void choose(int type){
        if(type==0){
            tvCone.setBackground(getResources().getDrawable(R.drawable.choose_hui_rectangle));
            tvCone .setTextColor(getResources().getColor(R.color.white));
            tvCthree.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setBackground(null);
            tvCtwo.setTextColor(getResources().getColor(R.color.blues));
            tvCtwo.setBackground(null);

            viewpagerMain.setCurrentItem(0);
        }else if(type==1){
            tvCone.setBackground(null);
            tvCone.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setBackground(null);
            tvCtwo.setTextColor(getResources().getColor(R.color.white));

            tvCtwo.setBackground(getResources().getDrawable(R.drawable.blue_rectangle));

            viewpagerMain.setCurrentItem(1);
        }else if(type==2){
            tvCone.setBackground(null);
            tvCone.setTextColor(getResources().getColor(R.color.slow_black));
            tvCthree.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setBackground(getResources().getDrawable(R.drawable.choose_huis_rectangle));

            tvCtwo.setTextColor(getResources().getColor(R.color.blues));
            tvCtwo.setBackground(null);
            viewpagerMain.setCurrentItem(2);
        }
    }
    private void addFragment() {
        mFragments = new ArrayList<>();
        fragment1 = new LineCharFragment();
        fragment2 = new LineCharFragment();
        fragment3 = new LineCharFragment();

        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
