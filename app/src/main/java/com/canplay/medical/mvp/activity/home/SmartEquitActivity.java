package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.Equip;
import com.canplay.medical.bean.Euipt;
import com.canplay.medical.mvp.adapter.TimeAdapter;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 血压计、血糖仪等
 */
public class SmartEquitActivity extends BaseActivity implements HomeContract.View {
@Inject
HomePresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_ring)
    TextView tvRing;
    @BindView(R.id.listview)
    ListView listview;
    private Equip data;
    private TimeAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_smart_equit);
        ButterKnife.bind(this);
        data = (Equip) getIntent().getSerializableExtra("data");

        navigationBar.setNavigationBarListener(this);
        if (data != null) {
            if (TextUtil.isNotEmpty(data.name)) {
                navigationBar.setNaviTitle(data.name);
            }
        }
        adapter=new TimeAdapter(this);
        listview.setAdapter(adapter);
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
                Intent intent = new Intent(SmartEquitActivity.this, MeasureActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }

            @Override
            public void navigationimg() {

            }
        });


    }


    @Override
    public void initData() {

    }




    @Override
    public <T> void toEntity(T entity) {
        list= (List<Euipt>) entity;

    }
   private List<Euipt> list;
    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
