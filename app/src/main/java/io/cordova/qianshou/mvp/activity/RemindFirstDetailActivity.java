package io.cordova.qianshou.mvp.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.WeacConstants;
import io.cordova.qianshou.bean.WeacStatus;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.mvp.activity.home.MedicalDetailActivity;
import io.cordova.qianshou.mvp.activity.mine.AlarmActivity;
import io.cordova.qianshou.mvp.adapter.DetailAdapter;
import io.cordova.qianshou.mvp.adapter.ItemAdapter;
import io.cordova.qianshou.mvp.adapter.SmartCycAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.receiver.AlarmClockBroadcast;
import io.cordova.qianshou.receiver.PhoneReceiver;
import io.cordova.qianshou.util.AlarmClockOperate;
import io.cordova.qianshou.util.AudioPlayer;
import io.cordova.qianshou.util.Parcelables;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.RegularListView;
import com.google.gson.Gson;
import com.google.zxing.client.android.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.WeacConstants;
import io.cordova.qianshou.bean.WeacStatus;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.receiver.AlarmClockBroadcast;
import io.cordova.qianshou.receiver.PhoneReceiver;
import io.cordova.qianshou.util.AudioPlayer;
import io.cordova.qianshou.util.Parcelables;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.RegularListView;

/**
 * 提醒详情1
 */
