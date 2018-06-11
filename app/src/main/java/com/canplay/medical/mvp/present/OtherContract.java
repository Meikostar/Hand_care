package com.canplay.medical.mvp.present;

import com.canplay.medical.base.BasePresenter;
import com.canplay.medical.base.BaseView;
import com.canplay.medical.bean.AddMedical;
import com.canplay.medical.bean.Bind;
import com.canplay.medical.bean.Editor;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.Phone;
import com.canplay.medical.bean.Phones;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Pws;
import com.canplay.medical.bean.Sug;
import com.canplay.medical.bean.avator;

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
