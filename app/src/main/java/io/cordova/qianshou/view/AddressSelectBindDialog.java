package io.cordova.qianshou.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;


import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.City;
import io.cordova.qianshou.bean.Province;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/29
 * 版本:
 ***/
public class AddressSelectBindDialog {
    private Context mContext;
    private View mView;
    private PopupWindow mPopupWindow;
    private ViewGroup mViewGroup;
    private FrameLayout mFrameLayout;

    private Button mButtonCancel;
    private Button mButtonConfirm;
    private long id;
    private CycleWheelView mCycleWheelViewProvince;
    private CycleWheelView mCycleWheelViewCity;


    /**
     * 所有省
     */
    private List<String> mProvinceDatas = new ArrayList<>();
    /**
     * key - 省 value - 市
     */
    private Map<String, List<String>> mCitisDatasMap = new HashMap<String, List<String>>();
    /**
     * key - 市 values - 区
     */


    /**
     * key - 区 values - 邮编
     */
    private Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    private String mCurrentProviceName;
    private int mCurrentProviceIndex;
    /**
     * 当前市的名称
     */
    private String mCurrentCityName;
    private int mCurrentCityIndex;
    /**
     * 当前区的名称
     */

    private int mCurrentDistrictIndex;

    /**
     * 当前区的邮政编码
     */
    private String mCurrentZipCode ="";

    private BindClickListener mBindClickListener;
    private boolean haveSetCity = false;
    private boolean haveSetDistrict = false;

    public AddressSelectBindDialog(Context mContext, String s, String c) {

        mCurrentProviceName = s;
        mCurrentCityName = c;

        this.mContext = mContext;
        initView();
    }

    public void setBindClickListener(BindClickListener mBindClickListener){
        this.mBindClickListener=mBindClickListener;
    }
    public interface BindClickListener{
        void site(String province, String city);
    }

    private void initView(){

        mView = View.inflate(mContext, R.layout.view_address_new_store_dialog,null);
        mViewGroup = (ViewGroup) ((Activity)mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        mCycleWheelViewProvince = (CycleWheelView)mView.findViewById(R.id.cwv_province);
        mCycleWheelViewCity = (CycleWheelView)mView.findViewById(R.id.cwv_city);

        mButtonCancel = (Button)mView.findViewById(R.id.but_cancel);
        mButtonConfirm = (Button)mView.findViewById(R.id.but_confirm);
        mFrameLayout = (FrameLayout)mView.findViewById(R.id.fl_rsbd_region);
        initNetDatas();


        mButtonCancel.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {//确定
            @Override
            public void onClick(View v) {
                dismiss();
                if(mBindClickListener!=null){
                    mBindClickListener.site(mCycleWheelViewProvince.getSelectLabel(),mCycleWheelViewCity.getSelectLabel());
                }
            }
        });
        mFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 省份
     */
    private void setProvince(){
        mCycleWheelViewProvince.setLabels(mProvinceDatas,null);
        try {
            mCycleWheelViewProvince.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        mCycleWheelViewProvince.setLabelSelectSize(15f);
        mCycleWheelViewProvince.setLabelSize(12f);
        mCycleWheelViewProvince.setAlphaGradual(0.7f);
        mCycleWheelViewProvince.setCycleEnable(true);
        mCycleWheelViewProvince.setSelection(mCurrentProviceIndex);
        mCycleWheelViewProvince.setLabelColor(Color.parseColor("#b3b3b3"));
        mCycleWheelViewProvince.setDivider(Color.parseColor("#e3e3e3"), 1);
        mCycleWheelViewProvince.setLabelSelectColor(Color.parseColor("#2196f3"));
        mCycleWheelViewProvince.setSolid(Color.WHITE, Color.WHITE);
        mCycleWheelViewProvince.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                List<String> city = mCitisDatasMap.get(mProvinceDatas.get(position));
                //if (city!=null && city.size()>0) {
                mCycleWheelViewCity.setLabels(city,null);
                //if (list != null && list.size() > 0) {
                //   subscriptions.remove(subscriptions.size()-1);
                if (!haveSetCity){
                    haveSetCity = true;
                    mCycleWheelViewCity.setSelection(mCurrentCityIndex);
                }
                // }
                //}
            }
        });
    }

    /**
     * 市
     */
    private void setCity(){
        List<String> city = mCitisDatasMap.get(mProvinceDatas.get(0));
        //if (city!=null && city.size()>0){
        mCycleWheelViewCity.setLabels(city,null);
        // }
        try {
            mCycleWheelViewCity.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        mCycleWheelViewCity.setAlphaGradual(0.7f);
        mCycleWheelViewCity.setLabelSelectSize(15f);
        mCycleWheelViewCity.setLabelSize(12f);
        mCycleWheelViewCity.setCycleEnable(true);
        mCycleWheelViewCity.setSelection(mCurrentCityIndex);
        mCycleWheelViewCity.setLabelColor(Color.parseColor("#b3b3b3"));
        mCycleWheelViewCity.setDivider(Color.parseColor("#e3e3e3"), 1);
        mCycleWheelViewCity.setLabelSelectColor(Color.parseColor("#2196f3"));
        mCycleWheelViewCity.setSolid(Color.WHITE, Color.WHITE);
        mCycleWheelViewCity.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                List<String> lab= mCycleWheelViewCity.getLabels();
                if(lab!=null && lab.size()>1){
                    //if(list!=null && list.size()>0){
                    //    subscriptions.remove(subscriptions.size()-1);

                    if (!haveSetDistrict){
                        haveSetDistrict = true;

                    }
                    // }
                }

            }
        });
    }



    public void show(){

        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        if(mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }else {
            //   mPopupWindow.showAsDropDown(parentView,0,0);
            mPopupWindow.showAtLocation(mViewGroup, Gravity.BOTTOM, 0, 0);
        }
    }

    public void dismiss(){
        if (mPopupWindow!=null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private void initNetDatas(){

                            parseAddressData(BaseApplication.province.provinceCityList);
                            mCurrentProviceName = BaseApplication.province.provinceCityList.get(0).provinceName;
                            mCurrentCityName =  BaseApplication.province.provinceCityList.get(0).cityList.get(0).cityName;
                            setProvince();
                            setCity();

    }


    private void parseAddressData(List<Province> provinceList) {
        for(int i=0;i<provinceList.size();i++){

            mProvinceDatas.add(i,provinceList.get(i).provinceName);
            List<City> cityList = provinceList.get(i).cityList;
            List<String> cityNames = new ArrayList<>();
            if (cityList!=null && cityList.size()>0){
                for(int j=0;j<cityList.size();j++){
                    cityNames.add(j,cityList.get(j).cityName);
                }
            }
            // 省-市的数据，保存到mCitisDatasMap
            mCitisDatasMap.put(provinceList.get(i).provinceName, cityNames);
        }
    }


}
