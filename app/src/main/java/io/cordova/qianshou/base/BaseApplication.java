package io.cordova.qianshou.base;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.multidex.MultiDex;


import io.cordova.qianshou.R;
import io.cordova.qianshou.base.DaggerAppComponent;
import io.cordova.qianshou.base.manager.AppManager;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.location.LocationUtil;
import io.cordova.qianshou.receiver.Receiver1;
import io.cordova.qianshou.receiver.Receiver2;
import io.cordova.qianshou.receiver.Service1;
import io.cordova.qianshou.receiver.Service2;
import io.cordova.qianshou.util.ExceptionHandler;
import io.cordova.qianshou.util.JPushUtils;
import com.google.zxing.client.android.decode.WeacConstants;
import com.marswin89.marsdaemon.DaemonApplication;
import com.marswin89.marsdaemon.DaemonConfigurations;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import io.cordova.qianshou.base.manager.AppManager;
import io.cordova.qianshou.bean.AlarmClock;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.location.LocationUtil;
import io.cordova.qianshou.receiver.Receiver1;
import io.cordova.qianshou.receiver.Receiver2;
import io.cordova.qianshou.receiver.Service1;
import io.cordova.qianshou.receiver.Service2;
import io.cordova.qianshou.util.ExceptionHandler;
import io.cordova.qianshou.util.JPushUtils;
import io.valuesfeng.picker.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import io.valuesfeng.picker.universalimageloader.core.ImageLoader;
import io.valuesfeng.picker.universalimageloader.core.ImageLoaderConfiguration;
import io.valuesfeng.picker.universalimageloader.core.assist.QueueProcessingType;




/**
 * App基类
 * Created by peter on 2016/9/11.
 */

public class BaseApplication extends DaemonApplication  {
    //全局单例
    AppComponent mAppComponent;
    public static  BaseApplication cplayApplication;
    public static  String avatar="http://qsgx-pt.com:8091/Flow/avatar/";
    public static  String phone="";
    public static  long time1=0;
    public static  long time2=0;
    public static  boolean isOwn;
    public static  long phoneState=0;//1通话中，2 响铃 0，挂断和空闲
    public AlarmClock mAlarmClock;
    public static String times;
    public static Map<String,String> map=new HashMap<>();
    public static BaseApplication getInstance() {
        if (cplayApplication == null) {
            cplayApplication = new BaseApplication();
        }
        return  cplayApplication;
    }
    public static Province province;
    public LocationUtil locationUtil;
    public  Vibrator mVibrator;
    @Override
    public void onCreate(){
        // TODO Auto-generated method stub
        super.onCreate();
        //生成全局单例
        cplayApplication = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);
        ApplicationConfig.setAppInfo(this);
        /**
         * 初始化百度地图
         */
        LocationUtil.initUtil(this);
        locationUtil = LocationUtil.shareInstance();
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        unSppuortSystemFont();
        //全局异常处理
        initImageLoader(this);
        new ExceptionHandler().init(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        String androidId = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        JPushUtils.shareInstance().setAlias(androidId,11);
//        JPushInterface.setLatestNotificationNumber(this, 1);
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
    }



    public void unSppuortSystemFont() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    /**
     * you can override this method instead of {@link android.app.Application attachBaseContext}
     * @param base
     */
    @Override
    public void attachBaseContextByDaemon(Context base) {
        super.attachBaseContextByDaemon(base);
    }


    /**
     * give the configuration to lib in this callback
     * @return
     */
    @Override
    protected DaemonConfigurations getDaemonConfigurations() {
        DaemonConfigurations.DaemonConfiguration configuration1 = new DaemonConfigurations.DaemonConfiguration(
                "com.marswin89.marsdaemon.demo:process1",
                Service1.class.getCanonicalName(),
                Receiver1.class.getCanonicalName());

        DaemonConfigurations.DaemonConfiguration configuration2 = new DaemonConfigurations.DaemonConfiguration(
                "com.marswin89.marsdaemon.demo:process2",
                Service2.class.getCanonicalName(),
                Receiver2.class.getCanonicalName());

        DaemonConfigurations.DaemonListener listener = new MyDaemonListener();
        //return new DaemonConfigurations(configuration1, configuration2);//listener can be null
        return new DaemonConfigurations(configuration1, configuration2, listener);
    }


    class MyDaemonListener implements DaemonConfigurations.DaemonListener{
        @Override
        public void onPersistentStart(Context context) {
        }

        @Override
        public void onDaemonAssistantStart(Context context) {
        }

        @Override
        public void onWatchDaemonDaed() {
        }
    }

    /**
     * 退出应用
     */
    public void exit(){
        AppManager.getInstance(this).exitAPP(this);
    }

    @Override
    public void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
//          ImageLoaderConfiguration.createDefault(this);
        // method.
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.memoryCacheSize(cacheSize);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 10 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
//        config.memoryCache(new WeakMemoryCache()).threadPoolSize(1);
        config.memoryCacheExtraOptions(480, 800);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

    }
    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
