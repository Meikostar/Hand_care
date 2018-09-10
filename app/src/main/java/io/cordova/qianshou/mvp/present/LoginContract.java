package io.cordova.qianshou.mvp.present;

import io.cordova.qianshou.base.BasePresenter;
import io.cordova.qianshou.base.BaseView;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Recovery;
import io.cordova.qianshou.bean.RecoveryPsw;

import io.cordova.qianshou.base.BasePresenter;
import io.cordova.qianshou.base.BaseView;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Recovery;
import io.cordova.qianshou.bean.RecoveryPsw;

public class LoginContract {
    public    interface View extends BaseView {

//        <T> void toList(List<T> list, int type, int... refreshType);
        <T> void toEntity(T entity,int type);

        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {

        /**
         * 获得联系人列表
         */
        void goLogin(String account, String pwd);

        /**
         * 获取token
         */
        void getToken();
        /**
         * 发送验证码
         */
        void getCode(String phone);

        /**
         * 发送验证码(wangjimima)
         */
        void getRecoveryCode(String phone);
        /**
         * 校验验证码
         */
        void checkCode(String phone,String code);

        /**
         * 校验验证码(wangjimima)
         */
        void checkCodeRecovery(Recovery body);


        /**
         * 忘记密码密码
         */

        void recoveryPsw(RecoveryPsw recoveryPsw);
        /**
         * 注册
         */
        void register(String a,String b,String c,String d,String e );

        void changePhone(Phones name);

    }
}
