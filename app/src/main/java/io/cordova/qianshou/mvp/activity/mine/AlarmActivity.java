package io.cordova.qianshou.mvp.activity.mine;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.WeacConstants;
import io.cordova.qianshou.bean.WeacStatus;
import io.cordova.qianshou.mvp.activity.AlarmClockNapNotificationActivity;
import io.cordova.qianshou.mvp.activity.MainActivity;
import io.cordova.qianshou.mvp.activity.RemindFirstDetailActivity;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.receiver.AlarmClockBroadcast;

import io.cordova.qianshou.receiver.PhoneReceiver;
import io.cordova.qianshou.util.AudioPlayer;
import io.cordova.qianshou.util.Parcelables;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import com.google.zxing.client.android.utils.LogUtil;

import java.text.SimpleDateFormat;
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
import io.cordova.qianshou.bean.WeacConstants;
import io.cordova.qianshou.bean.WeacStatus;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.receiver.AlarmClockBroadcast;
import io.cordova.qianshou.util.AudioPlayer;
import io.cordova.qianshou.util.Parcelables;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TimeUtil;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 闹铃
 */
public class AlarmActivity extends BaseActivity implements BaseContract.View {
    @Inject
    BasesPresenter presenter;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_reminds)
    TextView tv_reminds;

    private int sex = 0;
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
    private AlarmClock mAlarmClock = null;

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


    private byte[] bytes;


    private int hour;
    private int minture;
    private int type;

    @Override
    public void initViews() {
        setContentView(R.layout.alarm_pop);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        // 启动的Activity个数加1
        WeacStatus.sActivityNumber++;

//        registerPhoneReceiver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 画面出现在解锁屏幕上,显示,常亮
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent1 = this.getIntent();
        bytes = intent1.getByteArrayExtra("alarm_clock");
        AlarmClock alarmClockss = Parcelables.toParcelable(bytes, AlarmClock.CREATOR);

        if (BaseApplication.phoneState != 0) {
            finish();
        }
        String s = "";
        if (alarmClockss != null) {
            mAlarmClock=alarmClockss;
        } else {
            List<AlarmClock> alarmClocks = SpUtil.getInstance().getAllAlarm();
            s = TimeUtil.formatHour(System.currentTimeMillis());
                String[] split = s.split(":");
                hour = Integer.valueOf(split[0]);
                minture = Integer.valueOf(split[1]);

            for (AlarmClock alarmClock : alarmClocks) {
                if (alarmClock.getHour() == hour && minture == alarmClock.getMinute()) {
                    mAlarmClock = alarmClock;
                }
            }
            if (mAlarmClock == null) {
                for (int i = 0; i < 105; i++) {
                    if (minture > 0) {
                        minture = minture - 1;
                    } else {
                        minture = 59;
                        if (hour > 1) {
                            hour = hour - 1;
                        } else {
                            hour = 23;
                        }

                    }

                    for (AlarmClock alarmClock : alarmClocks) {

                        if (alarmClock.getHour() == hour && minture == alarmClock.getMinute()) {
                            mAlarmClock = alarmClock;
                        }
                    }
                }
            }

        }



            if (mAlarmClock != null) {
                String tag = mAlarmClock.getTag();
                String[] splits = tag.split(":");
                if (splits != null && splits.length != 1) {
                    if (Integer.valueOf(splits[0]) == 1) {
                        if (splits.length == 3) {
                            if (splits[2].equals("血压")) {
                                img.setImageResource(R.drawable.cyc2);
                                type = 1;
                            } else {
                                img.setImageResource(R.drawable.cyc3);
                                type = 2;
                            }
                            tvContent.setText("快去测量您的" + splits[2]);
                            tv_reminds.setText(splits[2] + "测量");
                        }

                    } else {
                        Intent intent = new Intent(this, RemindFirstDetailActivity.class);
                        intent.putExtra("data", mAlarmClock);
                        startActivity(intent);
                        finish();
                    }
                }


            }


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

        countDownTimer = new CountDownTimer(1000 * 30, 30000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();


            }
        }.start();

    }

    private CountDownTimer countDownTimer;


    @Override
    public <T> void toEntity(T entity, int type) {
        finishActivitys();
    }

    @Override
    public void bindEvents() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlayer.getInstance(AlarmActivity.this).stop();
                RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.FINISH, ""));
                RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE, ""));
                Intent intent = new Intent(AlarmActivity.this, LoginActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
                finishActivitys();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlayer.getInstance(AlarmActivity.this).stop();
                RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.FINISH, ""));
                RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.MESURE, ""));
                Intent intent = new Intent(AlarmActivity.this, LoginActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
                finishActivitys();

            }
        });
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if(SubscriptionBean.PHONE_STATE==bean.type){
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
    }
     private Subscription mSubscription;

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 执行结束当前Activity操作
     */
    private void finishActivitys() {
        // 点击按钮标记
        mIsOnclick = true;

        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void initData() {

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
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
//        unRegisterPhoneReceiver();
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


        if (mAudioManager != null) {
            // 复原手机媒体音量
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                    mCurrentVolume, AudioManager.ADJUST_SAME);
        }

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


    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
