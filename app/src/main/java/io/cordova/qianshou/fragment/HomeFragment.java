package io.cordova.qianshou.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseFragment;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.mvp.activity.LocationBdActivity;
import io.cordova.qianshou.mvp.activity.home.MeasurePlanActivity;
import io.cordova.qianshou.mvp.activity.home.MessageActivity;
import io.cordova.qianshou.mvp.activity.home.UsePlanActivity;
import io.cordova.qianshou.mvp.activity.mine.MineEuipmentActivity;
import io.cordova.qianshou.mvp.activity.mine.MineHealthCenterActivity;
import io.cordova.qianshou.mvp.activity.mine.RemindHealthActivity;
import io.cordova.qianshou.mvp.adapter.BannerAdapter;
import io.cordova.qianshou.mvp.adapter.HomeAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.util.DensityUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by mykar on 17/4/10.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, HomeContract.View {
    @Inject
    HomePresenter presenter;
    Unbinder unbinder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_alarm)
    ImageView ivAlarm;
    @BindView(R.id.navigationbar_title)
    TextView navigationbarTitle;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.bannerView)
    BannerView bannerView;
    @BindView(R.id.ll_health)
    LinearLayout llHealth;
    @BindView(R.id.ll_care)
    LinearLayout llCare;
    @BindView(R.id.ll_equipment)
    LinearLayout llEquipment;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minter)
    TextView tvMinter;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.tv_hour1)
    TextView tvHour1;
    @BindView(R.id.tv_minter1)
    TextView tvMinter1;
    @BindView(R.id.tv_state1)
    TextView tvState1;
    @BindView(R.id.ll_bg1)
    LinearLayout llBg1;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.ll_m1)
    LinearLayout llM1;
    @BindView(R.id.ll_m2)
    LinearLayout llM2;
    @BindView(R.id.ll_m3)
    LinearLayout llM3;
    @BindView(R.id.ll_m4)
    LinearLayout llM4;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_min1)
    TextView tvMin1;
    @BindView(R.id.tv_remind)
    TextView tvRemind;
    @BindView(R.id.tv_remind2)
    TextView tvRemind2;


    public interface ScanListener {
        void scanListener();
    }

    public ScanListener listener;
    private HomeAdapter adapter;
    private List<Integer> list = new ArrayList<>();
    private int[] img = {R.drawable.bg1, R.drawable.bg2};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private BannerAdapter adapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        bannerView.requestFocus();
        bannerView.setFocusableInTouchMode(true);
        adapters = new BannerAdapter(getActivity());
        list.add(R.drawable.bg1);
        list.add(R.drawable.bg2);
        adapters.setData(list);
        bannerView.setAdapter(adapters);
        initView();
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(presenter!=null){
            presenter.getDetails(1);
            presenter.getDetails(2);
            presenter.getMessageCout();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ScanListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    private Subscription mSubscription;

    private void initListener() {
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.MENU_REFASHS) {
                } else if (SubscriptionBean.MESURE == bean.type) {
                    presenter.getDetails(1);
                    presenter.getDetails(2);
                    presenter.getMessageCout();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);


        llBg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeasurePlanActivity.class);
                intent.putExtra("time", tvHour1.getText().toString() + ":" + tvMinter1.getText().toString());
                startActivity(intent);
            }
        });
        llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsePlanActivity.class);
                intent.putExtra("time", tvHour.getText().toString() + ":" + tvMinter.getText().toString());
                startActivity(intent);
            }
        });
        llShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LocationBdActivity.class));
            }
        });
        llEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MineEuipmentActivity.class);
                intent.putExtra("name", "智能设备");
                startActivity(intent);
            }
        });
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.scanListener();
            }
        });
        ivAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });


        llHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RemindHealthActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);

            }
        });
        llCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MineHealthCenterActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initView() {
        showProgress("加载中...");



    }

    private CountDownTimer countDownTimer1;
    private CountDownTimer countDownTimer2;
    private CountDownTimer countDownTimer3;
    private String time;
    private long times;
    private int minters;
    private int hours;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            if(presenter!=null){
                presenter.getDetails(1);
                presenter.getDetails(2);
                presenter.getMessageCout();
            }

        }
    }

    @Override
    public <T> void toEntity(T entity, int type) {
        dimessProgress();

        if (countDownTimer3 != null) {
            countDownTimer3.cancel();
        }
        countDownTimer3 = new CountDownTimer(60*1000*30, 60*1000*30) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                presenter.getDetails(1);
                presenter.getDetails(2);
                presenter.getMessageCout();

            }
        }.start();
        if (type == 1) {
            Medil entitys = (Medil) entity;
            if (entitys == null || TextUtil.isEmpty(entitys.nextPlan.when)) {
                if (type == 1) {

                    llM1.setVisibility(View.VISIBLE);
                    llM2.setVisibility(View.GONE);
                    tvState.setVisibility(View.GONE);


                } else if (type == 2) {

                    llM3.setVisibility(View.VISIBLE);
                    llM4.setVisibility(View.GONE);
                    tvState1.setVisibility(View.GONE);


                }
                return;
            }
            time = TimeUtil.formatHour(TimeUtil.getStringToDate(entitys.nextPlan.when));
            String date = TimeUtil.formatHour(System.currentTimeMillis());
            final String[] split = date.split(":");
            final String[] splits = time.split(":");
            hours = (-Integer.valueOf(split[0]) + Integer.valueOf(splits[0]));

            minters = (-Integer.valueOf(split[1]) + Integer.valueOf(splits[1]));
            if (minters < 0) {
                minters = minters + 60;
                hours = hours - 1;
            }
            if (hours < 0) {
                hours = hours + 24;
            }
            if (hours == 0 && minters == 0) {
                llM1.setVisibility(View.GONE);
                llM2.setVisibility(View.VISIBLE);
                tvState.setVisibility(View.VISIBLE);
                hours = 24;
                minters = 0;
            } else {
                llM1.setVisibility(View.GONE);
                llM2.setVisibility(View.VISIBLE);
                tvState.setVisibility(View.VISIBLE);

            }
            tvRemind.setTextColor(getResources().getColor(R.color.color6));
            tvRemind.setText("暂无测量提醒");

            final long times = TimeUtil.getStringToDate(entitys.nextPlan.when) - System.currentTimeMillis();
            if (countDownTimer1 != null) {
                countDownTimer1.cancel();
            }
            countDownTimer1 = new CountDownTimer(times, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    BaseApplication.time1 = millisUntilFinished;
                    String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                    String[] times = timeStr.split(",");
                    if (tvHour != null && times != null) {
                        tvHour.setText(times[1]);
                        tvMinter.setText(Integer.valueOf(times[2]) == 0 ? "01" : times[2]);
                        if (Integer.valueOf(times[2]) == 0 && Integer.valueOf(times[3]) != 0) {
                            tvMin.setText("小于");
                            tvMin.setTextColor(getResources().getColor(R.color.color6));
                            tvHour.setVisibility(View.INVISIBLE);
                        } else {
                            tvMin.setText("小时");
                            tvMin.setTextColor(getResources().getColor(R.color.colorred));
                            tvHour.setVisibility(View.VISIBLE);
                        }
                    }


                }

                @Override
                public void onFinish() {
                    if (tvHour != null) {
                        tvHour.setText("00");
                        tvMinter.setText("00");
                    }
                    if (times <= 0) {
                        llM1.setVisibility(View.VISIBLE);
                        llM2.setVisibility(View.GONE);
                        tvState.setVisibility(View.GONE);

                        tvRemind.setTextColor(getResources().getColor(R.color.colorred));
                        tvRemind.setText(splits[0] + ":" + splits[1] + "分服药时间已经到了请确认服药");
                    }

                }
            }.start();
