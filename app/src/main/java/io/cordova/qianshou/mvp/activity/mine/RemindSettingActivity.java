package io.cordova.qianshou.mvp.activity.mine;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.DATA;
import io.cordova.qianshou.bean.Item;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.RingSelectItem;
import io.cordova.qianshou.bean.Decs;
import io.cordova.qianshou.mvp.activity.home.ChooseMedicalActivity;
import io.cordova.qianshou.mvp.activity.home.SmartKitActivity;
import io.cordova.qianshou.mvp.adapter.MedicaldTurnapter;
import io.cordova.qianshou.mvp.adapter.RingSelectAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.HourSelector;
import io.cordova.qianshou.view.ListPopupWindow;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.RegularListView;
import io.cordova.qianshou.view.RingPopupWindow;
import com.google.gson.Gson;
import com.google.zxing.client.android.decode.WeacConstants;
import com.google.zxing.client.android.utils.AudioPlayer;
import com.google.zxing.client.android.utils.MyUtil;
import io.cordova.qianshou.mvp.activity.home.MedicalDetailActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.DATA;
import io.cordova.qianshou.bean.Decs;
import io.cordova.qianshou.bean.Item;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.RingSelectItem;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.HourSelector;
import io.cordova.qianshou.view.ListPopupWindow;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.RegularListView;
import io.cordova.qianshou.view.RingPopupWindow;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 设置提醒
 */
