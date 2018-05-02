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
import com.canplay.medical.bean.AlarmClock;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.mvp.activity.mine.RemindSettingActivity;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.mvp.component.AlarmClockDeleteEvent;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.AlarmClockOperate;
import com.canplay.medical.util.MyUtil;
import com.canplay.medical.view.NavigationBar;
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
 * 用药提醒
 */
public class RemindMedicatFragment extends BaseFragment implements HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.rl_menu)
    ListView rlMenu;
    Unbinder unbinder;
    private RemindMedicatAdapter adapter;
    private Subscription mSubscription;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remind_medical, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        presenter.MedicineRemindList();
        mAlarmClockList = new ArrayList<>();
        initView();

        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if(SubscriptionBean.MEDICALREFASH==bean.type){
                    AlarmClock alarm= (AlarmClock) bean.content;
                     addList(alarm);
                    presenter.MedicineRemindList();
                }else if(SubscriptionBean.ALARM==bean.type){

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


    private void initView() {
        adapter = new RemindMedicatAdapter(getActivity(), null, rlMenu);
        rlMenu.setAdapter(adapter);
        adapter.setListener(new RemindMedicatAdapter.selectItemListener() {
            @Override
            public void delete(Medicine medicine, int type, int poistion) {
                if(type==1){
                    startActivity(new Intent(getActivity(), RemindSettingActivity.class));
                }else if(type==2){
                   presenter.removeRemind(medicine.reminderId);
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
    public void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }

    }
    /**
     * 保存闹钟信息的list
     */
    private List<AlarmClock> mAlarmClockList;
    private void addList(AlarmClock ac) {
        mAlarmClockList.clear();

        int id = ac.getId();
        int count = 0;
        int position = 0;
        List<AlarmClock> list = AlarmClockOperate.getInstance().loadAlarmClocks();
        for (AlarmClock alarmClock : list) {
            mAlarmClockList.add(alarmClock);

            if (id == alarmClock.getId()) {
                position = count;
                if (alarmClock.isOnOff()) {
                    MyUtil.startAlarmClock(getActivity(), alarmClock);
                }
            }
            count++;
        }

        checkIsEmpty(list);


    }

    private void deleteList(int position) {
        mAlarmClockList.clear();

        List<AlarmClock> list = AlarmClockOperate.getInstance().loadAlarmClocks();
        for (AlarmClock alarmClock : list) {
            mAlarmClockList.add(alarmClock);
        }

        checkIsEmpty(list);

//        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
    }

    private void updateList() {
        mAlarmClockList.clear();

        List<AlarmClock> list = AlarmClockOperate.getInstance().loadAlarmClocks();
        for (AlarmClock alarmClock : list) {
            mAlarmClockList.add(alarmClock);

            // 当闹钟为开时刷新开启闹钟
            if (alarmClock.isOnOff()) {
                MyUtil.startAlarmClock(getActivity(), alarmClock);
            }
        }

        checkIsEmpty(list);


    }

    private void checkIsEmpty(List<AlarmClock> list) {
        if (list.size() != 0) {
            rlMenu.setVisibility(View.VISIBLE);
//            mEmptyView.setVisibility(View.GONE);
        } else {
            rlMenu.setVisibility(View.GONE);
//            mEmptyView.setVisibility(View.VISIBLE);


        }
    }

    private List<Medicine> data;

    @Override
    public <T> void toEntity(T entity,int type) {
        data = (List<Medicine>) entity;
        adapter.setData(data);
    }

    @Override
    public void toNextStep(int type) {
//        showToast("删除成功");
     presenter.MedicineRemindList();
    }

    @Override
    public void showTomast(String msg) {

    }
}
