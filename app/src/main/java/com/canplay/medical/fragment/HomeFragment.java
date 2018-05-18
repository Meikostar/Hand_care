package com.canplay.medical.fragment;

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

import com.canplay.medical.R;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.mvp.activity.LocationBdActivity;
import com.canplay.medical.mvp.activity.home.MeasurePlanActivity;
import com.canplay.medical.mvp.activity.home.MessageActivity;
import com.canplay.medical.mvp.activity.home.UsePlanActivity;
import com.canplay.medical.mvp.activity.mine.MineEuipmentActivity;
import com.canplay.medical.mvp.activity.mine.MineHealthCenterActivity;
import com.canplay.medical.mvp.activity.mine.RemindHealthActivity;
import com.canplay.medical.mvp.adapter.HomeAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.banner.BannerView;

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


    public interface ScanListener {
        void scanListener();
    }

    public ScanListener listener;
    private HomeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        bannerView.requestFocus();
        bannerView.setFocusableInTouchMode(true);
        initView();
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

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
                }else if(SubscriptionBean.MESURE==bean.type){
                    presenter.getUserData(1);
                    presenter.getUserData(2);
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
                intent.putExtra("time",tvHour1.getText().toString()+":"+tvMinter1.getText().toString());
                startActivity(intent);
            }
        });
        llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsePlanActivity.class);
                intent.putExtra("time",tvHour.getText().toString()+":"+tvMinter.getText().toString());
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
        presenter.getUserData(1);
        presenter.getUserData(2);
        presenter.getMessageCout();


    }
   private CountDownTimer countDownTimer1;
   private CountDownTimer countDownTimer2;
    @Override
    public <T> void toEntity(T entity,int type) {
        BASE entitys = (BASE) entity;
        String time = entitys.nextTaskDueIn;
        String[] split = time.split(":");

            if (type==1) {
               int  hour=Integer.valueOf(split[0]);
                int  minter=Integer.valueOf(split[1]);

               long times = hour*3600*1000+minter*60*1000;
                if(countDownTimer1!=null){
                    countDownTimer1.cancel();
                }
                countDownTimer1 = new CountDownTimer(times, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        BaseApplication.time1=millisUntilFinished;
                        String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                        String[] times = timeStr.split(",");
                        if(tvHour!=null&&times!=null){
                            tvHour.setText(times[1]);
                            tvMinter.setText(times[2]);
                        }


                    }

                    @Override
                    public void onFinish() {
                        if(tvHour!=null){
                            tvHour.setText("00");
                            tvMinter.setText("00");
                        }



                    }
                }.start();
                tvHour.setText(split[0]);
                tvMinter.setText(split[1]);
                tvState.setText(entitys.isCompleted ? "已完成" : "未完成");
            } else if(type==2) {
                int  hour=Integer.valueOf(split[0]);
                int  minter=Integer.valueOf(split[1]);

                long times = hour*3600*1000+minter*60*1000;
                if(countDownTimer2!=null){
                    countDownTimer2.cancel();
                }
                countDownTimer2 = new CountDownTimer(times, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        BaseApplication.time2=millisUntilFinished;
                        String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                        String[] times = timeStr.split(",");

                        if(tvHour1!=null&&times!=null){
                            tvHour1.setText(times[1]);
                            tvMinter1.setText(times[2]);
                        }
                    }

                    @Override
                    public void onFinish() {
                        if(tvHour1!=null){
                            tvHour1.setText("00");
                            tvMinter1.setText("00");
                        }



                    }
                }.start();
                tvHour1.setText(split[0]);
                tvMinter1.setText(split[1]);
                tvState1.setText(entitys.isCompleted ? "已完成" : "未完成");
            }else if(type==3) {
                if(entitys.numberOfUnreadMessages==0){
                    tvCount.setVisibility(View.GONE);
                }else {
                    tvCount.setVisibility(View.VISIBLE);
                    tvCount.setText(""+entitys.numberOfUnreadMessages);
                }
            }else {
                tvHour1.setText(split[0]);
                tvMinter1.setText(split[1]);
            }


    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

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
