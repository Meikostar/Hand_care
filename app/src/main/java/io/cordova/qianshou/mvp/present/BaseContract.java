package io.cordova.qianshou.mvp.present;

import io.cordova.qianshou.base.BasePresenter;
import io.cordova.qianshou.base.BaseView;
import io.cordova.qianshou.bean.Add;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.bean.unBind;

import io.cordova.qianshou.base.BasePresenter;
import io.cordova.qianshou.base.BaseView;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.avator;

public class BaseContract {
    public    interface View extends BaseView {

//        <T> void toList(List<T> list, int type, int... refreshType);
        <T> void toEntity(T entity,int type);

        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {
        void getBoxInfo();
        /**
         * 测量记录
         */
        void getMeasureRecord( int type,String b,String c, String d);

        /**
         * 血压测量记录
         */
        void getBloodPressList(int  type,String from, String take,String userId);

        /**
         * 添加提醒
         */
        void addMesure(Mesure base);
        /**
         * 添加血糖记录
         */
        void addBloodSugar(Sug base);

        /**
         * 血糖测量记录
         */
        void getBloodList(final int  type, String from, String take,String userId);

        /**
         * 指定天数血糖测量记录
         */
        void getDayBloodRecord( int day);

        /**
         * 指定天数血糖测量记录
         */
        void addBloodPress(Press base);

        /**
         * 指定天数血压测量记录
         */
        void getDayBloodPressRecord( int day);

        /**
         * 药物列表
         */
        void getMedicineList();

        /**
         * 扫描添加药物
         */
        void getMedicineInfo(String medicindCode);

        void addMedical(Medic med);


        /**
         * 头像
         */
        void upPhotos(avator med);

        /**
         * 添加设备
         */
        void bindDevice(Bind med);

        void Uncertified(Medic med);

        /**
         * 确认服药
         */
        void confirmEat(String reminderTimeId) ;
        /**
         * 智能药盒
         *
         */
        void  myMedicineBox();
        void searchMedicine(String content);
        void addMediacl(AddMedical base);
        /**
         * 时间轴
         *
         */
        void getTimeRecord( int  type,String from, String take);
        /**
         * 编辑用户信息
         */
        void editorUser(Editor name);
        /**
         * 提醒详情
         *
         */
        void getDetail(String id);
    }
}
