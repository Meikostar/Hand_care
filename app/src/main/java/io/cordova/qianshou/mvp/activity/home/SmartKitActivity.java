package io.cordova.qianshou.mvp.activity.home;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Euip;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.mvp.adapter.EuipmentAdapter;
import io.cordova.qianshou.mvp.adapter.ItemAdapter;
import io.cordova.qianshou.mvp.adapter.SmartCycAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.NoScrollGridView;
import io.cordova.qianshou.view.RegularListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 智能药盒
 */
public class SmartKitActivity extends BaseActivity implements BaseContract.View {

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.rl_menu)
    RegularListView rlMenu;
    @BindView(R.id.grid_view)
    NoScrollGridView gridView;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private ItemAdapter adapter;
    private SmartCycAdapter adapters;
    private List<Medicines> list=new ArrayList<>();
    private Box boxs;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_smart_kit);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        showProgress("加载中...");
        presenter.getBoxInfo();
        navigationBar.setNavigationBarListener(this);
        adapter = new ItemAdapter(this);
        adapters = new SmartCycAdapter(this);
        rlMenu.setAdapter(adapter);
        gridView.setAdapter(adapters);

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

        adapter.setClickListener(new ItemAdapter.ItemCliks() {
            @Override
            public void getItem(Box menu, int type) {
                Intent intent = new Intent(SmartKitActivity.this, MedicalDetailActivity.class);
                intent.putExtra("name",menu.medicine);
                startActivity(intent);
            }
        });
        adapters.setClickListener(new SmartCycAdapter.ItemCliks() {
            @Override
            public void getItem(Box box, int poistion) {
                datas=box.medicines;

                adapter.setData(box.medicines);
                tvType.setText("药物种类（"+ box.medicines.size()+")");
                tvTime.setText(TimeUtil.formatTims(box.dateTime));
                tvCode.setText("0"+(poistion+1)+"药盒");
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
                if(datas!=null){
                    for(Box box:datas){
                        Medicines medicines = new Medicines();
                        medicines.name=box.medicine;
                        list.add(medicines);
                    }
                    RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.CHOOSMEDICALSS,list));
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
        dimessProgress();
        Box box= (Box) entity;
        if(box!=null){
            data=box.cups;
//        tvTime.setText(TimeUtil.formatTims(box.planCreatedDateTime));
            int poistion=0;
            for(int i=0;i<data.size();i++){
                if(data.get(i).status==1){
                    data.get(i).status=3;
                    poistion=i;
                    break;
                }
            }
            datas=data.get(poistion).medicines;
            time=data.get(poistion).dateTime;
            tvTime.setText(TimeUtil.formatTims(time));
            tvType.setText("药物种类（"+ data.get(poistion).medicines.size()+")");
            adapter.setData(data.get(poistion).medicines);
            adapters.setData(data);
        }

    }
    private long time;
    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
        dimessProgress();
        showToasts(msg);
    }
}
