package com.canplay.medical.mvp.activity.health;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BasePresenter;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.activity.home.BloodMeasureRecordActivity;
import com.canplay.medical.mvp.adapter.UsePlanAdapter;
import com.canplay.medical.mvp.adapter.UserRecordAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_NavigationBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服药记录
 */
public class TakeMedicineActivity extends BaseActivity implements BaseContract.View{
    @Inject
    BasesPresenter presenter;


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_menu)
    ListView rlMenu;

    private UsePlanAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_take_medicine);
        ButterKnife.bind(this);

        String userId = SpUtil.getInstance().getUserId();
        presenter.getMeasureRecord(userId,"Medicine","0","1000");
        navigationBar.setNavigationBarListener(this);
        adapter = new UsePlanAdapter(this);
        adapter.setType(1);
        rlMenu.setAdapter(adapter);

    }


    @Override
    public void bindEvents() {




    }



    @Override
    public void initData() {

    }

  private List<Record> list;
    @Override
    public <T> void toEntity(T entity, int type) {
        list= (List<Record>) entity;
        adapter.setData(list,0);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
