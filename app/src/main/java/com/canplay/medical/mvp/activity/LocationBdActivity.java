package com.canplay.medical.mvp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorInfo;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.LOCATION;
import com.canplay.medical.bean.PoiDetail;
import com.canplay.medical.location.LocationUtil;
import com.canplay.medical.mvp.activity.mine.MedicalDetailActivity;
import com.canplay.medical.mvp.adapter.LocationListAdapter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.webContent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.radius;


public class LocationBdActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.top_view_back)
    ImageView topViewBack;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_go)
    LinearLayout llGo;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    private int count = 3;
    private BaiduMap mapViewMap;
    private boolean ifFrist = true;
    private String url;

    private int id;
    private int type;


    private String ids = "";
    private String w;
    private String j;
    private String address;
    private double jd = 0.0;
    private double wd = 0.0;
    private List<LOCATION> list = new ArrayList<>();

    private PoiSearch mPoiSearch;
    private LatLng point = null;
    private int i = 1;
    private List<File> files = new ArrayList<>();
    private GeoCoder geoCoder;

    private String address_detial;
    private String citys;
    private boolean isFirst;

    @Override
    public void initViews() {

        setContentView(R.layout.activity_location_mediacl);
        ButterKnife.bind(this);
        isFirst = true;
//
        geoCoder = GeoCoder.newInstance();
//        address = ShareDataManager.getInstance().getPara(this, ShareDataManager.ADDRESS_DETAIL);
        w = LocationUtil.latitude + "";
        j = LocationUtil.longitude + "";
        mapViewMap = mapView.getMap();
        address_detial = LocationUtil.address + "";
        citys = LocationUtil.city + "";



    }

     public int cpoiton=0;
     public View view;
     public ImageView imgs;
    private String uid;
     public LatLngBounds.Builder builder ;
    private PoiDetailResult result;
    private Map<Double,LatLng> map=new HashMap<>();
    private Map<Double,String> maps=new HashMap<>();
    private List<LatLng> lists=new ArrayList<>();
    @Override
    public void bindEvents() {
         llDetail.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 PoiDetail poiDetail = new PoiDetail();
                 poiDetail.address=result.getAddress();
                 poiDetail.name=result.getName();
                 poiDetail.time=result.getShopHours();
                 poiDetail.latitude=result.getLocation().latitude;
                 poiDetail.longitude=result.getLocation().longitude;
                 poiDetail.phone=result.getTelephone();
                 Intent intent = new Intent(LocationBdActivity.this, MedicalDetailActivity.class);
                 intent.putExtra("data",poiDetail);
                 startActivity(intent);
             }
         });
        topViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (list.size() > 0) {
                    intent.putExtra("name", list.get(0));
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {


                List<PoiInfo> allPoi = poiResult.getAllPoi();
                list.clear();
                map.clear();
                if (allPoi != null && allPoi.size() > 0) {
                    wd = allPoi.get(0).location.latitude;
                    jd = allPoi.get(0).location.longitude;
                    LatLng ll = new LatLng(wd,
                            jd);
                    MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
                    // 移动到某经纬度
                    mapViewMap.animateMapStatus(update);
                    if (mapViewMap != null) {
                        mapViewMap.clear();
                    }
                    cpoiton=0;
                    lists.clear();
                    for (PoiInfo info : allPoi) {

                        LOCATION location = new LOCATION();
                        location.setAddress(info.address);
                        location.setName(info.name);
                        location.setUid(info.uid);
                        location.setLatui(info.location.longitude);
                        location.setWeidu(info.location.latitude);
                        list.add(location);
                        LatLng   point = new LatLng(info.location.latitude, info.location.longitude);
                        lists.add(point);
                        if(map.get(info.location.longitude)==null){
                            map.put(info.location.longitude,point);
                            maps.put(info.location.longitude,info.uid);
                        }

                            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.location_mark, null);
                            imgs= (ImageView) view.findViewById(R.id.iv_img);

                        if(cpoiton==0){
                            imgs.setImageResource(R.mipmap.mykar_eye_ac);
                        }else {
                            imgs.setImageResource(R.drawable.pin_red);
                        }
                        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
                        cpoiton++;
                        OverlayOptions option = new MarkerOptions()
                                .position(point)
                                .icon(bitmap);
                        mapViewMap.addOverlay(option);
                        if(builder==null){
                            builder = new LatLngBounds.Builder();
                        }
                        for (LatLng p : lists) {
                            builder = builder.include(p);
                        }
                        LatLngBounds latlngBounds = builder.build();
                        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds,mapView.getWidth()-50,mapView.getHeight()-100);
                        mapViewMap.animateMapStatus(u);
                    }
                    mPoiSearch.searchPoiDetail((new PoiDetailSearchOption()).poiUid(list.get(1).getUid()));
                } else {
//                    mPoiSearch.searchInCity((new PoiCitySearchOption())
//                            .city(citys != null ? citys : LocationUtil.city)
//                            .keyword(LocationUtil.city)
//
//                            .pageNum(10).pageCapacity(0));
                    mPoiSearch.searchNearby(new PoiNearbySearchOption()
                            .keyword("药店")
                            .sortType(PoiSortType.distance_from_near_to_far)
                            .location(new LatLng(Double.valueOf(w), Double.valueOf(j)))
                            .radius(radius)
                            .pageNum(10));
                }


            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                result=poiDetailResult;
                String detailUrl = poiDetailResult.getDetailUrl();
                if(TextUtil.isNotEmpty(poiDetailResult.getAddress())){
                    tvAddress.setText(poiDetailResult.getAddress());
                }
                if(TextUtil.isNotEmpty(poiDetailResult.getShopHours())){
                    tvTime.setText(poiDetailResult.getShopHours());
                }
                if(TextUtil.isNotEmpty(poiDetailResult.getName())){
                    tvName.setText(poiDetailResult.getName());
                }

