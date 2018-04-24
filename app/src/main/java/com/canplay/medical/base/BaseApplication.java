package com.canplay.medical.base;

import android.app.Application;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Vibrator;
import android.support.multidex.MultiDex;

import com.canplay.medical.base.manager.AppManager;
import com.canplay.medical.bean.Province;
import com.canplay.medical.location.LocationUtil;
import com.canplay.medical.receiver.Receiver1;
import com.canplay.medical.receiver.Receiver2;
import com.canplay.medical.receiver.Service1;
import com.canplay.medical.receiver.Service2;
import com.canplay.medical.util.ExceptionHandler;
import com.canplay.medical.util.JPushUtils;
import com.marswin89.marsdaemon.DaemonApplication;
import com.marswin89.marsdaemon.DaemonConfigurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import io.valuesfeng.picker.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import io.valuesfeng.picker.universalimageloader.core.ImageLoader;
import io.valuesfeng.picker.universalimageloader.core.ImageLoaderConfiguration;
import io.valuesfeng.picker.universalimageloader.core.assist.QueueProcessingType;




/**
 * App基类
 * Created by peter on 2016/9/11.
 */

public class BaseApplication extends DaemonApplication {
    //全局单例
    AppComponent mAppComponent;
    public static  BaseApplication cplayApplication;
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

        //全局异常处理
        initImageLoader(this);
        new ExceptionHandler().init(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        String androidId = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        JPushUtils.shareInstance().setAlias(androidId,11);
//        JPushInterface.setLatestNotificationNumber(this, 1);

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
