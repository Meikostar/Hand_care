package io.cordova.qianshou.mvp.present;

import io.cordova.qianshou.base.BasePresenter;
import io.cordova.qianshou.base.BaseView;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.avator;

import io.cordova.qianshou.base.BasePresenter;
import io.cordova.qianshou.base.BaseView;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Pws;

public class OtherContract {
    public    interface View extends BaseView {

//        <T> void toList(List<T> list, int type, int... refreshType);
        <T> void toEntity(T entity, int type);

        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {

        /**
         * 验证药监码并返回药品信息
         */
        void getMedicalInfo(String verifyCode);
        void confirmEat(String reminderTimeId);

        /**
         * 药品名称或取药品信息
         */
        void getMedicalDetail(String verifyCode);
        /**
         * 编辑用户信息
         */
        void editorUser(Editor name);

        /**
         * 编辑用户信息
         */
        void editorPhone(Phone name);

        /**
         * 编辑用户密码
         */
        void editorPsd(Pws name);

        /**
         *
         */
        void getDetails(String type);

        /**
         *修改手机号码
         */
        void changePhone(Phones name);
    }
}
