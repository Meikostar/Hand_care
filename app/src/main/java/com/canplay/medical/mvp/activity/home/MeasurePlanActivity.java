package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.mvp.adapter.UsePlanAdapter;
import com.canplay.medical.mvp.adapter.UserRecordAdapter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.order;

/**
 * 测量计划
 */
public class MeasurePlanActivity extends BaseActivity {


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
    private UserRecordAdapter adapter;
     private String time;
    private CountDownTimer countDownTimer;
    private long times;
    private int  hour;
    private int  hours;
    private int minter;
    private int minters;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_mesure_plan);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter = new UserRecordAdapter(this);
        rlMenu.setAdapter(adapter);
        time=getIntent().getStringExtra("time");
        if(TextUtil.isNotEmpty(time)){
            String date = TimeUtil.formatHour(System.currentTimeMillis());
            String[] split = date.split(":");
            String[] splits = time.split(":");
            hours=(Integer.valueOf(split[0])+Integer.valueOf(splits[0]));

            if(Integer.valueOf(split[1])+Integer.valueOf(splits[1])>=60){
                minters=(Integer.valueOf(split[1])+Integer.valueOf(splits[1])-60);
                hours=hours+1;
            }else {
                minters=(Integer.valueOf(split[1])+Integer.valueOf(splits[1]));
            }

            hour=Integer.valueOf(splits[0]);
            minter=Integer.valueOf(splits[1]);
            tvTime.setText((hours>=24?(hours-24):hours)+":"+(minters<10?0+""+minters:minters));
            times = hour*3600*1000+minter*60*1000;

                countDownTimer = new CountDownTimer(times, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                        String[] times = timeStr.split(",");
                        if(tvHour!=null){
                            tvHour.setText(times[1]);
                            tvMinter.setText(times[2]);
                            tvSecond.setText(times[3]);
                        }


                    }

                    @Override
                    public void onFinish() {
                        if(tvHour!=null){
                            tvHour.setText("00");
                            tvMinter.setText("00");
                            tvSecond.setText("00");
                        }


                    }
                }.start();

        }
        initPopView();


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
        popView_navigationBar.setName("血压测量记录","血糖测量记录");
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
                        intent.putExtra("type",1) ;
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



}