//
//                try {
//                    getUrlTask getUrlTask = new getUrlTask();
//                    getUrlTask.execute(detailUrl);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                List<PoiIndoorInfo> poiIndoorInfos = poiIndoorResult.getmArrayPoiInfo();
            }
        };
        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .keyword("药店")
                .sortType(PoiSortType.distance_from_near_to_far)
                .location(new LatLng(Double.valueOf(w), Double.valueOf(j)))
                .radius(radius)
                .pageNum(10));

        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);

//        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
//            public void onGetGeoCodeResult(GeoCodeResult result) {
//                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
////                    geoCoder.geocode(new GeoCodeOption()
////                            .city(TextUtil.isNotEmpty(citys)?citys:LocationUtil.city)
////                            .address(TextUtil.isNotEmpty(citys)?citys:LocationUtil.city));
//                    mPoiSearch.searchNearby(new PoiNearbySearchOption()
//                            .keyword("药店")
//                            .sortType(PoiSortType.distance_from_near_to_far)
//                            .location(new LatLng(Double.valueOf(w), Double.valueOf(j)))
//                            .radius(radius)
//                            .pageNum(10));
//                    return;
//                }
//                //获取反向地理编码结果
//                LatLng location = result.getLocation();
//                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(location));
//
//            }
//
//            @Override
//            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
//                List<PoiInfo> poiList = result.getPoiList();
//
//                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//
//                    if (TextUtil.isNotEmpty(j)) {
//                        jd = Double.parseDouble(j);
//                        wd = Double.parseDouble(w);
//                        point = new LatLng(wd, jd);
//                    } else {
//                        point = new LatLng(22.963175, 113.400244);
//                    }
//
//                    navigateTo();
//
////构建Marker图标
//                    BitmapDescriptor bitmap = BitmapDescriptorFactory
//                            .fromResource(R.drawable.pin_red);
////构建MarkerOption，用于在地图上添加Marker
//                    OverlayOptions option = new MarkerOptions()
//                            .position(point)
//                            .icon(bitmap);
////在地图上添加Marker，并显示
//                    mapViewMap.addOverlay(option);
//                    return;
//                }
//                dimessProgress();
//                //获取反向地理编码结果
//                list.clear();
//
//                if (poiList != null) {
//                    wd = poiList.get(0).location.latitude;
//                    jd = poiList.get(0).location.longitude;
//                    LatLng ll = new LatLng(wd,
//                            jd);
//                    MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
//                    // 移动到某经纬度
//                    mapViewMap.animateMapStatus(update);
//                    if (mapViewMap != null) {
//                        mapViewMap.clear();
//                    }
//                    point = new LatLng(wd, jd);
//                    BitmapDescriptor bitmap = BitmapDescriptorFactory
//                            .fromResource(R.drawable.pin_red);
//                    OverlayOptions option = new MarkerOptions()
//                            .position(point)
//                            .icon(bitmap);
//                    mapViewMap.addOverlay(option);
//                    for (PoiInfo info : poiList) {
//                        LOCATION location = new LOCATION();
//                        location.setAddress(info.address);
//                        location.setName(info.name);
//                        location.setLatui(info.location.longitude);
//                        location.setWeidu(info.location.latitude);
//                        list.add(location);
//                    }
//                }
//
//
//
//            }
//        };
//        geoCoder.setOnGetGeoCodeResultListener(listener);
//        geoCoder.geocode(new GeoCodeOption()
//                .city(citys)
//                .address(address_detial));
//        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(point));

        mapViewMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mapViewMap.clear();
                LatLng latLng = marker.getPosition();
                lists.clear();

                for(Double key:map.keySet()){

                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.location_mark, null);
                        imgs= (ImageView) view.findViewById(R.id.iv_img);

                    lists.add(map.get(key));
                    point=map.get(key);
                    if(latLng.longitude==key){
                        uid = maps.get(key);

                        imgs.setImageResource(R.mipmap.mykar_eye_ac);
                    }else {
                        imgs.setImageResource(R.drawable.pin_red);
                    }
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);

                    OverlayOptions option = new MarkerOptions()
                            .position(point)
                            .icon(bitmap);
                    mapViewMap.addOverlay(option);

                }
                if(TextUtil.isNotEmpty(uid)){
                    mPoiSearch.searchPoiDetail((new PoiDetailSearchOption()).poiUid(uid));
                }

                return true;
            }
        });
        //单击地图的监听
        mapViewMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                                             //地图单击事件回调方法
                                             @Override
                                             public void onMapClick(LatLng latLng) {
                                                 mapViewMap.clear();
                                                 mPoiSearch.searchNearby(new PoiNearbySearchOption()
                                                         .keyword("药店")
                                                         .sortType(PoiSortType.distance_from_near_to_far)
                                                         .location(new LatLng(latLng.latitude, latLng.longitude))
                                                         .radius(8000)
                                                         .pageNum(10));

                                             }

                                             //Poi 单击事件回调方法，比如点击到地图上面的商店，公交车站，地铁站等等公共场所
                                             @Override
                                             public boolean onMapPoiClick(MapPoi mapPoi) {
//                                                Log.e("TAG", "点击到地图上的POI物体了！名称：" + mapPoi.getName() + ",Uid:" + mapPoi.getUid());
                                                 return true;
                                             }
                                         }
        );

    }

