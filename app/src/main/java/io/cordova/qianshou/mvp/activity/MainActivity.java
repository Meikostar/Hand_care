package io.cordova.qianshou.mvp.activity;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseAllActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseDailogManager;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.bean.WeacConstants;
import io.cordova.qianshou.bean.unBind;
import io.cordova.qianshou.fragment.HomeDoctorFragment;
import io.cordova.qianshou.fragment.HomeFragment;
import io.cordova.qianshou.fragment.HealthDataFragment;
import io.cordova.qianshou.fragment.SetFragment;
import io.cordova.qianshou.mvp.activity.health.BloodChartRecordActivity;
import io.cordova.qianshou.mvp.activity.health.SugarChartRecordActivity;
import io.cordova.qianshou.mvp.activity.mine.AddFriendActivity;
import io.cordova.qianshou.mvp.activity.mine.FriendDetailActivity;
import io.cordova.qianshou.mvp.activity.mine.MineInfoActivity;
import io.cordova.qianshou.mvp.adapter.FragmentViewPagerAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.component.OnChangeListener;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.receiver.AlarmReceiver;
import io.cordova.qianshou.receiver.DaemonService;
import io.cordova.qianshou.receiver.PhoneReceiver;
import io.cordova.qianshou.receiver.Service1;
import io.cordova.qianshou.util.MyUtil;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.BottonNevgBar;
import io.cordova.qianshou.view.ChangeNoticeDialog;
import io.cordova.qianshou.view.MarkaBaseDialog;
import io.cordova.qianshou.view.NoScrollViewPager;
import com.google.gson.Gson;
import com.google.zxing.client.android.activity.CaptureActivity;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import io.cordova.qianshou.base.BaseAllActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseDailogManager;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.fragment.HealthDataFragment;
import io.cordova.qianshou.fragment.HomeDoctorFragment;
import io.cordova.qianshou.fragment.HomeFragment;
import io.cordova.qianshou.fragment.SetFragment;
import io.cordova.qianshou.mvp.activity.health.BloodChartRecordActivity;
import io.cordova.qianshou.mvp.activity.health.SugarChartRecordActivity;
import io.cordova.qianshou.mvp.activity.mine.FriendDetailActivity;
import io.cordova.qianshou.mvp.component.OnChangeListener;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.receiver.DaemonService;
import io.cordova.qianshou.receiver.PhoneReceiver;
import io.cordova.qianshou.receiver.Service1;
import io.cordova.qianshou.util.MyUtil;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.BottonNevgBar;
import io.cordova.qianshou.view.ChangeNoticeDialog;
import io.cordova.qianshou.view.MarkaBaseDialog;
import io.cordova.qianshou.view.NoScrollViewPager;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseAllActivity implements HomeFragment.ScanListener, HomeContract.View {
    @Inject
    HomePresenter presenter;
    NoScrollViewPager viewpagerMain;
    BottonNevgBar bnbHome;
    private Subscription mSubscription;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;
    private int current = 0;
    private long firstTime = 0l;
    private HomeDoctorFragment homeDoctorFragment;
    private HomeFragment homeFragment;

    private HealthDataFragment menutFragment;
    private SetFragment setFragment;
    private View line;
    private ChangeNoticeDialog dialog;
    private int status;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        registerPhoneReceiver();
        bnbHome = (BottonNevgBar) findViewById(R.id.bnb_home);
        line =  findViewById(R.id.line);
        status=getIntent().getIntExtra("type",0);
        startService(new Intent(this, DaemonService.class));
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        user_id = SpUtil.getInstance().getUserId();
        viewpagerMain = (NoScrollViewPager) findViewById(R.id.viewpager_main);
        viewpagerMain.setScanScroll(false);
        dialog=new ChangeNoticeDialog(this,line);
        if(status==1){
            startActivity(new Intent(this, BloodChartRecordActivity.class));
        }else if(status==2){
            startActivity(new Intent(this, SugarChartRecordActivity.class));
        }else if(status==88){
            presenter.MedicineRemindList();
            presenter.MeasureRemindList();
        }

    }
    private void alarm() {
        startService(new Intent(MainActivity.this, Service1.class));

    }
    @Override
    public void bindEvents() {

        setViewPagerListener();
        setNevgBarChangeListener();


        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if(SubscriptionBean.MENU_SCAN==bean.type){
                  dialog.show();
                }else if(SubscriptionBean.FINISH==bean.type){
                    finish();
                }


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);

        dialog.setBindClickListener(new ChangeNoticeDialog.BindClickListener() {
            @Override
            public void teaMoney(String money) {
                dialog.dismiss();
            }
        });
    }
    /**
     * 注册电话监听
     */
    private void registerPhoneReceiver() {
        if (phoneReceiver == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PHONE_STATE");
            intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
            phoneReceiver = new PhoneReceiver(new PhoneReceiver.OnPhoneListener() {
                @Override
                public void onPhoneResume() {

                }

                @Override
                public void onPhoneIdle() {
                    //开始播放音乐

                }
            });
            registerReceiver(phoneReceiver, intentFilter);
        }
    }

    private PhoneReceiver phoneReceiver;
    /**
     * 解注册电话监听
     */
    private void unRegisterPhoneReceiver() {

        if (phoneReceiver != null) {
            unregisterReceiver(phoneReceiver);
        }

    }
    @Override
    public void initData() {
        addFragment();
        mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewpagerMain.setAdapter(mainViewPagerAdapter);
        viewpagerMain.setOffscreenPageLimit(3);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(current);
        bnbHome.setSelect(current);
        if(BaseApplication.province==null){
            Observable.create(new Observable.OnSubscribe<JSONObject>() {

                @Override
                public void call(Subscriber<? super JSONObject> subscriber) {
                    JSONObject json = TextUtil.getJson("city.json",MainActivity.this);
                    subscriber.onNext(json);
                    subscriber.onCompleted();//结束异步任务

                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<JSONObject>() {
                        @Override
                        public void call(JSONObject json) {
                            JSONObject dataJson = json.optJSONObject("data");
                            BaseApplication.province = new Gson().fromJson(dataJson.toString(), Province.class);

                        }
                    });
        }
    }

    private void setViewPagerListener() {
        viewpagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bnbHome.setSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setNevgBarChangeListener() {
        bnbHome.setOnChangeListener(new OnChangeListener() {
            @Override
            public void onChagne(int currentIndex) {
                current = currentIndex;
                bnbHome.setSelect(currentIndex);alarm();
                viewpagerMain.setCurrentItem(currentIndex);
            }
        });
    }

    private void addFragment() {
        mFragments = new ArrayList<>();
        homeDoctorFragment=new HomeDoctorFragment();

        menutFragment=new HealthDataFragment();
        homeFragment=new HomeFragment();
        setFragment=new SetFragment();
        mFragments.add(homeFragment);
        mFragments.add(homeDoctorFragment);
        mFragments.add(menutFragment);
        mFragments.add(setFragment);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterPhoneReceiver();
        if (mSubscription != null) {
            RxBus.getInstance().unSub(mSubscription);
        }
    }

    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
         /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
         * 也可以不传这个参数
         * 不传的话  默认都为默认不震动  其他都为true
         * */

        //ZxingConfig config = new ZxingConfig();
        //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
        //config.setPlayBeep(true);//是否播放提示音
        //config.setShake(true);//是否震动
        //config.setShowAlbum(true);//是否显示相册
        //config.setShowFlashLight(true);//是否显示闪光灯
        //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }
    private String user_id;
    private Bind bind = new Bind();
    private int REQUEST_CODE_SCAN=6;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra("scan_result");
                if(TextUtil.isNotEmpty(content)){
                    String[] split = content.split("###");
                    if(split!=null&&split.length==2){
                        Intent intent = new Intent(MainActivity.this, FriendDetailActivity.class);
                        intent.putExtra("id",split[1]);
                        intent.putExtra("status","add");
                        startActivity(intent);
                    }else {
                        showPopwindow(content);
                    }
                }
//                showToasts("扫描结果为：" +content);
//                result.setText("扫描结果为：" + content);
            }
        }
    }

    @Override
    public void scanListener() {
        PermissionGen.with(MainActivity.this)//动态权限
                .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                .permissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
    }
    private void addList(AlarmClock ac) {
        MyUtil.startAlarmClock(this, ac);

    }
    private List<Medicine> data;
    @Override
    public <T> void toEntity(T entity,int type) {
        data = (List<Medicine>) entity;
         if(type==67){

             for (Medicine medicine : data) {


                         String[] split = medicine.when.split(":");
                         arm = BaseApplication.getInstance().mAlarmClock;
                         arm.setHour(Integer.valueOf(split[0]));
                         arm.setMinute(Integer.valueOf(split[1]));

                         arm.setTag("2" + ":" + (medicine.reminderTimeId == null ? "##" : medicine.reminderTimeId));
                         int id = arm.getId();
                         arm.setId(id + 1);
                         Gson gson = new Gson();
                         String jsonStr = gson.toJson(arm); //将对象转换成Json
                         SpUtil.getInstance().putString(medicine.when + "ma", jsonStr);
                         String time = SpUtil.getInstance().getString("time");
                         if (TextUtil.isNotEmpty(time)) {
                             SpUtil.getInstance().putString("time", time + "," + medicine.when + "ma");
                         } else {
                             SpUtil.getInstance().putString("time", medicine.when + "ma");
                         }
                         addList(arm);


             }
         }else {

             for (Medicine medicine : data) {
                         String[] split = medicine.when.split(":");
                         arm = BaseApplication.getInstance().mAlarmClock;
                         arm.setHour(Integer.valueOf(split[0]));
                         arm.setMinute(Integer.valueOf(split[1]));

                         arm.setTag(1 + ":" + (medicine.reminderTimeId != null ? medicine.reminderTimeId : "###") + ":" + (medicine.items.get(0).name != null ? (medicine.items.get(0).name.equals("血压") ? "血压" : "血糖") : "血压"));
                         int id = arm.getId();
                         arm.setId(id + 1);
                         Gson gson = new Gson();
                         String jsonStr = gson.toJson(arm); //将对象转换成Json
                         SpUtil.getInstance().putString(medicine.when, jsonStr);
                         String time = SpUtil.getInstance().getString("time");
                         if (TextUtil.isNotEmpty(time)) {
                             SpUtil.getInstance().putString("time", time + "," + medicine.when + "");
                         } else {
                             SpUtil.getInstance().putString("time", medicine.when + "");
                         }
                         addList(arm);


             }
         }
    }
    private AlarmClock arm;
    @Override
    public void toNextStep(int type) {
       showToasts("绑定成功");
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.EUIP_REFASH,""));
    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
    }

    private View views=null;
    private TextView sure = null;
    private TextView cancel = null;
    private TextView title = null;
    private EditText reson = null;
    public void showPopwindow(final String content) {

        views = View.inflate(this, R.layout.add_euip, null);
        sure = (TextView) views.findViewById(R.id.txt_sure);
        cancel = (TextView) views.findViewById(R.id.txt_cancel);
        title = (TextView) views.findViewById(R.id.tv_title);
        reson = (EditText) views.findViewById(R.id.edit_reson);
        title.setText("设备号："+content+"添加到到设备吗?");
        final MarkaBaseDialog dialog = BaseDailogManager.getInstance().getBuilder(this).setMessageView(views).create();
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final EditText finalReson = reson;
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.serialNo = content;
                bind.userId = user_id;
                presenter.bindDevice(bind);
                dialog.dismiss();
            }
        });


    }

//
//    //屏蔽返回键的代码:
//    public boolean onKeyDown(int keyCode,KeyEvent event)
//    {
//        switch(keyCode)
//        {
//            case KeyEvent.KEYCODE_BACK:return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}
