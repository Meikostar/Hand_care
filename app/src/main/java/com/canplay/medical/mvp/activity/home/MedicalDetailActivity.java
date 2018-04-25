package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Euip;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.mvp.adapter.MedicalDetailAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.OtherContract;
import com.canplay.medical.mvp.present.OtherPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 药品详情
 */
public class MedicalDetailActivity extends BaseActivity implements OtherContract.View{
    @Inject
    OtherPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.listview)
    RegularListView listview;
    private MedicalDetailAdapter adapter;
   private String name;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_medical_detail);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        navigationBar.setNavigationBarListener(this);
        name=getIntent().getStringExtra("name");
        if(TextUtil.isNotEmpty(name)){
            presenter.getMedicalDetail(name);
            tvName.setText(name);

        }
        adapter = new MedicalDetailAdapter(this);
        listview.setAdapter(adapter);

    }

    @Override
    public void bindEvents() {

    }


    @Override
    public void initData() {

    }

    private Medicines medicines;
    private List<Euip> list=new ArrayList<>();
    @Override
    public <T> void toEntity(T entity, int type) {
         medicines= (Medicines) entity;
        Glide.with(this).load(((Medicines) entity).image).asBitmap().placeholder(R.drawable.moren).into(ivImg);
        Euip euip = new Euip();
        euip.name="成分";
        euip.dec=medicines.composition;
        Euip euip1 = new Euip();
        euip1.name="性状";
        euip1.dec=medicines.look;
        Euip euip2 = new Euip();
        euip2.name="适用症";
        euip2.dec=medicines.disease;
        Euip euip3 = new Euip();
        euip3.name="不良发应";
        euip3.dec=medicines.adverseReaction;
        Euip euip4 = new Euip();
        euip4.name="用法用量";
        euip4.dec=medicines.instruction;
        Euip euip5 = new Euip();
        euip5.name="主意事项";
        euip5.dec=medicines.caution;
        list.add(euip);
        list.add(euip1);
        list.add(euip2);
        list.add(euip3);
        list.add(euip4);
        list.add(euip5);
        adapter.setData(list);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
