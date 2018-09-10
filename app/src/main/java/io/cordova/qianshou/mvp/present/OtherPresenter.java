package io.cordova.qianshou.mvp.present;


import android.support.annotation.NonNull;

import io.cordova.qianshou.base.manager.ApiManager;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.BaseData;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.Sugar;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.mvp.http.BaseApi;
import io.cordova.qianshou.net.MySubscriber;
import io.cordova.qianshou.util.SpUtil;

import java.util.List;

import javax.inject.Inject;

import io.cordova.qianshou.base.manager.ApiManager;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.net.MySubscriber;
import rx.Subscription;


public class OtherPresenter implements OtherContract.Presenter {
    private Subscription subscription;

    private OtherContract.View mView;

    private BaseApi contactApi;

    @Inject
    OtherPresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void getMedicalInfo(String verifyCode) {

        subscription = ApiManager.setSubscribe(contactApi.getMedicalInfo(verifyCode), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(List<Record> entity){

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
    public void getDetails(String type) {

        subscription = ApiManager.setSubscribe(contactApi.getDetails(type), new MySubscriber<Medil>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Medil entity){

                mView.toEntity(entity.object,0);
            }
        });
    }



    @Override
    public void editorPhone(Phone name){
        subscription = ApiManager.setSubscribe(contactApi.editorPhone(name), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,0);
            }
        });
    }

    @Override
    public void changePhone(Phones name){
        subscription = ApiManager.setSubscribe(contactApi.changePhone(name), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,0);
            }
        });
    }

    @Override
    public void editorPsd(Pws name){
        subscription = ApiManager.setSubscribe(contactApi.editorPsw(name), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
               mView.showTomast(e.getMessage());



            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,0);
            }
        });
    }
    @Override
    public void getMedicalDetail(String name) {
        subscription = ApiManager.setSubscribe(contactApi.getMedicalDetail(name), new MySubscriber<Medicines>(){
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
    public void editorUser(Editor name) {
        subscription = ApiManager.setSubscribe(contactApi.editorUser(name), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,0);
            }
        });
    }

    @Override
    public void attachView(@NonNull OtherContract.View view){
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
