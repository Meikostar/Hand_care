package io.cordova.qianshou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseFragment;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Health;
import io.cordova.qianshou.mvp.activity.health.BloodChartRecordActivity;
import io.cordova.qianshou.mvp.activity.health.SugarChartRecordActivity;
import io.cordova.qianshou.mvp.activity.health.TakeMedicineActivity;
import io.cordova.qianshou.mvp.activity.health.TimeXRecordActivity;
import io.cordova.qianshou.mvp.adapter.HealthDataAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 健康数据
 */
public class HealthDataFragment extends BaseFragment implements View.OnClickListener, HomeContract.View {
    @Inject
    HomePresenter presenter;


    Unbinder unbinder;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_list)
    ListView rlList;
    @BindView(R.id.ll_bg)
    LinearLayout llbg;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;

    private HealthDataAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        loadingView.showPager(BaseLoadingPager.STATE_LOADING);
        presenter.getHealthData();
        initView();
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private Subscription mSubscription;

    private void initListener() {
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.MENU_REFASHS) {
                } else if (SubscriptionBean.BLOODORSUGAR == bean.type) {
                    loadingView.showPager(BaseLoadingPager.STATE_LOADING);
                    presenter.getHealthData();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
        llbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TimeXRecordActivity.class));
            }
        });

    }

    private void initView() {
        adapter = new HealthDataAdapter(getActivity());
        rlList.setAdapter(adapter);
        adapter.setClickListener(new HealthDataAdapter.ItemCliks() {
            @Override
            public void getItem(Health menu, int type) {
                if (type == 0) {
                    if (TextUtil.isNotEmpty(menu.high)) {
                        startActivity(new Intent(getActivity(), BloodChartRecordActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), SugarChartRecordActivity.class));
                    }

                } else if (type == 1) {
                    if(menu.bloodGlucoseLevels!=null){
                        startActivity(new Intent(getActivity(), SugarChartRecordActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(), TakeMedicineActivity.class));
                    }

                } else if (type == 2) {

                    startActivity(new Intent(getActivity(), TakeMedicineActivity.class));
                }


            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    private Health health;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

    private List<Health> list = new ArrayList<>();
    private Health healths = new Health();

    @Override
    public <T> void toEntity(T entity, int type) {
        health = (Health) entity;
        list.clear();
        if (health != null) {
            if (health.bloodPressure != null) {
                list.add(health.bloodPressure);
            }
            if (health.bloodGlucoseLevels != null) {

                healths.bloodGlucoseLevels = health.bloodGlucoseLevels;
                list.add(healths);
            }
            if (health.medicineRecord != null) {
                list.add(health.medicineRecord);
            }
        }
        if (list.size() == 0) {

            loadingView.showPager(LoadingPager.STATE_EMPTY);

        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
        adapter.setData(list);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
