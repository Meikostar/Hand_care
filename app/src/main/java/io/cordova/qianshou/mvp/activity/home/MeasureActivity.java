package io.cordova.qianshou.mvp.activity.home;

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
import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.DATA;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.RingSelectItem;
import io.cordova.qianshou.mvp.activity.mine.RemindSettingActivity;
import io.cordova.qianshou.mvp.adapter.RingSelectAdapter;
import io.cordova.qianshou.mvp.adapter.TimeAddAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.util.AlarmClockOperate;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.HourSelector;
import io.cordova.qianshou.view.ListPopupWindow;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.RingPopupWindow;
import com.google.zxing.client.android.decode.WeacConstants;
import com.google.zxing.client.android.utils.AudioPlayer;
import com.google.zxing.client.android.utils.MyUtil;
import android.support.v4.app.LoaderManager;

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
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.DATA;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.RingSelectItem;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.HourSelector;
import io.cordova.qianshou.view.ListPopupWindow;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.RingPopupWindow;

/**
 * 测量提醒
 */
public class MeasureActivity extends BaseActivity implements
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
        mAlarmClock=BaseApplication.getInstance().mAlarmClock;

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
                String time = SpUtil.getInstance().getString("time");
                if (TextUtil.isNotEmpty(time)) {
                    String  times = selector.getSelector();
                    if(time.contains(times+"ma")){
                        showToasts("该时间点已设置提醒");
                        return;
                    }
                }
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

            dat.add(mAlarmClock);
//

        }
//        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESUREREFASH,dat));
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
