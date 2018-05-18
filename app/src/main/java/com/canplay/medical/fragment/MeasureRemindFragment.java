package com.canplay.medical.fragment;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
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
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.adapter.RemindMeasureAdapter;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.AlarmClockOperate;
import com.canplay.medical.util.MyUtil;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.RegularListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                  String cont= (String) bean.content;
                    if(TextUtil.isNotEmpty(cont)){
                        return;
                    }
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
                    time=medicine.when;
                    name=medicine.name;
                    mesure.when=times;
                    mesure.name=medicine.items.get(0).name;
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
    private String name="";
    private String time="";
    private Mesure mesure=new Mesure();
    private List<String> times=new ArrayList<>();


    /**
     * 保存闹钟信息的list
     */
    private List<AlarmClock> mAlarmClockList;
    private void addList(AlarmClock ac) {
        MyUtil.startAlarmClock(getActivity(), ac);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

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
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }

    }

    private List<Medicine> data;
    private AlarmClock arm;
    private Map<String ,AlarmClock> map=new HashMap<>();
    private int i=0;
    @Override
    public <T> void toEntity(T entity,int type) {
        if(type==6){
            BASE base= (BASE) entity;
            if(base.isSucceeded){
                data.remove(poition);
                adapter.setData(data);
                List<AlarmClock> alarmClocks = AlarmClockOperate.getInstance().loadAlarmClocks();
                for(AlarmClock alarmClock:alarmClocks){
                    if(TextUtil.isNotEmpty(time)){
                        String[] split = time.split(":");
                        if(alarmClock.getHour()==Integer.valueOf(split[0])&&alarmClock.getMinute()==Integer.valueOf(split[1])){
                            AlarmClockOperate.getInstance().deleteAlarmClock(alarmClock);

                            // 关闭闹钟
                            MyUtil.cancelAlarmClock(getActivity(),
                                    alarmClock.getId());
                            // 关闭小睡
                            MyUtil.cancelAlarmClock(getActivity(),
                                    -alarmClock.getId());

                            NotificationManager notificationManager = (NotificationManager) getActivity()
                                    .getSystemService(Activity.NOTIFICATION_SERVICE);
                            // 取消下拉列表通知消息
                            notificationManager.cancel(alarmClock.getId());
                        }
                    }
                }

            }else {
                if(i!=0){
                    return;
                }
                i++;
                times.clear();
                times.addAll(base.when);
                mesure.name=name;
                mesure.when=times;
                String userId = SpUtil.getInstance().getUserId();
                mesure.userId=userId;
                mesure.type="time";
                mesure.remindingFor="Measurement";
                presenter.addMesure(mesure);
            }
        }else {
            data= (List<Medicine>) entity;

            adapter.setData(data);
            List<AlarmClock> alarmClocks = AlarmClockOperate.getInstance().loadAlarmClocks();

            map.clear();
            for(AlarmClock alarmClock:alarmClocks){
                map.put((alarmClock.getHour()+":"+alarmClock.getMinute()),alarmClock);
            }

            for(Medicine medicine:data){
                if(TextUtil.isNotEmpty(medicine.when)){
                    if(map.size()==0){
                        String[] split = medicine.when.split(":");
                        arm=BaseApplication.getInstance().mAlarmClock;
                        arm.setHour(Integer.valueOf(split[0]));
                        arm.setMinute(Integer.valueOf(split[1]));

                        arm.setTag(1+":"+medicine.reminderTimeId+":"+medicine.items.get(0).name!=null?medicine.items.get(0).name:"血压");
                        AlarmClockOperate.getInstance().saveAlarmClock(arm);
                        addList(arm);
                    }else {
                        AlarmClock alarmClock = map.get(medicine.when);
                        if(alarmClock==null){
                            String[] split = medicine.when.split(":");
                            arm=BaseApplication.getInstance().mAlarmClock;
                            arm.setTag(1+":"+medicine.reminderTimeId+":"+medicine.items.get(0).name!=null?medicine.items.get(0).name:"血压");
                            arm.setHour(Integer.valueOf(split[0]));
                            arm.setMinute(Integer.valueOf(split[1]));
                            AlarmClockOperate.getInstance().saveAlarmClock(arm);
                            addList(arm);
                        }
                    }

                }
            }
        }

    }
    private int poition;
    @Override
    public void toNextStep(int type) {

    }


    @Override
    public void showTomast(String msg) {

    }
}
