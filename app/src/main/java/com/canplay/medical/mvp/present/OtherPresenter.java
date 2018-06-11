package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;

import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.AddMedical;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.BaseData;
import com.canplay.medical.bean.Bind;
import com.canplay.medical.bean.Box;
import com.canplay.medical.bean.Editor;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.Medil;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.Phone;
import com.canplay.medical.bean.Phones;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Pws;
import com.canplay.medical.bean.Record;
import com.canplay.medical.bean.Sug;
import com.canplay.medical.bean.Sugar;
import com.canplay.medical.bean.avator;
import com.canplay.medical.mvp.http.BaseApi;
import com.canplay.medical.net.MySubscriber;
import com.canplay.medical.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
