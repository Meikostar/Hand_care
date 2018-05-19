package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Medil;
import com.canplay.medical.mvp.adapter.MesureAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.OtherContract;
import com.canplay.medical.mvp.present.OtherPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_NavigationBar;
import com.canplay.medical.view.scrollView.StickyScrollView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 测量计划
 */
public class MeasurePlanActivity extends BaseActivity implements OtherContract.View {
    @Inject
    OtherPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minter)
    TextView tvMinter;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_menu)
    ListView rlMenu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.scrollView)
    StickyScrollView scrollView;
    private MesureAdapter adapter;
    private String time;
    private CountDownTimer countDownTimer;
    private long times;
    private int hour;
    private int hours;
    private int minter;
    private int minters;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_mesure_plan);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        navigationBar.setNavigationBarListener(this);
        showProgress("加载中...");
        presenter.getDetails("Measurement");
        adapter = new MesureAdapter(this);
        rlMenu.setAdapter(adapter);
        time = getIntent().getStringExtra("time");
        line.setFocusable(true);
        tvTime.setFocusable(true);
        tvTime.setFocusableInTouchMode(true);
        tvTime.requestFocus(); scrollView.setFocusable(false);
        initPopView();

        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.MENU_REFASHS) {
                }else if(SubscriptionBean.BLOODORSUGAR==bean.type){
                    presenter.getDetails("Measurement");
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
    }


    @Override
    public void bindEvents() {


        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                popView_navigationBar.showPopView();
            }

            @Override
            public void navigationimg() {

            }
        });


    }

    private PopView_NavigationBar popView_navigationBar;

    private void initPopView() {

        popView_navigationBar = new PopView_NavigationBar(this, 1);
        popView_navigationBar.setName("血压测量记录", "血糖测量记录");
        popView_navigationBar.setView(line);

        popView_navigationBar.setClickListener(new PopView_NavigationBar.ItemCliskListeners() {
            @Override
            public void clickListener(int poition) {
                switch (poition) {
                    case 0://血压测量记录
                        startActivity(new Intent(MeasurePlanActivity.this, BloodMeasureRecordActivity.class));
                        break;
                    case 1://血糖测量记录
                        Intent intent = new Intent(MeasurePlanActivity.this, BloodMeasureRecordActivity.class);
                        intent.putExtra("type", 1);
                        startActivity(intent);
                        break;

                }
                popView_navigationBar.dismiss();
            }

        });
    }

    @Override
    public void initData() {

    }

    private Medil medil;

    @Override
    public <T> void toEntity(T entity, int type) {
        medil = (Medil) entity;
        dimessProgress();
        if (TextUtil.isNotEmpty(medil.nextPlan.when)) {
            time = TimeUtil.formatHour(TimeUtil.getStringToDate(medil.nextPlan.when));
            String date = TimeUtil.formatHour(System.currentTimeMillis());
            String[] split = date.split(":");
            String[] splits = time.split(":");
            hours = (-Integer.valueOf(split[0]) + Integer.valueOf(splits[0]));
            if (hours < 0) {
                hours = hours + 24;
            }
            minters = (-Integer.valueOf(split[1]) + Integer.valueOf(splits[1]));
            if (minters < 0) {

                minters = minters + 60;
            }

            tvTime.setText(time);
            times = hours * 3600 * 1000 + minters * 60 * 1000;
            if (BaseApplication.time2 == 0) {
                BaseApplication.time2 = times;
            }
            countDownTimer = new CountDownTimer(BaseApplication.time2 == 0 ? times : BaseApplication.time2, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                    String[] times = timeStr.split(",");
                    if (tvHour != null) {
                        tvHour.setText(times[1]);
                        tvMinter.setText(times[2]);
                        tvSecond.setText(times[3]);
                    }


                }

                @Override
                public void onFinish() {
                    if (tvHour != null) {
                        tvHour.setText("00");
                        tvMinter.setText("00");
                        tvSecond.setText("00");
                    }


                }
            }.start();

        }
        adapter.setData(medil.actions);
    }
    private Subscription mSubscription;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }
    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }


}
