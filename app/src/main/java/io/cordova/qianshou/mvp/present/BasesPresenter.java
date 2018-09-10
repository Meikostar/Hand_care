package io.cordova.qianshou.mvp.present;


import android.support.annotation.NonNull;

import io.cordova.qianshou.base.manager.ApiManager;
import io.cordova.qianshou.bean.Add;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.BaseData;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Boxs;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.bean.Righter;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.Sugar;
import io.cordova.qianshou.bean.USER;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.bean.unBind;
import io.cordova.qianshou.mvp.http.BaseApi;
import io.cordova.qianshou.net.MySubscriber;
import io.cordova.qianshou.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.cordova.qianshou.base.manager.ApiManager;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.BaseData;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Boxs;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.Sugar;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.net.MySubscriber;
import io.cordova.qianshou.util.SpUtil;
import rx.Subscription;


public class BasesPresenter implements BaseContract.Presenter {
    private Subscription subscription;

    private BaseContract.View mView;

    private BaseApi contactApi;

    @Inject
    BasesPresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void getMeasureRecord( final int type,String category,String from, String take) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getMeasureRecord(userId,category,from,take), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,type);
            }
        });
    }
    @Override
    public void getTimeRecord(final int  type,String from, String take) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getTimeRecord(userId,from,take), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,type);
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
    public void getDetail(String id) {
        subscription = ApiManager.setSubscribe(contactApi.getDetail(id), new MySubscriber<Detail>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Detail entity){

                mView.toEntity(entity,11);

            }
        });
    }
    @Override
    public void getBloodPressList(final int  type, String from, String take,String userId) {

        subscription = ApiManager.setSubscribe(contactApi.getBloodPressList(userId,from,take), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,type);
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
    public void Uncertified(Medic med) {

        subscription = ApiManager.setSubscribe(contactApi.Uncertified(med), new MySubscriber<Medicines>(){
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
    public void upPhotos(avator med) {

        subscription = ApiManager.setSubscribe(contactApi.upPhotos(med), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(BASE entity){
                if (entity.IsSucceeded){
                    mView.toEntity(entity,1);
                }else {
                    mView.toEntity(entity,0);
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


            }

            @Override
            public void onNext(BASE entity){
                if (entity.isSucceeded){
                    mView.toEntity(entity,1);
                }else {
                    mView.toEntity(entity,0);
                }


            }
        });
    }


    @Override
    public void getMedicineList() {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getMedicalList(userId), new MySubscriber<List<Medicine>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Medicine> entity){

                mView.toEntity(entity,0);
            }
        });
    }
    private List<Medicines> list=new ArrayList<>();

    @Override
    public void getMedicineInfo(String medicindCode) {

        subscription = ApiManager.setSubscribe(contactApi.getMedicineInfo(medicindCode), new MySubscriber<Medicines>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Medicines entity){
                list.clear();
                list.add(entity);
                mView.toEntity(list,0);
            }
        });
    }

    @Override
    public void getBloodList(final int  type, String from, String take,String userId) {

        subscription = ApiManager.setSubscribe(contactApi.getBloodList(userId,from,take), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,type);
            }
        });
    }
    @Override
    public void getDayBloodRecord( final int day) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getDayBloodRecord(userId,day+""), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,day);
            }
        });
    }

    @Override
    public void getDayBloodPressRecord( final int day) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getDayBloodPressRecord(userId,day+""), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,day);
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

                mView.toEntity(entity,3);
            }
        });
    }
    @Override
    public void addMediacl(AddMedical base) {

        subscription = ApiManager.setSubscribe(contactApi.addMedical(base), new MySubscriber<BaseData>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                mView.showTomast(e.getMessage());
            }

            @Override
            public void onNext(BaseData entity){

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
    public void myMedicineBox() {
        String id=SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.myMedicineBox(id), new MySubscriber<Boxs>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Boxs entity){

                mView.toEntity(entity,1);
            }
        });

    }
    @Override
    public void getBoxInfo() {
        String id=SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getBoxInfo(id), new MySubscriber<Box>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Box entity){

                mView.toEntity(entity,1);
            }
        });

    }

    @Override
    public void addBloodPress(Press base) {

        subscription = ApiManager.setSubscribe(contactApi.addBloodPress(base), new MySubscriber<Sugar>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Sugar entity){

                mView.toEntity(entity,2);
            }
        });
    }
    @Override
    public void addBloodSugar(Sug base) {

        subscription = ApiManager.setSubscribe(contactApi.addBloodSugar(base), new MySubscriber<Sugar>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Sugar entity){

                mView.toEntity(entity,1);
            }
        });
    }

    @Override
    public void attachView(@NonNull BaseContract.View view){
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
