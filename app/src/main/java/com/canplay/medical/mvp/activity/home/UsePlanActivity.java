package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseDailogManager;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Medil;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity;
import com.canplay.medical.mvp.adapter.OrderGridAdapter;
import com.canplay.medical.mvp.adapter.UsesPlanAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.OtherContract;
import com.canplay.medical.mvp.present.OtherPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.MarkaBaseDialog;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_NavigationBar;
import com.canplay.medical.view.RegularListView;
import com.canplay.medical.view.scrollView.StickyScrollView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 用药计划
 */
public class UsePlanActivity extends BaseActivity implements OtherContract.View {
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
    @BindView(R.id.rl_menu)
    RegularListView rlMenu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.stub_gird)
    ViewStub stubGird;
    @BindView(R.id.iv_state)
    ImageView ivState;
    @BindView(R.id.scrollView)
    StickyScrollView scrollView;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;

    @BindView(R.id.img_empty)
    ImageView imgEmpty;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_remind)
    TextView tvRemind;
    @BindView(R.id.ll_remind)
    LinearLayout llRemind;
    @BindView(R.id.ll_times)
    LinearLayout llTimes;
    private UsesPlanAdapter adapter;
    private String time;
    private CountDownTimer countDownTimer;
    private long times;
    private int hour;
    private int hours;
    private int minter;
    private int minters;
    private Subscription mSubscription;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_use_plan);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter = new UsesPlanAdapter(this);
        rlMenu.setAdapter(adapter);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        showProgress("加载中...");
        presenter.getDetails("Medicine");
        time = getIntent().getStringExtra("time");
        line.setFocusable(true);
        tvTime.setFocusable(true);
        tvTime.setFocusableInTouchMode(true);
        tvTime.requestFocus();
        scrollView.setFocusable(false);
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.MENU_REFASHS) {
                } else if (SubscriptionBean.BLOODORSUGAR == bean.type) {

                    presenter.getDetails("Medicine");
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
        initPopView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
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
        popView_navigationBar.setView(line);

        popView_navigationBar.setClickListener(new PopView_NavigationBar.ItemCliskListeners() {
            @Override
            public void clickListener(int poition) {
                switch (poition) {
                    case 0://我的用药提醒
                        startActivity(new Intent(UsePlanActivity.this, MinePlanRemindActivity.class));
                        break;
                    case 1://我的用药记录
                        startActivity(new Intent(UsePlanActivity.this, TakeMedicineActivity.class));
                        break;

                }
                popView_navigationBar.dismiss();
            }

        });
    }

    @Override
    public void initData() {

    }

    private OrderGridAdapter adapters;
    private GridView gridView;

    public void showGirdView(List<Medil> list) {//有图片是展示
        View view = stubGird.inflate();
        gridView = (GridView) view.findViewById(R.id.grid);
        adapters = new OrderGridAdapter(this);

    }

    private void iniGridView(final List<Medil> list) {

        int length = 85;  //定义一个长度
        int size = list.size();  //得到集合长度
        //获得屏幕分辨路
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;

        int gridviewWidth = (int) (size * (length + 10) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(2, 0, 0, 0);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(2); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
        adapters.setDatas(list);
        gridView.setAdapter(adapters);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private Medil medil;

    @Override
    public <T> void toEntity(T entity, int type) {
        medil = (Medil) entity;
        dimessProgress();
        tvTime.requestFocus();
        if (medil != null) {
            showGirdView(null);
            llBg.setVisibility(View.VISIBLE);
            tvState.setText("下次服药时间");
        } else {

            rlBg.setVisibility(View.VISIBLE);
            txtDesc.setText("暂无服药记录");

        }
        if (medil.nextPlan != null) {
            if (medil.nextPlan.status.equals("incomplete")) {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopwindow(medil.nextPlan.reminderTimeId);

                    }
                });
            } else {
                tvContent.setVisibility(View.GONE);
            }
            if (TextUtil.isNotEmpty(medil.nextPlan.code)) {
                if (medil.nextPlan.code.equals("早")) {
                    ivState.setImageResource(R.drawable.zs);
                } else if (medil.nextPlan.code.equals("中")) {
                    ivState.setImageResource(R.drawable.zz);
                } else if (medil.nextPlan.code.equals("晚")) {
                    ivState.setImageResource(R.drawable.w);
                }
                tvName.setText(medil.nextPlan.code);
            } else {
                rlBg.setVisibility(View.VISIBLE);
                txtDesc.setText("暂无服药记录");
            }
            iniGridView(medil.nextPlan.items);
            if (TextUtil.isNotEmpty(medil.nextPlan.when)) {
                time = TimeUtil.formatHour(TimeUtil.getStringToDate(medil.nextPlan.when));

                String date = TimeUtil.formatHour(System.currentTimeMillis());
                String[] split = date.split(":");
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
                tvTime.setText(TimeUtil.formatToNew(TimeUtil.getStringToDate(medil.nextPlan.when)));
                times = TimeUtil.getStringToDate(medil.nextPlan.when) - System.currentTimeMillis();
                llTimes.setVisibility(View.VISIBLE);
                llRemind.setVisibility(View.GONE);
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
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
                            llTimes.setVisibility(View.GONE);
                            llRemind.setVisibility(View.VISIBLE);
                            tvRemind.setText("已经到了"+splits[0]+":"+splits[1]+"分服药时间，请按医嘱服用药物并确认服药。");
                        }

                    }
                }.start();

            }
        }

        if (medil.plans != null) {
            datas.clear();
            for (Medil medils : medil.plans) {
                if (medils.status.equals("completed")) {
                    datas.add(medils);
                }
            }
            if (datas.size() == 0) {

                rlBg.setVisibility(View.VISIBLE);
                txtDesc.setText("暂无服药记录");
            } else {
                rlBg.setVisibility(View.GONE);
            }
            adapter.setData(datas);
            scrollView.fullScroll(ScrollView.FOCUS_UP);

        } else {
            rlBg.setVisibility(View.VISIBLE);
            txtDesc.setText("暂无服药记录");
        }
        tvTime.requestFocus();

    }

    private List<Medil> datas = new ArrayList<>();

    @Override
    public void toNextStep(int type) {
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE, ""));
        showToasts("确认成功...");
        finish();

    }

    @Override
    public void showTomast(String msg) {
        dimessProgress();
    }

    private View views=null;
    private TextView sure = null;
    private TextView cancel = null;
    private TextView title = null;
    private EditText reson = null;
    public void showPopwindow(final String id) {

        views = View.inflate(this, R.layout.add_euip, null);
        sure = (TextView) views.findViewById(R.id.txt_sure);
        cancel = (TextView) views.findViewById(R.id.txt_cancel);
        title = (TextView) views.findViewById(R.id.tv_title);
        reson = (EditText) views.findViewById(R.id.edit_reson);
        title.setText("确定服用"+time+"相关的药？");
        final MarkaBaseDialog dialog = BaseDailogManager.getInstance().getBuilder(this).setMessageView(views).create();
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

                showProgress("确认中...");
                presenter.confirmEat(id);

                dialog.dismiss();
            }
        });


    }

}
