package io.cordova.qianshou.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.util.LogUtils;

public class PhoneReceiver extends BroadcastReceiver {

    private OnPhoneListener onPhoneListener;

    public PhoneReceiver() {
    }


    public PhoneReceiver(OnPhoneListener onPhoneListener) {
        this.onPhoneListener = onPhoneListener;
    }

    /**
     * @param context The Context in which the receiver is running.
     * @param intent  The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
            //电话监听
            if (onPhoneListener != null)
                onPhoneListener.onPhoneResume();

        } else {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    public interface OnPhoneListener {
        void onPhoneResume();

        void onPhoneIdle();
    }


    PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //方法必须写在super方法后面，否则incomingNumber无法获取到值。
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    BaseApplication.phoneState=0;
                    LogUtils.i("电话挂断了");
                    if (onPhoneListener != null)
                        onPhoneListener.onPhoneIdle();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    LogUtils.i("电话接听");
                    BaseApplication.phoneState=1;
                    RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.PHONE_STATE,""));
                    if (onPhoneListener != null)
                        onPhoneListener.onPhoneIdle();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    //输出来电号码
                    BaseApplication.phoneState=2;
                    RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.PHONE_STATE,""));
                    LogUtils.i("电话响铃:来电号码" + incomingNumber);
                    if (onPhoneListener != null)
                        onPhoneListener.onPhoneResume();
                    break;
            }
        }
    };


}