package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;

import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Bind;
import com.canplay.medical.bean.Boxs;
import com.canplay.medical.bean.Euipt;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.bean.Health;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.Medil;
import com.canplay.medical.bean.Message;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.USER;
import com.canplay.medical.bean.unBind;
import com.canplay.medical.mvp.http.BaseApi;
import com.canplay.medical.net.MySubscriber;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Subscription;


public class HomePresenter implements HomeContract.Presenter {
    private Subscription subscription;

    private HomeContract.View mView;

    private BaseApi contactApi;

    @Inject
    HomePresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }

    @Override
    public void getDetails(final int type) {
        String content;
        if(type==1){
            content="Medicine";
        }else {
            content="Measurement";
        }
        subscription = ApiManager.setSubscribe(contactApi.getDetails(content), new MySubscriber<Medil>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Medil entity){

                mView.toEntity(entity.object,type);
            }
        });
    }

    @Override
    public void getUserData(final int type) {//1 用药提醒2 代表测量提醒
        String content;
        if(type==1){
            content="Medicine";
        }else {
            content="Measurement";
        }
        subscription = ApiManager.setSubscribe(contactApi.getUserdata(content), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
//                if(e.toString().contains("java.io.IOException:")){
//                    mView.showTomast("账号或密码错误");
//                }

            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,type);

            }
        });
    }

    @Override
    public void addMesure(Mesure base) {

        subscription = ApiManager.setSubscribe(contactApi.addMesure(base), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                mView.showTomast("添加失败");
            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,6);
            }
        });
    }
    @Override
    public void getMessageList() {
        subscription = ApiManager.setSubscribe(contactApi.getMessageList(), new MySubscriber<List<Message>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                    mView.showTomast(e.getMessage());


            }

            @Override
            public void onNext(List<Message> entity){
                mView.toEntity(entity,0);

            }
        });
    }
    @Override
    public void confirmEat(String reminderTimeId) {

        subscription = ApiManager.setSubscribe(contactApi.confirmEat(reminderTimeId), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(BASE entity){

                mView.toNextStep(2);
            }
        });
    }
    @Override
    public void removeRemind(String base) {

        subscription = ApiManager.setSubscribe(contactApi.removeRemind(base), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                mView.showTomast("添加失败");
            }

            @Override
            public void onNext(BASE entity){
                if(entity.isSucceeded){
                    mView.toNextStep(1);
                }else {
                    mView.showTomast(entity.message);
                }

            }
        });
    }
    @Override
    public void UnbindDevice(unBind med) {

        subscription = ApiManager.setSubscribe(contactApi.UnbindDevice(med), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
               mView.showTomast("移除设备失败");

            }

            @Override
            public void onNext(BASE entity){
                if (entity.isSucceeded){
                    mView.toNextStep(2);
                }else {
                    mView.showTomast(entity.message);
                }


            }
        });
    }
    @Override
    public void bindDevice(Bind med) {

        subscription = ApiManager.setSubscribe(contactApi.bindDevice(med), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast("绑定设备失败");
            }

            @Override
            public void onNext(BASE entity){
                if (entity.isSucceeded){
                    entity.type=3+"";
                    mView.toNextStep(1);
                }else {
                    mView.showTomast(entity.message);
                }

            }
        });
    }
    @Override
    public void MedicineRemindList() {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.MedicineRemindList(userId), new MySubscriber<Medicine>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                    mView.showTomast(e.getMessage());


            }

            @Override
            public void onNext(Medicine entity){
                if(entity!=null){
                    mView.toEntity(entity.schedule,0);

                }else {
                    List<Medicine> list=new ArrayList<Medicine>();
                    mView.toEntity(list,0);

                }

            }
        });
    }
    @Override
    public void MeasureRemindList() {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.MeasureRemindList(userId), new MySubscriber<Medicine>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                    mView.showTomast(e.getMessage());


            }

            @Override
            public void onNext(Medicine entity){
                if(entity!=null){
                    mView.toEntity(entity.schedule,66);

                }else {
                    List<Medicine> list=new ArrayList<Medicine>();
                    mView.toEntity(list,66);

                }
//                mView.toEntity(entity.schedule,66);

            }
        });
    }
    @Override
    public void MeasureRemindDetail(String name) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.MeasureRemindDetail(userId,name), new MySubscriber<List<Medicine>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                if(e.toString().contains("java.io.IOException:")){
                    mView.showTomast("账号或密码错误");
                }

            }

            @Override
            public void onNext(List<Medicine> entity){
                if(entity!=null){
                    mView.toEntity(entity.get(0).schedule,0);

                }else {
                    List<Medicine> list=new ArrayList<Medicine>();
                    mView.toEntity(list,0);

                }
//                mView.toEntity(entity.get(0).schedule,0);

            }
        });
    }

    @Override
    public void getMessageCout() {
        subscription = ApiManager.setSubscribe(contactApi.getMessageCout(), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                if(e.toString().contains("java.io.IOException:")){
//                    mView.showTomast("账号或密码错误");
                }

            }

            @Override
            public void onNext(BASE entity){
                entity.type=3+"";
                mView.toEntity(entity,0);

            }
        });
    }


    @Override
    public void checkCode(String code,String phone) {
        subscription = ApiManager.setSubscribe(contactApi.checkCode(phone,code), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.toNextStep(0);
                mView.showTomast("验证码错误");

            }

            @Override
            public void onNext(BASE entity){
                mView.toNextStep(1);

            }
        });
    }
    @Override
    public void getFriendList() {
        subscription = ApiManager.setSubscribe(contactApi.getFriendList(), new MySubscriber<List<Friend>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Friend> entity){
                mView.toEntity(entity,0);

            }
        });
    }

    @Override
    public void getFriendInfo(String userId) {
        subscription = ApiManager.setSubscribe(contactApi.getFriendInfo(userId), new MySubscriber<Friend>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext( Friend entity){

                mView.toEntity(entity,1);

            }
        });
    }

    @Override
    public void getDoctorList() {
        subscription = ApiManager.setSubscribe(contactApi.getDoctorList(), new MySubscriber<List<Friend>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Friend> entity){
                mView.toEntity(entity,0);

            }
        });
    }
    @Override
    public void myMedicineBox() {
        String id=SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.myMedicineBox(id), new MySubscriber<Boxs>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Boxs entity){

                mView.toEntity(entity,9);
            }
        });

    }
    @Override
    public void getSmartList() {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getSmartList(userId), new MySubscriber<List<Euipt>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
              mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext(List<Euipt> entity){

                mView.toEntity(entity,0);

            }
        });
    }

    @Override
    public void getHealthData() {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getHealthData(userId), new MySubscriber<Health>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Health entity){

                mView.toEntity(entity,0);

            }
        });
    }



    @Override
    public void addFriend(Add base) {

        subscription = ApiManager.setSubscribe(contactApi.addFriend(base), new MySubscriber<List<BASE>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<BASE> entity){

                mView.toNextStep(6);
            }
        });
    }

    @Override
    public void agree(String id) {
        subscription = ApiManager.setSubscribe(contactApi.agree(id), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext(String entity){
                if(TextUtil.isNotEmpty(entity)){
                    if(entity.equals("true")){
                        mView.toNextStep(4);
                    }else {
                        mView.showTomast("操作失败");
                    }
                }else {
                    mView.showTomast("操作失败");

                }


            }
        });
    }

    @Override
    public void disAgree(String id) {
        subscription = ApiManager.setSubscribe(contactApi.dissAgree(id), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(BASE entity){

                mView.toNextStep(8);
            }
        });
    }

    @Override
    public void AddDoctor(String content) {
        subscription = ApiManager.setSubscribe(contactApi.AddDoctor(content), new MySubscriber<Friend>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext(Friend entity){


                mView.toNextStep(7);

            }
        });
    }
    @Override
    public void DelDoctor(String content) {
        subscription = ApiManager.setSubscribe(contactApi.DelDoctor(content), new MySubscriber<Friend>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Friend entity){


                mView.toNextStep(2);

            }
        });
    }
    @Override
    public void getDoctorInfo(String content) {
        subscription = ApiManager.setSubscribe(contactApi.getDoctorInfo(content), new MySubscriber<Friend>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
              mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext(Friend entity){

                mView.toEntity(entity,6);

            }
        });
    }
    @Override
    public void searchDoctor(String content) {
        subscription = ApiManager.setSubscribe(contactApi.searchDoctor(content), new MySubscriber<List<Friend>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext(List<Friend> entity){
                if(entity.size()>0&&entity.get(0)!=null){
                    entity.get(0).type=2;
                }
                mView.toEntity(entity,0);

            }
        });
    }
    @Override
    public void searchMedicine(String content) {
        subscription = ApiManager.setSubscribe(contactApi.searchMedicine(content), new MySubscriber<List<Medicines>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Medicines> entity){

                mView.toEntity(entity,0);

            }
        });
    }
    @Override
    public void addMedical(Medic med) {
        subscription = ApiManager.setSubscribe(contactApi.addMedical(med), new MySubscriber<Medicines>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Medicines entity){

                mView.toEntity(entity,0);

            }
        });
    }

    @Override
    public void SearFriend(String content) {
        subscription = ApiManager.setSubscribe(contactApi.searchFriend(content), new MySubscriber<List<Friend>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Friend> entity){
                mView.toEntity(entity,0);

            }
        });
    }
    @Override
    public void attachView(@NonNull HomeContract.View view){
        mView = view;
    }


    @Override
    public void detachView(){
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        mView = null;
    }
}
