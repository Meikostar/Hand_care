package com.canplay.medical.mvp.activity.home;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.database.Cursor;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.AlarmClock;
import com.canplay.medical.bean.DATA;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.RingSelectItem;
import com.canplay.medical.mvp.activity.mine.RemindSettingActivity;
import com.canplay.medical.mvp.adapter.RingSelectAdapter;
import com.canplay.medical.mvp.adapter.TimeAddAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.AlarmClockOperate;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.HourSelector;
import com.canplay.medical.view.ListPopupWindow;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RingPopupWindow;
import com.google.zxing.client.android.decode.WeacConstants;
import com.google.zxing.client.android.utils.AudioPlayer;
import com.google.zxing.client.android.utils.MyUtil;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.canplay.medical.R.attr.hour;
import static com.canplay.medical.R.attr.minter;

/**
 * 测量提醒
 */
public class MeasureActivity extends BaseActivity  implements
        LoaderManager.LoaderCallbacks<Cursor> ,BaseContract.View{
    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_again)
    LinearLayout llAgain;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_ring)
    LinearLayout llRing;
    @BindView(R.id.tv_ring)
    TextView tvRing;
    @BindView(R.id.ll_project)
    LinearLayout ll_project;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.listview_all_city)
    ListView listview;
    private Medicine medicine;
    private TimeAddAdapter adapter;
    private List<Medicines> namess;
    /**
     * 闹钟实例
     */
    private AlarmClock mAlarmClock;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_measure_remind);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        medicine= (Medicine) getIntent().getSerializableExtra("data");
        navigationBar.setNavigationBarListener(this);
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);
        adapter=new TimeAddAdapter(this);
        listview.setAdapter(adapter);
        initPopWind();
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
        mAlarmClock.setTag("1");
        mAlarmClock.setRepeat("每天");
        // 响铃周期
        mAlarmClock.setWeeks("2,3,4,5,6,7,1");

    }
    private int type=1;
    private int CHOOSE=6;
    private Mesure mesure=new Mesure();
    private List<String> datas=new ArrayList<>();
    @Override
    public void bindEvents() {
        adapter.setClickListener(new TimeAddAdapter.ItemCliks() {
            @Override
            public void getItem(String menu, int poition) {
                data.remove(poition);
                map.remove(menu);
            }
        });
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                mesure.name=tvType.getText().toString().trim();
                if(medicine==null){
                    if(map.size()==0){
                        showToasts("请添加提醒时间");
                        return;
                    }
                }

                datas.clear();
//                for(String time:map.values()){
//                    datas.add(time);
//                }
                datas.addAll(data);
                mesure.when=datas;
                String userId = SpUtil.getInstance().getUserId();
                mesure.userId=userId;
                mesure.type="time";
                mesure.remindingFor="Measurement";
                presenter.addMesure(mesure);
                showProgress("添加中...");
            }

            @Override
            public void navigationimg() {

            }
        });
     tvAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(mPopupWindow.isShowing()){
                 mPopupWindow.dismiss();
             }else {
                 mPopupWindow.showAtLocation(line , Gravity.BOTTOM, 0, 0);
             }

         }
     });
        ll_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeasureActivity.this, ChooseProjectActivity.class);
                intent.putExtra("status",type);
                startActivityForResult(intent,CHOOSE);
            }
        });
        llAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(line);
            }
        });
        llRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAsDropDown(line);
            }
        });
    }

   private Map<String ,String > map =new HashMap<>();
    private List<String> data =new ArrayList<>();
    private List<DATA> list =new ArrayList<>();
    private List<DATA> lists =new ArrayList<>();

    private ListPopupWindow popupWindow;
    private RingPopupWindow popupWindow1;
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
        DATA data4 = new DATA();
        data4.content="每隔1周";
        DATA data5 = new DATA();
        data5.content="每隔2周";
        list.add(datas);
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        popupWindow=new ListPopupWindow(this,list);
        popupWindow.setSureListener(new ListPopupWindow.ClickListener() {
            @Override
            public void clickListener(DATA menu, int poistion) {
               tvTime.setText(menu.content);
                popupWindow.dismiss();
            }
        });

        if(medicine!=null){
            if(TextUtil.isNotEmpty(medicine.items.get(0).name)){
                tvType.setText(medicine.items.get(0).name);
            }
            String time= map.get(medicine.when);
            if(TextUtil.isEmpty(time)){
                map.put(medicine.when,medicine.when);
                data.add(medicine.when);
                adapter.setData(data);
            }
        }
        initRingPop();
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
    private static final int LOADER_ID = 1;
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
            AudioPlayer.getInstance(MeasureActivity.this).stop();
            return false;
        }
    });


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==CHOOSE){
                 type = data.getIntExtra("type",1);
                if(type==1){
                    tvType.setText("血压");
                }else {
                    tvType.setText("血糖");
                }
            }
        }
    }
    public void initRingPop(){

    }
    private View mView;
   private PopupWindow mPopupWindow;
   private TextView but_confirm;
   private TextView but_cancel;
   private TextView but_title;
   private HourSelector selector;

    public void initPopWind(){
        mView=View.inflate(this, R.layout.time_select, null);
        but_cancel=(TextView)mView.findViewById(R.id.but_cancel);
        but_confirm=(TextView)mView.findViewById(R.id.but_confirm);
        but_title=(TextView)mView.findViewById(R.id.but_title);
        selector=(HourSelector)mView.findViewById(R.id.selector);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        but_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selector = MeasureActivity.this.selector.getSelector();
                String time= map.get(selector);

                if(TextUtil.isEmpty(time)){
                    map.put(selector,selector);
                    data.add(selector);
                    adapter.setData(data);
                }
                mPopupWindow.dismiss();
            }
        });
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


    }
    private List<AlarmClock> dat=new ArrayList<>();
    @Override
    public <T> void toEntity(T entity, int type) {
        dimessProgress();
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE,""));
        for(String time:data){
            String[] split = time.split(":");
            // 初始化闹钟实例的小时
            mAlarmClock.setHour(Integer.valueOf(split[0]));
            // 初始化闹钟实例的分钟
            mAlarmClock.setMinute(Integer.valueOf(split[1]));
            mAlarmClock.setTag("1");
            dat.add(mAlarmClock);
//

        }
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESUREREFASH,dat));
        finish();
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
            showToasts(msg);
        dimessProgress();
    }
}
