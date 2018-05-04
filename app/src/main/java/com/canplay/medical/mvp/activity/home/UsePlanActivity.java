package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.BASEBEAN;
import com.canplay.medical.mvp.adapter.OrderGridAdapter;
import com.canplay.medical.mvp.adapter.UsePlanAdapter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_NavigationBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用药计划
 */
public class UsePlanActivity extends BaseActivity {


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
    ListView rlMenu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.stub_gird)
    ViewStub stubGird;
    private UsePlanAdapter adapter;
    private String time;
    private CountDownTimer countDownTimer;
    private long times;
    private int  hour;
    private int  hours;
    private int minter;
    private int minters;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_use_plan);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter = new UsePlanAdapter(this);
        rlMenu.setAdapter(adapter);
        time=getIntent().getStringExtra("time");
        if(TextUtil.isNotEmpty(time)){

            String date = TimeUtil.formatHour(System.currentTimeMillis());
            String[] split = date.split(":");
            String[] splits = time.split(":");
              hours=(Integer.valueOf(split[0])+Integer.valueOf(splits[0]));
               minters=(Integer.valueOf(split[1])+Integer.valueOf(splits[1]));
            if(Integer.valueOf(split[1])+Integer.valueOf(splits[1])>=60){
                minters=(Integer.valueOf(split[1])+Integer.valueOf(splits[1])-60);
                hours=hours+1;
            }else {
                minters=(Integer.valueOf(split[1])+Integer.valueOf(splits[1]));
            }
            tvTime.setText(hours+":"+(minters<10?0+""+minters:minters));
            hour=Integer.valueOf(splits[0]);
            minter=Integer.valueOf(splits[1]);
            times = hour*3600*1000+minter*60*1000;
            if (times>0&&times < (60 * 60 * 24 * 1000)) {
                countDownTimer = new CountDownTimer( times, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String timeStr = TimeUtil.getTimeFormat(millisUntilFinished / 1000);
                        String[] times = timeStr.split(",");
                        tvHour.setText(times[1]);
                        tvMinter.setText(times[2]);
                        tvSecond.setText(times[3]);

                    }

                    @Override
                    public void onFinish() {
                        tvHour.setText(00);
                        tvMinter.setText(00);
                        tvSecond.setText(00);

                    }
                }.start();
            }
        }
        initPopView();
        showGirdView(null);
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
                        startActivity(new Intent(UsePlanActivity.this, MineUseRecordActivity.class));
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
    public void showGirdView(List<BASEBEAN> list) {//有图片是展示
        View view = stubGird.inflate();
        gridView = (GridView) view.findViewById(R.id.grid);
        adapters = new OrderGridAdapter(this);
        iniGridView(list);
    }
    private void iniGridView(final List<BASEBEAN> list) {

        int length = 90;  //定义一个长度
        int size = 7;  //得到集合长度
        //获得屏幕分辨路
        DisplayMetrics dm = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;

        int gridviewWidth = (int) (size * (length + 10) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(2,0,0,0);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(2); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
//        adapters.setDatas(list);
        gridView.setAdapter(adapters);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