//            tvHour.setText(split[0]);
//            tvMinter.setText(split[1]);
            tvState.setText(entitys.status.equals("completed") ? "已完成" : "未完成");
        } else if (type == 2) {
            Medil entitys = (Medil) entity;
            if (entitys == null || TextUtil.isEmpty(entitys.nextPlan.when)) {
                if (type == 1) {

                    llM1.setVisibility(View.VISIBLE);
                    llM2.setVisibility(View.GONE);
                    tvState.setVisibility(View.GONE);


                } else if (type == 2) {

                    llM3.setVisibility(View.VISIBLE);
                    llM4.setVisibility(View.GONE);
                    tvState1.setVisibility(View.GONE);


                }
                return;
            }
            time = TimeUtil.formatHour(TimeUtil.getStringToDate(entitys.nextPlan.when));
            String date = TimeUtil.formatHour(System.currentTimeMillis());
            String[] split = date.split(":");
          final   String[] splits = time.split(":");
            hours = (-Integer.valueOf(split[0]) + Integer.valueOf(splits[0]));

            minters = (-Integer.valueOf(split[1]) + Integer.valueOf(splits[1]));
            if (minters < 0) {
                minters = minters + 60;
                hours = hours - 1;
            }
            if (hours < 0) {
                hours = hours + 24;
            }
            if (hours == 0 && minters == 0) {
                hours = 24;
                minters = 0;
                llM3.setVisibility(View.GONE);
                llM4.setVisibility(View.VISIBLE);
                tvState1.setVisibility(View.VISIBLE);

            } else {
                llM3.setVisibility(View.GONE);
                llM4.setVisibility(View.VISIBLE);
                tvState1.setVisibility(View.VISIBLE);

            }
            tvRemind2.setText("暂无测量提醒");
            tvRemind2.setTextColor(getResources().getColor(R.color.color6));
            final long times = TimeUtil.getStringToDate(entitys.nextPlan.when) - System.currentTimeMillis();
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
            countDownTimer2 = new CountDownTimer(times, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    BaseApplication.time2 = millisUntilFinished;
                    String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                    String[] times = timeStr.split(",");

                    if (tvHour1 != null && times != null) {
                        tvHour1.setText(times[1]);
                        tvMinter1.setText(Integer.valueOf(times[2]) == 0 ? "01" : times[2]);
                        if (Integer.valueOf(times[2]) == 0 && Integer.valueOf(times[3]) != 0) {
                            tvMin1.setText("小于");
                            tvMin1.setTextColor(getResources().getColor(R.color.color6));
                            tvHour1.setVisibility(View.INVISIBLE);
                        } else {
                            tvMin1.setText("小时");
                            tvMin1.setTextColor(getResources().getColor(R.color.colorred));
                            tvHour1.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onFinish() {
                    if (tvHour1 != null) {
                        tvHour1.setText("00");
                        tvMinter1.setText("00");
                    }

                    if (times <= 0) {
                        llM3.setVisibility(View.VISIBLE);
                        llM4.setVisibility(View.GONE);
                        tvState1.setVisibility(View.GONE);
                        tvRemind2.setTextColor(getResources().getColor(R.color.colorred));
                        tvRemind2.setText(splits[0] + ":" + splits[1] + "分测量时间已经到了快去测量吧");
                    }

                }
            }.start();
            tvHour1.setText(split[0]);
            tvMinter1.setText(split[1]);
            tvState1.setText(entitys.status.equals("completed") ? "已完成" : "未完成");
        } else if (type == 3) {
            BASE entitys = (BASE) entity;
            if (entitys.numberOfUnreadMessages == 0) {
                tvCount.setVisibility(View.GONE);
            } else {
                tvCount.setVisibility(View.VISIBLE);
                tvCount.setText("" + entitys.numberOfUnreadMessages);
            }
        } else {
//            tvHour1.setText(split[0]);
//            tvMinter1.setText(split[1]);
        }


    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
        showToast(msg);
        dimessProgress();
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
