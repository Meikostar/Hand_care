package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Sug;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加血压测试值/添加血糖测试值
 */
public class AddBloodDataActivity extends BaseActivity implements BaseContract.View {

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_change_psw)
    LinearLayout llChangePsw;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_one)
    EditText etOne;
    @BindView(R.id.et_two)
    EditText etTwo;
    @BindView(R.id.et_three)
    EditText etThree;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tv_type1)
    TextView tvType1;
    @BindView(R.id.tv_type3)
    TextView tvType3;


    private int type;//0添加血压测试值   1 添加血糖测试值

    @Override
    public void initViews() {
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);

        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        type = getIntent().getIntExtra("type", 0);
        navigationBar.setNavigationBarListener(this);

        if (type != 0) {
            navigationBar.setNaviTitle("添加血糖测试值");
            llType.setVisibility(View.GONE);
            tvType.setText("血糖");
            tvName.setText("血糖");
            tvType1.setText("mmol/L");
        }

//        mWindowAddPhoto = new PhotoPopupWindow(this);
    }

    private Sug sug = new Sug();
    private Press per = new Press();

    @Override
    public void bindEvents() {

        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                if (type == 1) {
                    sug.userId = SpUtil.getInstance().getUserId();
                    sug.timeStamp = "" + System.currentTimeMillis();
                    sug.BloodGlucoseLevel = etOne.getText().toString();
                    if(TextUtil.isEmpty(etOne.getText().toString())){
                        showToasts("请输入血糖值");
                        return;
                    }
                    presenter.addBloodSugar(sug);
                } else {
                    per.userId = SpUtil.getInstance().getUserId();
                    per.Low = etTwo.getText().toString().trim();
                    per.high = etOne.getText().toString().trim();
                    per.pulse = etThree.getText().toString().trim();
                    per.timeStamp = "" + System.currentTimeMillis();
                    presenter.addBloodPress(per);
                }
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
    public <T> void toEntity(T entity, int type) {
        if (type == 1) {//添加血糖记录成功

            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.BLOODORSUGAR, ""));
            finish();
        } else if (type == 2) {//添加血压记录成功

            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.BLOODORSUGAR, ""));
            finish();
        }
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
