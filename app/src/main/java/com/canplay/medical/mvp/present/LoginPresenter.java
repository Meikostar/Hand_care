package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;


import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.BaseResult;
import com.canplay.medical.bean.Phones;
import com.canplay.medical.bean.Recovery;
import com.canplay.medical.bean.RecoveryPsw;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.USER;
import com.canplay.medical.mvp.http.BaseApi;

import com.canplay.medical.net.MySubscriber;
import com.canplay.medical.util.SpUtil;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Subscription;


public class LoginPresenter implements LoginContract.Presenter {
    private Subscription subscription;

    private LoginContract.View mView;

    private BaseApi contactApi;

    @Inject
    LoginPresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void goLogin(String account, String pwd) {
        Map<String, String> params = new TreeMap<>();
        params.put("grant_type", "password");
        params.put("username", account);
        params.put("password", pwd);
        String content = params.toString();
        subscription = ApiManager.setSubscribe(contactApi.Login(params), new MySubscriber<USER>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                    mView.showTomast("账号或密码错误");


            }

            @Override
            public void onNext(USER entity){
                SpUtil.getInstance().putUser(entity);
                mView.toEntity(entity,0);
            }
        });
    }
    @Override
    public void getToken() {

    }
    public void changePhone(Phones name){
        subscription = ApiManager.setSubscribe(contactApi.changePhone(name), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,66);
            }
        });
    }
    @Override
    public void getRecoveryCode(String phone) {
        subscription = ApiManager.setSubscribe(contactApi.getRecoveryCode(phone), new MySubscriber<BaseResult>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                    mView.showTomast(e.getMessage());


            }

            @Override
            public void onNext(BaseResult entity){
                if(entity.isSucceeded){
                    mView.toEntity(entity,0);
                }else {
                    mView.showTomast(entity.message);
                }


            }
        });
    }


    @Override
    public void checkCodeRecovery(Recovery body) {

        subscription = ApiManager.setSubscribe(contactApi.checkCodeRecovery(body), new MySubscriber<BaseResult>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.toNextStep(0);
                mView.showTomast("验证码错误");

            }

            @Override
            public void onNext(BaseResult entity){

                if(entity.isSucceeded){
                    mView.toEntity(entity,1);
                }else {
                    mView.showTomast(entity.message);
                }

            }
        });
    }
    @Override
    public void getCode(String phone) {
        subscription = ApiManager.setSubscribe(contactApi.getCode(phone), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(BASE entity){
                if(entity.isSucceeded){
                    mView.toEntity(entity,0);
                }else {
                    mView.showTomast(entity.message);
                }


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
               if(entity.isVerfied){
                   mView.toNextStep(1);
               }else {
                   mView.showTomast("验证码错误或失效");
               }


            }
        });
    }
    @Override
    public void register(String name,String birth,String email ,String pwd,String phone) {

        Righter righter = new Righter();
        righter.DisplayName=name;
        righter.dob=birth;
        righter.confirmPassword=pwd;
        righter.email="";
        righter.password=pwd;
        righter.mobile=phone;

        subscription = ApiManager.setSubscribe(contactApi.righter(righter), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                mView.showTomast(e.getMessage());

            }

            @Override
            public void onNext(BASE entity){
                if(entity.isSucceeded){
                    mView.toNextStep(2);
                }else {
                    mView.showTomast(entity.message);
                }


            }
        });
    }

    @Override
    public void recoveryPsw(RecoveryPsw recoveryPsw) {



        subscription = ApiManager.setSubscribe(contactApi.recoveryPsw(recoveryPsw), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.toNextStep(0);
                mView.showTomast("稍后再试");

            }

            @Override
            public void onNext(BASE entity){
                if(entity.isSucceeded){
                    mView.toNextStep(2);
                }else {
                    mView.showTomast(entity.message);
                }


            }
        });
    }
    @Override
    public void attachView(@NonNull LoginContract.View view){
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
