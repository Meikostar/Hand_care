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
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 图表
 */
public class ChartFragment extends BaseFragment implements BaseContract.View {
    @Inject
    BasesPresenter presenter;
    Unbinder unbinder;

    @BindView(R.id.viewpager_main)
    NoScrollViewPager viewpagerMain;
    @BindView(R.id.tv_press)
    TextView tvPress;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.tv_cone)
    TextView tvCone;
    @BindView(R.id.tv_ctwo)
    TextView tvCtwo;
    @BindView(R.id.tv_cthree)
    TextView tvCthree;
    @BindView(R.id.tv_state2)
    TextView tvState2;
    @BindView(R.id.tv_state1)
    TextView tvState1;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private LineCharFragment fragment1;
    private LineCharFragment fragment2;
    private LineCharFragment fragment3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String userId;
    private Subscription mSubscription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_line, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        userId = SpUtil.getInstance().getUserId();
        presenter.getBloodList(1, 0 + "", 1 + "", userId);
        initView();

        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.BLOODORSUGAR) {
                    presenter.getBloodList(1, 0 + "", 1 + "", userId);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
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

    public void choose(int type) {
        if (type == 0) {
            tvCone.setBackground(getResources().getDrawable(R.drawable.choose_hui_rectangle));
            tvCone.setTextColor(getResources().getColor(R.color.white));
            tvCthree.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setBackground(null);
            tvCtwo.setTextColor(getResources().getColor(R.color.blues));
            tvCtwo.setBackground(null);

            viewpagerMain.setCurrentItem(0);
        } else if (type == 1) {
            tvCone.setBackground(null);
            tvCone.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setBackground(null);
            tvCtwo.setTextColor(getResources().getColor(R.color.white));

            tvCtwo.setBackground(getResources().getDrawable(R.drawable.blue_rectangle));

            viewpagerMain.setCurrentItem(1);
        } else if (type == 2) {
            tvCone.setBackground(null);
            tvCone.setTextColor(getResources().getColor(R.color.blues));
            tvCthree.setTextColor(getResources().getColor(R.color.white));
            tvCthree.setBackground(getResources().getDrawable(R.drawable.choose_huis_rectangle));

            tvCtwo.setTextColor(getResources().getColor(R.color.blues));
            tvCtwo.setBackground(null);
            viewpagerMain.setCurrentItem(2);
        }
    }

    private void addFragment() {
        mFragments = new ArrayList<>();
        fragment1 = new LineCharFragment(1);
        fragment2 = new LineCharFragment(2);
        fragment3 = new LineCharFragment(3);

        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private List<Record> lists;
    private float value;

    @Override
    public <T> void toEntity(T entity, int type) {
        lists = (List<Record>) entity;
        if (lists != null && lists.size() == 1 && TextUtil.isNotEmpty(lists.get(0).bgl)) {
            tvPress.setText(lists.get(0).bgl);
            value = Float.valueOf(lists.get(0).bgl);
        }
        if (value > 1.5 && value < 5.5) {
            tvState.setText("正常");
        } else {
            tvState.setText("异常");
            tvState.setTextColor(getResources().getColor(R.color.red_b));
        }
         tvTime.setText(TimeUtil.formatToNew(lists.get(0).timeStamp));

    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