public class RemindFirstDetailActivity extends BaseActivity implements BaseContract.View {

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_expend)
    ImageView ivExpend;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.rl_menu)
    RegularListView rlMenu;
    @BindView(R.id.tv_sure)
    TextView tvSure;

    private DetailAdapter adapter;
    private List<Medicines> list = new ArrayList<>();
    private Box boxs;
    private boolean isShow;
    private Animation animationhide;
    private Animation animationshow;

    private int hour;
    private int minture;
    private AlarmClock alarmClock;
    @Override
    public void initViews() {
        setContentView(R.layout.remind_first_activity);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        registerPhoneReceiver();
        navigationBar.setNavigationBarListener(this);
        alarmClock=getIntent().getParcelableExtra("data");
        String[] split = alarmClock.getTag().split(":");
        reminderTimeId=split[1];

        String jsonStr = SpUtil.getInstance().getString(reminderTimeId+"medcial");
        Gson gson = new Gson();
        Detail detail = gson.fromJson(jsonStr, Detail.class); //将json字符串转换成 AlarmClock对象

      if(BaseApplication.isOwn){
          tvSure.setText("关闭闹铃");
      }
        adapter = new DetailAdapter(this);
        rlMenu.setAdapter(adapter);
        if(detail!=null){
            data=detail;
            setData();
        }else {
            showProgress("加载中...");
            presenter.getDetail(reminderTimeId);
        }
        animationhide = AnimationUtils.loadAnimation(this, R.anim.list_hide);
        animationshow = AnimationUtils.loadAnimation(this, R.anim.list_show);
        WeacStatus.sActivityNumber++;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 画面出现在解锁屏幕上,显示,常亮
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (mAlarmClock != null) {
            // 取得小睡间隔
            mNapInterval = mAlarmClock.getNapInterval();
            // 取得小睡次数
            mNapTimes = mAlarmClock.getNapTimes();
        }
        // XXX:修正小睡数
        // mNapTimes = 1000;
        // 小睡已执行次数
        mNapTimesRan = getIntent().getIntExtra(
                WeacConstants.NAP_RAN_TIMES, 0);
        // 播放铃声
        playRing();

        mNotificationManager = NotificationManagerCompat.from(this);
        if (mAlarmClock != null) {
            // 取消下拉列表通知消息
            mNotificationManager.cancel(mAlarmClock.getId());
        }
        countDownTimer = new CountDownTimer(1000*30, 30000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE, ""));
               finish();


            }
        }.start();

    }
   private CountDownTimer countDownTimer;
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
                    //暂停音乐
                    finish();
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
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    super.handleMessage(msg);
            }
        }

    };
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void bindEvents() {
        ivExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    isShow = false;
                    rlMenu.startAnimation(animationhide);
                    rlMenu.setVisibility(View.GONE);
                } else {
                    isShow = true;
                    rlMenu.startAnimation(animationshow);
                    rlMenu.setVisibility(View.VISIBLE);
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

            }

            @Override
            public void navigationimg() {

            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BaseApplication.isOwn){
                    startActivity(new Intent(RemindFirstDetailActivity.this, LoginActivity.class));
                    AudioPlayer.getInstance(RemindFirstDetailActivity.this).stop();
                    finish();
                }else {
                    showProgress("确认中...");

                    presenter.confirmEat(reminderTimeId);
                    if(cout==1){
                        cout=0;
                    }
                    ++cout;
                }

            }
        });

    }
    private int cout;

    @Override
    public void initData() {

    }

    private Detail data;
    private List<Detail> datas;
    private String reminderTimeId="";
    @Override
    public <T> void toEntity(T entity, int type) {
        dimessProgress();
        data = (Detail) entity;

        setData();

    }
   public void setData(){
       if (data != null) {
           if (TextUtil.isNotEmpty(data.code)) {


           }
           if (TextUtil.isNotEmpty(data.time)) {
               tvTimes.setText(data.time);
           }
           if(TextUtil.isNotEmpty(data.type)){
               if(Integer.valueOf(data.type)==2){
                   ivImg.setImageResource(R.drawable.yaohe);
                   tvState.setText("请服用智能药杯");
               }else {
                   if (data.code.equals("早")) {
                       ivImg.setImageResource(R.drawable.zs);
                   } else if (data.code.equals("中")) {
                       ivImg.setImageResource(R.drawable.qianshou);
                   } else if (data.code.equals("晚")) {
                       ivImg.setImageResource(R.drawable.wt);
                   }
                   tvState.setText("请服用"+data.code+"药盒");
               }
           }
           datas = data.items;
           tvType.setText("药物种类（" + data.items.size() + ")");
           adapter.setData(datas);
       }
   }
    @Override
    public void toNextStep(int type) {
        dimessProgress();
        showTomast("用药已确认");
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.FINISH,""));
        RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE,""));
        startActivity(new Intent(this, LoginActivity.class));
        AudioPlayer.getInstance(this).stop();
        finish();
    }

    @Override
    public void showTomast(String msg) {
        dimessProgress();
    }
    /**
     * 播放铃声
     */
    private void playRing() {
        mAudioManager = (AudioManager) getSystemService(
                Context.AUDIO_SERVICE);
        mCurrentVolume = mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        if (mAlarmClock != null) {
            // 设置铃声音量
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                    mAlarmClock.getVolume(), AudioManager.ADJUST_SAME);

            // 默认铃声
            if (mAlarmClock.getRingUrl().equals(WeacConstants.DEFAULT_RING_URL)
                    || TextUtils.isEmpty(mAlarmClock.getRingUrl())) {
                // 振动模式
                if (mAlarmClock.isVibrate()) {
                    // 播放
                    AudioPlayer.getInstance(this).playRaw(
                            R.raw.ring_weac_alarm_clock_default, true, true);
                } else {
                    AudioPlayer.getInstance(this).playRaw(
                            R.raw.ring_weac_alarm_clock_default, true, false);
                }

                // 无铃声
            } else if (mAlarmClock.getRingUrl().equals(WeacConstants.NO_RING_URL)) {
                // 振动模式
                if (mAlarmClock.isVibrate()) {
                    AudioPlayer.getInstance(this).stop();
                    AudioPlayer.getInstance(this).vibrate();
                } else {
                    AudioPlayer.getInstance(this).stop();
                }
            } else {
                // 振动模式
                if (mAlarmClock.isVibrate()) {
                    AudioPlayer.getInstance(this).play(
                            mAlarmClock.getRingUrl(), true, true);
                } else {
                    AudioPlayer.getInstance(this).play(
                            mAlarmClock.getRingUrl(), true, false);
                }
            }
        } else {
            AudioPlayer.getInstance(this).playRaw(
                    R.raw.ring_weac_alarm_clock_default, true, true);
        }
    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        // 停止运行更新时间的线程
        mIsRun = false;
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        unRegisterPhoneReceiver();
        // 当没有点击按钮，则当前响铃被新闹钟任务杀死，开启小睡
        if (!mIsOnclick) {
            // 小睡
            nap();
        }

        // 当前只有一个Activity
        if (WeacStatus.sActivityNumber <= 1) {
            // 停止播放
            AudioPlayer.getInstance(this).stop();
        }

        // 启动的Activity个数减一
        WeacStatus.sActivityNumber--;



        // 复原手机媒体音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                mCurrentVolume, AudioManager.ADJUST_SAME);
    }

    /**
     * 小睡
     */
    @TargetApi(19)
    private void nap() {
        // 当小睡执行了X次
        if (mNapTimesRan == mNapTimes || mAlarmClock == null) {
            return;
        }
        // 小睡次数加1
        mNapTimesRan++;
        LogUtil.d(LOG_TAG, "已执行小睡次数：" + mNapTimesRan);

        // 设置小睡相关信息
        Intent intent = new Intent(this, AlarmClockBroadcast.class);
        byte[] bytes = Parcelables.toByteArray(mAlarmClock);
        intent.putExtra(WeacConstants.ALARM_CLOCK, bytes);
        intent.putExtra(WeacConstants.NAP_RAN_TIMES, mNapTimesRan);
        PendingIntent pi = PendingIntent.getBroadcast(this,
                -mAlarmClock.getId(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this
                .getSystemService(Activity.ALARM_SERVICE);
        // XXX
        // 下次响铃时间
        long nextTime = System.currentTimeMillis() + 1000 * 60 * mNapInterval;

        LogUtil.i(LOG_TAG, "小睡间隔:" + mNapInterval + "分钟");

        // 当前版本为19（4.4）或以上使用精准闹钟
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
        }

        // 设置通知相关信息
        Intent it = new Intent(this,
                AlarmClockNapNotificationActivity.class);
        byte[] bytess = Parcelables.toByteArray(mAlarmClock);
        intent.putExtra(WeacConstants.ALARM_CLOCK, bytess);
        // FLAG_UPDATE_CURRENT 点击通知有时不会跳转！！
        // FLAG_ONE_SHOT 清除列表只响应一个
        PendingIntent napCancel = PendingIntent.getActivity(this,
                mAlarmClock.getId(), it,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // 下拉列表通知显示的时间
        CharSequence time = new SimpleDateFormat("HH:mm", Locale.getDefault())
                .format(nextTime);

        // 通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 设置PendingIntent
        Notification notification = builder.setContentIntent(napCancel)
                // 当清除下拉列表触发
                .setDeleteIntent(napCancel)
                // 设置下拉列表标题
                .setContentTitle(
                        String.format("稍后在吃",
                                mAlarmClock.getTag()))
                // 设置下拉列表显示内容
                .setContentText(String.format("闹铃再次响起，轻触取消", time))
                // 设置状态栏显示的信息
                .setTicker(
                        String.format("小睡一会",
                                mNapInterval))
                // 设置状态栏（小图标）
                .setSmallIcon(R.drawable.logo)
                // 设置下拉列表（大图标）
                .setLargeIcon(
                        BitmapFactory.decodeResource(getResources(),
                                R.drawable.logo)).setAutoCancel(true)
                // 默认呼吸灯
                .setDefaults(NotificationCompat.DEFAULT_LIGHTS | NotificationCompat.FLAG_SHOW_LIGHTS)
                .build();
/*        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;*/

        // 下拉列表显示小睡信息
        mNotificationManager.notify(mAlarmClock.getId(), notification);
    }
    /**
     * Log tag ：AlarmClockOntimeFragment
     */
    private static final String LOG_TAG = "AlarmClockOntimeFragment";

    /**
     * 当前时间
     */
    private TextView mTimeTv;

    /**
     * 闹钟实例
     */
    private AlarmClock mAlarmClock;

    /**
     * 线程运行flag
     */
    private boolean mIsRun = true;

    /**
     * 线程标记
     */
    private static final int UPDATE_TIME = 1;

    /**
     * 通知消息管理
     */
    private NotificationManagerCompat mNotificationManager;

    /**
     * 小睡间隔
     */
    private int mNapInterval;

    /**
     * 小睡次数
     */
    private int mNapTimes;

    /**
     * 是否点击按钮
     */
    private boolean mIsOnclick = false;

    /**
     * 小睡已执行次数
     */
    private int mNapTimesRan;

    /**
     * 声音管理
     */
    private AudioManager mAudioManager;

    /**
     * 当前音量
     */
    private int mCurrentVolume;



}
