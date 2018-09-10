package io.cordova.qianshou.mvp.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.bean.PoiDetail;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.location.LocationUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.bean.PoiDetail;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.location.LocationUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;

/**
 * 药店详情
 */
public class MedicalDetailActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.ll_collection)
    LinearLayout llCollection;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    private PoiDetail poiDetailResult;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_medical_detial);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        poiDetailResult= (PoiDetail) getIntent().getSerializableExtra("data");

    }

    @Override
    public void bindEvents() {

    }

    private Province prov;

    @Override
    public void initData() {
        if(poiDetailResult!=null){

            if(TextUtil.isNotEmpty(poiDetailResult.address)){
                tvAddress.setText(poiDetailResult.address);
            }
            if(TextUtil.isNotEmpty(poiDetailResult.time)){
                tvTime.setText("营业时间：每天 "+poiDetailResult.time);
            }
            if(TextUtil.isNotEmpty(poiDetailResult.name)){
                tvName.setText(poiDetailResult.name);
                navigationBar.setNaviTitle(poiDetailResult.name);
            }
            if(TextUtil.isNotEmpty(poiDetailResult.phone)){
                tvPhone.setText(poiDetailResult.phone);
            }
            String distance = GetDistance(LocationUtil.latitude, LocationUtil.longitude, poiDetailResult.latitude, poiDetailResult.longitude);
            tvDistance.setText(distance+"");
        }

    }

    /**
     * 计算两点距离
     * @param lat_a
     * @param lng_a
     * @param lat_b
     * @param lng_b
     * @return
     */
    private final double EARTH_RADIUS = 6378137.0;
    private String GetDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
        double radLat1 = (lat_a * Math.PI / 180.0);
        double radLat2 = (lat_b * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)

                + Math.cos(radLat1) * Math.cos(radLat2)

                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10) / 10000;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String format = decimalFormat.format(s);
        return format;

    }
}
