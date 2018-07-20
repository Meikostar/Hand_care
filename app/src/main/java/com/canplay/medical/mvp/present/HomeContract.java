package com.canplay.medical.mvp.present;

import com.canplay.medical.base.BasePresenter;
import com.canplay.medical.base.BaseView;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.Bind;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.unBind;

public class HomeContract {
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
        void getUserData(int type);

        /**
         * 获取消息列表
         */
        void getMessageList();

        /**
         * 获取消息未读数
         */
        void getMessageCout();

        /**
         * 校验验证码
         */
        void checkCode(String phone, String code);

        /**
         * 获取用药提醒
         */
        void MedicineRemindList();

        /**
         * 获取测量提醒
         */
        void MeasureRemindList();

        /**
         * 好友列表
         */
        void getFriendList();

        /**
         * 好友列表
         */
        void SearFriend(String userId);

        /**
         * 好友列表
         */
        void getFriendInfo(String userId);

        /**
         * 医生列表
         */
        void getDoctorList();

        /**
         * 医生列表
         */
        void searchDoctor(String userId);

        /**
         * 医生详情
         */
        void getDoctorInfo(String userId);

        /**
         * 添加医生
         */
        void AddDoctor(String userId);

        /**
         * 智能设备列表
         */
        void getSmartList();
        /**
         * 添加pengyou
         */
        void addFriend(Add base);

        /**
         * 同意
         */
        void agree(String id);

        /**
         * 同意
         */
        void disAgree(String id);

        /**
         * search
         */
        void searchMedicine(String content);

        void addMedical(Medic med);
        /**
         * 添加设备
         */
        void bindDevice(Bind med);

        /**
         * 移除设备
         */
        void UnbindDevice(unBind med);

        /**
         * 移除用药提醒
         */
        void removeRemind(String base);


        /**
         * 获取某一管理计划
         */
        void MeasureRemindDetail(String name);

        void  addMesure(Mesure base);
        /**
         * 移除家庭医生
         */
        void DelDoctor(String content);


        void getHealthData();
        void confirmEat(String reminderTimeId);
        void myMedicineBox();
        void getDetails(int type);
        void getDetail(String id);
    }


}
