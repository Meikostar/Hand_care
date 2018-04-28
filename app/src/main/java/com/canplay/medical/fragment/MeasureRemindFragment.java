package com.canplay.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.adapter.RemindMeasureAdapter;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.view.RegularListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 测量提醒
 */
public class MeasureRemindFragment extends BaseFragment implements HomeContract.View {

    @Inject
    HomePresenter presenter;

    @BindView(R.id.rl_menu)
    ListView rlMenu;
    Unbinder unbinder;
    private RemindMeasureAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remind_medical, null);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);

        presenter.attachView(this);

        presenter.MeasureRemindList();
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Subscription mSubscription;
    private void initView() {
        adapter = new RemindMeasureAdapter(getActivity(), null, rlMenu);
        rlMenu.setAdapter(adapter);
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if(SubscriptionBean.MESURE==bean.type){
                    presenter.MeasureRemindList();
                }


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
        adapter.setListener(new RemindMeasureAdapter.selectItemListener() {
            @Override
            public void delete(Medicine medicine, int type, int poistion) {
                if(type==0){
                    times.clear();
                    times.add(medicine.when);

                    mesure.when=times;
                    String userId = SpUtil.getInstance().getUserId();
                    mesure.userId=userId;
                    mesure.type="time";
                    mesure.remindingFor="Measurement";
                    presenter.addMesure(mesure);
                }else {
                    Intent intent = new Intent(getActivity(), MeasureActivity.class);
                    intent.putExtra("data",medicine);
                    startActivity(intent);
                }

            }
        });

    }
    private Mesure mesure=new Mesure();
    private List<String> times=new ArrayList<>();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }

    }

    private List<Medicine> data;

    @Override
    public <T> void toEntity(T entity) {
        data= (List<Medicine>) entity;

        adapter.setData(data);
    }
    private int poition;
    @Override
    public void toNextStep(int type) {
        data.remove(poition);
        adapter.setData(data);
    }

    @Override
    public void showTomast(String msg) {

    }
}