//    private void navigateTo() {
//        // 按照经纬度确定地图位置
//
//
//        if (ifFrist) {
//            LatLng ll = new LatLng(wd,
//                    jd);
//            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
//            // 移动到某经纬度
//            mapViewMap.animateMapStatus(update);
////            update = MapStatusUpdateFactory.zoomBy(5f);
////            // 放大
////            mapViewMap.animateMapStatus(update);
//
//            ifFrist = false;
//        }
//        // 显示个人位置图标
//        MyLocationData.Builder builder = new MyLocationData.Builder();
//        builder.latitude(wd);
//        builder.longitude(jd);
//        MyLocationData data = builder.build();
//        mapViewMap.setMyLocationData(data);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        if (list.size() > 0) {
            intent.putExtra("name", list.get(0));
        }

        setResult(RESULT_OK, intent);
    }

    @Override
    public void initData() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        geoCoder.destroy();
    }



    //public abstract class AsyncTask<Params, Progress, Result>
    //在此例中，Params泛型是String类型，Progress泛型是Object类型，Result泛型是Long类型

    private class getUrlTask extends AsyncTask<String, Object, String> {
        @Override
        protected void onPreExecute() {
            Log.i("iSpring", "DownloadTask -> onPreExecute, Thread name: " + Thread.currentThread().getName());
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            Log.i("iSpring", "DownloadTask -> doInBackground, Thread name: " + Thread.currentThread().getName());

            String param = params[0];
            String url = "";
            try {
                url = webContent.getUrl(webContent.getHtml(param));
            } catch (Exception e) {
                e.printStackTrace();
            }

            //将总共下载的字节数作为结果返回
            return url;
        }


        @Override
        protected void onProgressUpdate(Object... values) {
            Log.i("iSpring", "DownloadTask -> onProgressUpdate, Thread name: " + Thread.currentThread().getName());
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String url) {
            Glide.with(LocationBdActivity.this).load(url).asBitmap().into(img);
        }

        @Override
        protected void onCancelled() {
            Log.i("iSpring", "DownloadTask -> onCancelled, Thread name: " + Thread.currentThread().getName());
            super.onCancelled();

        }
    }

}