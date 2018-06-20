package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;

import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.AddMedical;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.BaseData;
import com.canplay.medical.bean.Bind;
import com.canplay.medical.bean.Box;
import com.canplay.medical.bean.Boxs;
import com.canplay.medical.bean.Detail;
import com.canplay.medical.bean.Editor;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Record;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.Sug;
import com.canplay.medical.bean.Sugar;
import com.canplay.medical.bean.USER;
import com.canplay.medical.bean.avator;
import com.canplay.medical.bean.unBind;
import com.canplay.medical.mvp.http.BaseApi;
import com.canplay.medical.net.MySubscriber;
import com.canplay.medical.util.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

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

                mView.toEntity(entity,1);

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
