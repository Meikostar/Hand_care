package com.canplay.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Message;
import com.canplay.medical.mvp.activity.health.BloodChartRecordActivity;
import com.canplay.medical.mvp.activity.health.SugarChartRecordActivity;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity;
import com.canplay.medical.mvp.activity.health.TimeXRecordActivity;
import com.canplay.medical.mvp.adapter.HealthDataAdapter;
import com.canplay.medical.view.NavigationBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 健康数据
 */
public class HealthDataFragment extends BaseFragment implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_list)
    ListView rlList;
    @BindView(R.id.ll_bg)
    LinearLayout llbg;
    private HealthDataAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, null);
        unbinder = ButterKnife.bind(this, view);

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
                 startActivity(new Intent(getActivity(),TimeXRecordActivity.class));
            }
        });

    }

    private void initView() {
        adapter = new HealthDataAdapter(getActivity());
        rlList.setAdapter(adapter);
        adapter.setClickListener(new HealthDataAdapter.ItemCliks() {
            @Override
            public void getItem(Message menu, int type) {
                if(type==0){
                    startActivity(new Intent(getActivity(),BloodChartRecordActivity.class));
                }else if(type==1){
                    startActivity(new Intent(getActivity(),SugarChartRecordActivity.class));
                }else if(type==2){

                    startActivity(new Intent(getActivity(),TakeMedicineActivity.class));
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
