package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.img_empty)
    ImageView imgEmpty;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.tv_remind)
    TextView tvRemind;
    @BindView(R.id.ll_remind)
    LinearLayout llRemind;

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

        presenter.getDetails("Measurement");
        showProgress("加载中...");
        adapter = new MesureAdapter(this);
        rlMenu.setAdapter(adapter);
        time = getIntent().getStringExtra("time");
        line.setFocusable(true);
        tvTime.setFocusable(true);
        tvTime.setFocusableInTouchMode(true);
        tvTime.requestFocus();
        scrollView.setFocusable(false);
        initPopView();

        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.MENU_REFASHS) {
                } else if (SubscriptionBean.BLOODORSUGAR == bean.type) {

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


    @Override
    protected void onResume() {
        super.onResume();
        presenter.getDetails("Measurement");
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
        if (medil != null) {
            if (TextUtil.isNotEmpty(medil.nextPlan.when)) {
                tvState.setText("下次测量时间");
                time = TimeUtil.formatHour(TimeUtil.getStringToDate(medil.nextPlan.when));
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
                tvTime.setText(TimeUtil.formatToNew(TimeUtil.getStringToDate(medil.nextPlan.when)));
                times = TimeUtil.getStringToDate(medil.nextPlan.when) - System.currentTimeMillis();


                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                llTime.setVisibility(View.VISIBLE);
                llRemind.setVisibility(View.GONE);
                countDownTimer = new CountDownTimer(times, 1000) {
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
                        if (times <= 0) {
                            llTime.setVisibility(View.GONE);
                            llRemind.setVisibility(View.VISIBLE);
                            tvRemind.setText(splits[0]+":"+splits[1]+"分测量时间已经到了快去测量吧。");
                        }

                    }
                }.start();
                rlBg.setVisibility(View.GONE);
            } else {
                rlBg.setVisibility(View.VISIBLE);
                txtDesc.setText("暂无测量记录");
            }
            if (medil.actions.size() == 0) {

                rlBg.setVisibility(View.VISIBLE);
                txtDesc.setText("暂无测量记录");
            } else {
                rlBg.setVisibility(View.GONE);
            }
        } else {
            rlBg.setVisibility(View.VISIBLE);
            txtDesc.setText("暂无测量记录");
        }
        adapter.setData(medil.actions);
    }

    private Subscription mSubscription;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
        dimessProgress();
        showToasts(msg);
    }



}
