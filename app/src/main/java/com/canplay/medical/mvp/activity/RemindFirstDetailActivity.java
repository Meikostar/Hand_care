package com.canplay.medical.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Box;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.mvp.activity.home.MedicalDetailActivity;
import com.canplay.medical.mvp.adapter.ItemAdapter;
import com.canplay.medical.mvp.adapter.SmartCycAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提醒详情1
 */
public class RemindFirstDetailActivity extends BaseActivity implements BaseContract.View {

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_expend)
    ImageView ivExpend;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.rl_menu)
    RegularListView rlMenu;
    @BindView(R.id.tv_sure)
    TextView tvSure;

    private ItemAdapter adapter;
    private SmartCycAdapter adapters;
    private List<Medicines> list = new ArrayList<>();
    private Box boxs;
    private boolean isShow;
    private Animation animationhide;
    private Animation animationshow;

    @Override
    public void initViews() {
        setContentView(R.layout.remind_first_activity);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        presenter.myMedicineBox();
        navigationBar.setNavigationBarListener(this);
        adapter = new ItemAdapter(this);
        adapters = new SmartCycAdapter(this);
        rlMenu.setAdapter(adapter);
        animationhide = AnimationUtils.loadAnimation(this, R.anim.list_hide);
        animationshow = AnimationUtils.loadAnimation(this, R.anim.list_show);


    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                default:
                    super.handleMessage(msg);
            }
        }

    };

    @Override
    public void bindEvents() {
        ivExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    isShow = false;
                    rlMenu.startAnimation(animationhide);
                    rlMenu.setVisibility(View.GONE);
                } else {
                    isShow = true;
                    rlMenu.startAnimation(animationshow);
                    rlMenu.setVisibility(View.VISIBLE);
                }
            }
        });
        adapter.setClickListener(new ItemAdapter.ItemCliks() {
            @Override
            public void getItem(Box menu, int type) {
                Intent intent = new Intent(RemindFirstDetailActivity.this, MedicalDetailActivity.class);
                intent.putExtra("name", menu.medicine);
                startActivity(intent);
            }
        });
        adapters.setClickListener(new SmartCycAdapter.ItemCliks() {
            @Override
            public void getItem(Box box, int poistion) {
                datas = box.medicines;
                adapter.setData(box.medicines);
                tvType.setText("药物种类（" + box.medicines.size() + ")");
            }
        });
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {

            }

            @Override
            public void navigationimg() {

            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas != null) {
                    for (Box box : datas) {
                        Medicines medicines = new Medicines();
                        medicines.name = box.medicine;
                        list.add(medicines);
                    }
                    RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.CHOOSMEDICAL, list));
                    finish();
                }

            }
        });

    }


    @Override
    public void initData() {

    }

    private List<Box> data;
    private List<Box> datas;

    @Override
    public <T> void toEntity(T entity, int type) {
        Box box = (Box) entity;
        data = box.cups;
        tvTime.setText(TimeUtil.formatTims(box.planCreatedDateTime));
        int poistion = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).status == 1) {
                data.get(i).status = 3;
                poistion = i;
                break;
            }
        }
        datas = data.get(poistion).medicines;
        tvType.setText("药物种类（" + data.get(poistion).medicines.size() + ")");
        adapter.setData(data.get(poistion).medicines);
        adapters.setData(data);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
