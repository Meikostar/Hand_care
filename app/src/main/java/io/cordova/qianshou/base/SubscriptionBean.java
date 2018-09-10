package io.cordova.qianshou.base;

/***
 * 功能描述:RxBus消息订阅
 * 作者:chenwei
 * 时间:2016/12/15
 * 版本:1.0
 ***/

public class SubscriptionBean {
    public static int TEST=98;
    public static int ERROR_401=100;
    public static int RE_LOGIN=99;
    public static int NOFIFY=101;

    public static int MENU_REFASHS=102;

    public static int MENU_SCAN=103;//扫一扫
    public static int MESURE=104;//测量
    public static int MESURES=1011;//测量
    public static int FINISH=105;//
    public static int CHOOSMEDICAL=106;//选择药
    public static int CHOOSMEDICALSS=1066;//选择药
    public static int MEDICALREFASH=107;//药物提醒列表刷新
    public static int MESUREREFASH=1008;//药物提醒列表刷新
    public static int CLOSE=108;//药物提醒列表刷新
    public static int ALARM=109;//闹钟
    public static int ALARM_EDITOR=110;//闹钟
    public static int EDITOR=111;//编辑用户信息
    public static int BLOODORSUGAR=112;//编辑用户信息
    public static int DOCTOR=113;//刷新医生列表
    public static int EUIP_REFASH=114;//刷新医生列表
    public static int PHONE_STATE=115;//刷新医生列表



    /**
     * 重新登入
     */

    public static <T>RxBusSendBean createSendBean(int type,T t){
        RxBusSendBean<T> busSendBean = new RxBusSendBean();
        busSendBean.type = type;
        busSendBean.content = t;
        return busSendBean;
    }

    public static class RxBusSendBean<T>{
        public int type;
        public T content;
    }

}
