package io.cordova.qianshou.fragment;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseDailogManager;
import io.cordova.qianshou.base.BaseFragment;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.mvp.activity.home.MeasureActivity;
import io.cordova.qianshou.mvp.adapter.RemindMeasureAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.util.AlarmClockOperate;
import io.cordova.qianshou.util.MyUtil;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MarkaBaseDialog;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import com.google.gson.Gson;

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
    @BindView(R.id.loadingView)
    LoadingPager loadingView;
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

        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//            initData();
            //相当于Fragment的onResume
            loadingView.showPager(BaseLoadingPager.STATE_LOADING);
            presenter.MeasureRemindList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Subscription mSubscription;
    private int type;

    private void initView() {

        adapter = new RemindMeasureAdapter(getActivity(), null, rlMenu);
        rlMenu.setAdapter(adapter);
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if (SubscriptionBean.MESURE == bean.type) {
                    String cont = (String) bean.content;
                    type = 3;
                    if (TextUtil.isNotEmpty(cont)) {
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
                time=medicine.when;
                if (type == 0) {
//                    showProgress("删除中...");
                    showPopwindow(medicine.reminderTimeId);
//                    presenter.removeRemind(medicine.reminderTimeId);
                } else  if (type == 1){
                    Intent intent = new Intent(getActivity(), MeasureActivity.class);
                    intent.putExtra("data", medicine);
                    startActivity(intent);
                } else  if (type == 2){
                    showPopwindow(medicine.reminderTimeId);
                }

            }
        });

    }

    private String name = "";
    private String time = "";
    private Mesure mesure = new Mesure();
    private List<String> times = new ArrayList<>();


    /**
     * 保存闹钟信息的list
     */
    private List<AlarmClock> mAlarmClockList;

    private void addList(AlarmClock ac) {
        MyUtil.startAlarmClock(getActivity(), ac);

    }


    private View views=null;
    private TextView sure = null;
    private TextView cancel = null;
    private TextView title = null;
    private EditText reson = null;
    public void showPopwindow(final String id) {

        views = View.inflate(getActivity(), R.layout.add_euip, null);
        sure = (TextView) views.findViewById(R.id.txt_sure);
        cancel = (TextView) views.findViewById(R.id.txt_cancel);
        title = (TextView) views.findViewById(R.id.tv_title);
        reson = (EditText) views.findViewById(R.id.edit_reson);
        title.setText("你要删除此测量提醒?");
        final MarkaBaseDialog dialog = BaseDailogManager.getInstance().getBuilder(getActivity()).setMessageView(views).create();
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.removeRemind(id);
                showProgress("删除中...");
                dialog.dismiss();
            }
        });


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
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }

    }

    private List<Medicine> data;
    private AlarmClock arm;
    private Map<String, AlarmClock> map = new HashMap<>();
    private int i = 0;

    @Override
    public <T> void toEntity(T entity, int types) {
        dimessProgress();
        if (types == 6) {
            BASE base = (BASE) entity;
            if (base.isSucceeded) {
                data.remove(poition);
                adapter.setData(data);
                RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE, ""));
                List<AlarmClock> alarmClocks = SpUtil.getInstance().getAllAlarm();
                for (AlarmClock alarmClock : alarmClocks) {
                    if (TextUtil.isNotEmpty(time)) {
                        String[] split = time.split(":");
                        if (alarmClock.getHour() == Integer.valueOf(split[0]) && alarmClock.getMinute() == Integer.valueOf(split[1])) {
                            AlarmClockOperate.getInstance().deleteAlarmClock(alarmClock);

                            // 关闭闹钟
                            MyUtil.cancelAlarmClock(getActivity(),
                                    alarmClock.getId());
//                            // 关闭小睡
//                            MyUtil.cancelAlarmClock(getActivity(),
//                                    -alarmClock.getId());

                            NotificationManager notificationManager = (NotificationManager) getActivity()
                                    .getSystemService(Activity.NOTIFICATION_SERVICE);
                            // 取消下拉列表通知消息
                            notificationManager.cancel(alarmClock.getId());
                        }
                    }
                }

            } else {

            }
        } else {
            data = (List<Medicine>) entity;
            if (data.size() == 0) {
                 loadingView.setContent("暂无测量提醒");
                loadingView.showPager(LoadingPager.STATE_EMPTY);

            }else {
                loadingView.showPager(LoadingPager.STATE_SUCCEED);
            }
            adapter.setData(data);
            if (type != 3) {
                if (data.size() == 0) {

                    return;
                }
            } else {
                type = 0;
            }

            List<AlarmClock> alarmClocks = SpUtil.getInstance().getAllAlarm();

            map.clear();
            for (AlarmClock alarmClock : alarmClocks) {
                map.put(((alarmClock.getHour() < 10 ? "0" + alarmClock.getHour() : alarmClock.getHour()) + ":" + (alarmClock.getMinute() < 10 ? "0" + alarmClock.getMinute() : alarmClock.getMinute())), alarmClock);
            }

            for (Medicine medicine : data) {
                if (TextUtil.isNotEmpty(medicine.when)) {
                    if (map.size() == 0) {
                        String[] split = medicine.when.split(":");
                        arm = BaseApplication.getInstance().mAlarmClock;
                        arm.setHour(Integer.valueOf(split[0]));
                        arm.setMinute(Integer.valueOf(split[1]));

                        arm.setTag(1 + ":" + "###"+ ":" + (medicine.items.get(0).name != null ? (medicine.items.get(0).name.equals("血压") ? "血压" : "血糖") : "血压"));
                        int id = arm.getId();
                        arm.setId(id + 1);
                        Gson gson = new Gson();
                        String jsonStr = gson.toJson(arm); //将对象转换成Json
                        SpUtil.getInstance().putString(medicine.when, jsonStr);
                        String time = SpUtil.getInstance().getString("time");
                        if (TextUtil.isNotEmpty(time)) {
                            SpUtil.getInstance().putString("time", time + "," + medicine.when + "");
                        } else {
                            SpUtil.getInstance().putString("time", medicine.when + "");
                        }
                        map.put(medicine.when, arm);
                        addList(arm);
                    } else {
                        AlarmClock alarmClock = map.get(medicine.when);
                        if (alarmClock == null) {
                            String[] split = medicine.when.split(":");
                            arm = BaseApplication.getInstance().mAlarmClock;
                            arm.setTag(1 + ":" + "###" + ":" + (medicine.items.get(0).name != null ? (medicine.items.get(0).name.equals("血压") ? "血压" : "血糖") : "血压"));
                            arm.setHour(Integer.valueOf(split[0]));
                            arm.setMinute(Integer.valueOf(split[1]));
                            int id = arm.getId();
                            arm.setId(id + 1);
                            Gson gson = new Gson();
                            String jsonStr = gson.toJson(arm); //将对象转换成Json
                            SpUtil.getInstance().putString(medicine.when, jsonStr);
                            String time = SpUtil.getInstance().getString("time");
                            if (TextUtil.isNotEmpty(time)) {
                                SpUtil.getInstance().putString("time", time + "," + medicine.when + "");
                            } else {
                                SpUtil.getInstance().putString("time", medicine.when + "");
                            }
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
        data.remove(poition);
        adapter.setData(data);
        dimessProgress();
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE, ""));
        List<AlarmClock> alarmClocks = SpUtil.getInstance().getAllAlarm();
        for (AlarmClock alarmClock : alarmClocks) {
            if (TextUtil.isNotEmpty(time)) {
                String[] split = time.split(":");
                if (alarmClock.getHour() == Integer.valueOf(split[0]) && alarmClock.getMinute() == Integer.valueOf(split[1])) {
                    String times = SpUtil.getInstance().getString("time");
                    String data = "";
                    if (TextUtil.isNotEmpty(times)) {
                        String[] split1 = times.split(",");
                        for (int i = 0; i < split1.length; i++) {
                            if (!time.equals(split1[i])) {
                                if (TextUtil.isNotEmpty(data)) {
                                    data = data + "," + split1[i];
                                } else {
                                    data = split1[i];
                                }
                            }
                        }
                        SpUtil.getInstance().putString("time", data);
                    }
                    SpUtil.getInstance().putString(time, "");


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
    }


    @Override
    public void showTomast(String msg) {
        showToast(msg);
        dimessProgress();
    }
}