public class RemindSettingActivity extends BaseActivity implements
        LoaderManager.LoaderCallbacks<Cursor> ,BaseContract.View{

    @Inject
    BasesPresenter presenter;

    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_have)
    TextView tvHave;
    @BindView(R.id.ll_box)
    LinearLayout llBox;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.lv_info)
    RegularListView lvInfo;
    @BindView(R.id.tv_frequency)
    TextView tvFrequency;
    @BindView(R.id.ll_again)
    LinearLayout llAgain;
    @BindView(R.id.tv_ring)
    TextView tvRing;
    @BindView(R.id.ll_ring)
    LinearLayout llRing;
    @BindView(R.id.selector)
    HourSelector selector;
    private ListPopupWindow popupWindow;
    private RingPopupWindow popupWindow1;
    private MedicaldTurnapter adapter;
    private Medicine medicine;
    /**
     * loader Id
     */
    private static final int LOADER_ID = 1;
    private Subscription mSubscription;
    private List<Medicines> dat=new ArrayList<>();
    private AddMedical medical=new AddMedical();
    @Override
    public void initViews() {
        setContentView(R.layout.activity_remind_setting);
        ButterKnife.bind(this);

        medicine= (Medicine) getIntent().getSerializableExtra("data");
        navigationBar.setNavigationBarListener(this);
        LoaderManager loaderManager = getSupportLoaderManager();
        // 注册Loader
        if(medicine!=null){
            if(TextUtil.isNotEmpty(medicine.when)){

            }
           String[] split = medicine.when.split(":");
           if(split!=null&&split.length==2){
               selector.setDatas((Integer.valueOf(split[0])-1),Integer.valueOf(split[1]));
           }
       }
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        loaderManager.initLoader(LOADER_ID, null, this);
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if(SubscriptionBean.CHOOSMEDICALSS==bean.type){
                    dat= (List<Medicines>) bean.content;
                    for(Medicines medicines:dat){
                        Medicines medicine = map.get(medicines.name);
                        if(medicine==null){
                            datass.add(medicines);
                            map.put(medicine.name,medicine);
                        }
                    }
                     adapter.setData(datass,0);
                }


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        mAlarmClock = new AlarmClock();
        // 闹钟默认开启
        mAlarmClock.setOnOff(true);
        // 保存设置的音量
        mAlarmClock.setVolume(15);

        // 初始化闹钟实例的小时
        mAlarmClock.setHour(9);
        // 初始化闹钟实例的分钟
        mAlarmClock.setMinute(30);
        // 默认小睡
        mAlarmClock.setNap(true);
        // 小睡间隔10分钟
        mAlarmClock.setNapInterval(5);
        // 小睡3次
        mAlarmClock.setNapTimes(3);
        // 取得铃声选择配置信息
        SharedPreferences share = getSharedPreferences(
                WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
        String ringName = share.getString(WeacConstants.RING_NAME,
                getString(R.string.default_ring));
        String ringUrl = share.getString(WeacConstants.RING_URL,
                WeacConstants.DEFAULT_RING_URL);

        // 初始化闹钟实例的铃声名
        mAlarmClock.setRingName(ringName);
        // 初始化闹钟实例的铃声播放地址
        mAlarmClock.setRingUrl(ringUrl);
        mAlarmClock.setRepeat("每天");
        // 响铃周期
        mAlarmClock.setWeeks("2,3,4,5,6,7,1");
        RxBus.getInstance().addSubscription(mSubscription);
        adapter=new MedicaldTurnapter(this);
        lvInfo.setAdapter(adapter);
        if(medicine!=null){
            for(Item item:medicine.items){
                Medicines medicines = new Medicines();
                medicines.name=item.name;
                medicines.message=item.message;
                medicines.isCheck=true;
                dat.add(medicines);
            }
            adapter.setData(dat,0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }
   private List<String> times=new ArrayList<>();
   private List<Decs> datas=new ArrayList<>();
    List<Medicines> datass=new ArrayList<>();
    private int hour;
    private int minter;
    @Override
    public void bindEvents() {
        adapter.setClickListener(new MedicaldTurnapter.ClickListener() {
            @Override
            public void clickListener(Medicines medicines, int poition) {
                if(poition==-1){
                    Intent intent = new Intent(RemindSettingActivity.this, MedicalDetailActivity.class);
                    intent.putExtra("name",medicines.name);
                    startActivity(intent);
                }else {
                    datass.remove(poition);
                    map.remove(medicines.name);
                    adapter.setData(datass,0);
                }



            }
        });

        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                namess = adapter.getData();
                hour=Integer.valueOf(selector.getHour());
                minter=Integer.valueOf(selector.getMinute());
                medical.when=selector.getSelector();
                medical.repetition="1";
                medical.ringTone="";
                medical.when=selector.getSelector();
                cout=0;
                if(namess==null||namess.size()==0){
                    showToasts("请选择药物");
                    return;
                }


                String time = SpUtil.getInstance().getString("time");
                if (TextUtil.isNotEmpty(time)) {
                String  times = selector.getSelector();
                if(time.contains(times)){
                    showToasts("该时间点已设置提醒");
                    return;
                }
                }
                datas.clear();
                for(Medicines name:namess){
                    Decs dec=new Decs();
                    if(TextUtil.isNotEmpty(name.message)){
                        dec.dosage=name.message;
                    }else {
                        dec.dosage="1粒";
                    }
                    if(TextUtil.isNotEmpty(name.image)){
                        dec.image=name.image;
                    }else {
                        dec.image="";
                    }
                    if(TextUtil.isNotEmpty(name.name)){
                        dec.name=name.name;
                    }else {
                        dec.name="";
                    }
                    if(TextUtil.isNotEmpty(name.specs)){
                        dec.specs=name.specs;
                    }else {
                        dec.specs="";
                    }

                    medical.type="time";
                    medical.repetition	="1";
                    medical.ringTone="";
                 datas.add(dec);

                }

                medical.medicines=datas;
                showProgress("添加中...");
                presenter.addMediacl(medical);


            }

            @Override
            public void navigationimg() {

            }
        });
        llBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RemindSettingActivity.this,SmartKitActivity.class));
            }
        });
       tvAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(RemindSettingActivity.this, ChooseMedicalActivity.class);
               startActivityForResult(intent,77);
           }
       });
        llAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popupWindow.showAsDropDown(line);
            }
        });
        llRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow1!=null){
                    popupWindow1.showAsDropDown(line);
                }

            }
        });
    }
  private int cout;
    private Map<String ,Medicines> map=new HashMap<>();
    private Gson gson=new Gson();
  private List<DATA> data=new ArrayList<>();
    @Override
    public void initData() {
        DATA datas = new DATA();
        datas.content="每天";
        DATA data1 = new DATA();
        data1.content="每隔一天";
        DATA data2 = new DATA();
        data2.content="每隔两天";
        DATA data3 = new DATA();
        data3.content="每隔三天";
        data.add(datas);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        popupWindow=new ListPopupWindow(this,data);
        popupWindow.setSureListener(new ListPopupWindow.ClickListener() {
            @Override
            public void clickListener(DATA menu, int poistion) {
                   tvFrequency.setText(menu.content);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK==resultCode){
            Medicines med= (Medicines) data.getSerializableExtra("data");
            dat=med.data;
            for(Medicines medicines:dat){
                Medicines medicine = map.get(medicines.name);
                if(medicine==null){
                    datass.add(medicines);
                    map.put(medicines.name,medicines);
                }
            }
            adapter.setData(datass,0);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // 查询内部存储音频文件
        return new CursorLoader(this,
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA}, null, null,
                MediaStore.Audio.Media.DISPLAY_NAME);
    }

    /**
     * 铃声选择位置
     */
    private int mPosition = 0;
    private RingSelectAdapter mSystemRingAdapter;

    /**
     * 铃声名
     */
    public  String sRingName;
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
                // 当为编辑闹钟状态时，铃声名为闹钟单例铃声名
                String ringName1;
                if (sRingName != null) {
                    ringName1 = sRingName;
                } else {
                    SharedPreferences share = getSharedPreferences(
                            WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
                    // 当为新建闹钟状态时，铃声名为最近一次选择保存的铃声名,没有的话为默认铃声
                    ringName1 = share.getString(WeacConstants.RING_NAME,
                           "默认铃声");
                }

                // 过滤重复音频文件的Set
                HashSet<String> set = new HashSet<>();

                //  保存铃声信息的List
                List<Map<String, String>> list = new ArrayList<>();
                // 添加默认铃声
                Map<String, String> defaultRing = new HashMap<>();
                defaultRing.put(WeacConstants.RING_NAME, "默认铃声");
                defaultRing.put(WeacConstants.RING_URL, WeacConstants.DEFAULT_RING_URL);
                list.add(defaultRing);
                set.add("默认铃声");

                // 保存的铃声名为默认铃声，设置该列表的显示位置
                if ("默认铃声".equals(ringName1)) {
                    mPosition = 0;
                    RingSelectItem.getInstance().setRingPager(0);
                }

                // 添加无铃声
                Map<String, String> noRing = new HashMap<>();
                noRing.put(WeacConstants.RING_NAME, "无铃声");
                noRing.put(WeacConstants.RING_URL, WeacConstants.NO_RING_URL);
                list.add(noRing);
                set.add("无铃声");

                // 当列表中存在与保存的铃声名一致时，设置该列表的显示位置
                if ("无铃声".equals(ringName1)) {
                    mPosition = list.size() - 1;
                    RingSelectItem.getInstance().setRingPager(0);
                }

                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        // 音频文件名
                        String ringName = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        if (ringName != null) {
                            // 当过滤集合里不存在此音频文件
                            if (!set.contains(ringName)) {
                                // 添加音频文件到列表过滤同名文件
                                set.add(ringName);
                                // 去掉音频文件的扩展名
                                ringName = MyUtil.removeEx(ringName);
                                // 取得音频文件的地址
                                String ringUrl = cursor.getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.DATA));
                                Map<String, String> map = new HashMap<>();
                                map.put(WeacConstants.RING_NAME, ringName);
                                map.put(WeacConstants.RING_URL, ringUrl);
                                list.add(map);
                                // 当列表中存在与保存的铃声名一致时，设置该列表的显示位置
                                if (ringName.equals(ringName1)) {
                                    mPosition = list.size() - 1;
                                    RingSelectItem.getInstance().setRingPager(0);
                                }
                            }
                        }
                    }
                }

                mSystemRingAdapter = new RingSelectAdapter(this, list, ringName1);
                if(popupWindow1==null){

                    popupWindow1=new RingPopupWindow(this,mSystemRingAdapter);
//                    popupWindow1.remove();
                    popupWindow1.setSureListener(new RingPopupWindow.ClickListener() {
                        @Override
                        public void clickListener(String ringName, String ringUrl) {
                            mAlarmClock.setRingName(ringName);
                            // 初始化闹钟实例的铃声播放地址
                            mAlarmClock.setRingUrl(ringUrl);
                            tvRing.setText(ringName);
                            popupWindow1.dismiss();
                            handler.sendMessageDelayed(handler.obtainMessage(),1200);
                        }
                    });
                }

                break;
        }
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            AudioPlayer.getInstance(RemindSettingActivity.this).stop();
            return false;
        }
    });
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    private List<Medicines> namess;
    /**
     * 闹钟实例
     */
    private AlarmClock mAlarmClock;

    @Override
    public <T> void toEntity(T entity, int type) {


            showToasts("用药提醒添加成功");
            dimessProgress();

            // 初始化闹钟实例的小时
            mAlarmClock.setHour(hour);
            // 初始化闹钟实例的分钟
            mAlarmClock.setMinute(minter);
            mAlarmClock.setTag("2");
//            AlarmClockOperate.getInstance().saveAlarmClock(mAlarmClock);
//            RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MEDICALREFASH,mAlarmClock));
            finish();

        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURES,"data"));
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE,"data"));
        cout++;
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }

}
