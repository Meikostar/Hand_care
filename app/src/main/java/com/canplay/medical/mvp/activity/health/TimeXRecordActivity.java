package com.canplay.medical.mvp.activity.health;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.Euip;
import com.canplay.medical.mvp.activity.home.MinePlanRemindActivity;
import com.canplay.medical.mvp.activity.home.MineUseRecordActivity;
import com.canplay.medical.mvp.activity.home.UsePlanActivity;
import com.canplay.medical.mvp.adapter.EuipmentAdapter;
import com.canplay.medical.mvp.adapter.UseTimeAdapter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PhotoPopupWindow;
import com.canplay.medical.view.PopView_NavigationBar;
import com.canplay.medical.view.PopView_TimeRecord;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间轴事件
 */
public class TimeXRecordActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_list)
    ListView rlList;
    private UseTimeAdapter adapter;
    private PopView_TimeRecord popView_timeRecord;
    private String title;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_timex);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("name");
        if (TextUtil.isNotEmpty(title)) {
            navigationBar.setNaviTitle(title);
        }
        navigationBar.setNavigationBarListener(this);
        adapter = new UseTimeAdapter(this);
        rlList.setAdapter(adapter);
        popView_timeRecord = new PopView_TimeRecord(this,1);
        popView_timeRecord.setView(line);

        popView_timeRecord.setClickListener(new PopView_TimeRecord.ItemCliskListeners() {
            @Override
            public void clickListener(int poition) {
                switch (poition) {
                    case 0://全部

                        break;
                    case 1://血压

                        break;
                    case 2://服药

                        break;
                    case 3://血糖

                        break;

                }
                popView_timeRecord.dismiss();
            }

        });
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
                popView_timeRecord.showPopView();
            }

            @Override
            public void navigationimg() {

            }
        });


    }



    @Override
    public void initData() {

    }

}
