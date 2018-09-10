package io.cordova.qianshou.mvp.activity.home;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.view.NavigationBar;

/**
 * 添加数据
 */
public class AddDataActivity extends BaseActivity implements BaseContract.View {

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
    @BindView(R.id.et_sugar)
    EditText etSugar;


    private int type;//0添加血压测试值   1 添加血糖测试值
    @Override
    public void initViews() {
        setContentView(R.layout.activity_add_datas);
        ButterKnife.bind(this);

        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        type=getIntent().getIntExtra("type",0);
        navigationBar.setNavigationBarListener(this);



//        mWindowAddPhoto = new PhotoPopupWindow(this);
    }
    private Sug sug=new Sug();
    private Press per=new Press();
    @Override
    public void bindEvents() {

       navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
           @Override
           public void navigationLeft() {
               finish();
           }

           @Override
           public void navigationRight() {
             if(type==1){
                 sug.userId= SpUtil.getInstance().getUserId();
                 sug.timeStamp=""+System.currentTimeMillis();
                 sug.BloodGlucoseLevel=etOne.getText().toString();
                 presenter.addBloodSugar(sug);
             }else {
                 per.userId=SpUtil.getInstance().getUserId();
                 per.high=etTwo.getText().toString().trim();
                 per.Low=etOne.getText().toString().trim();
                 per.pulse=etThree.getText().toString().trim();
                 per.timeStamp=""+System.currentTimeMillis();
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
         if(type==1){//添加血糖记录成功
             finish();
         }else if(type==2){//添加血压记录成功
             finish();
         }
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }





}
