package io.cordova.qianshou.receiver;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import io.cordova.qianshou.mvp.activity.mine.AlarmActivity;

/**
 * Created by Administrator on 2018/4/13.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 获得提示信息
        String msg = intent.getStringExtra("msg");
        if (intent.getAction()!=null&&intent.getAction().equals("com.canplay.alarm.lxm")) {//自定义的action

            Intent i = new Intent();  //自定义打开的界面
        i.putExtra("msg","meiko");
        i.setClass(context, AlarmActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);}
    }


}
