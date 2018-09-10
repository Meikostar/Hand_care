package io.cordova.qianshou.bean;

import org.json.JSONObject;

/**
 * Created by mykar on 17/4/24.
 */

public class PREFIX {
//    "c_m_p_id": "289",
//            "country": "阿尔巴尼亚",#国家
//    "mobile_prefix": "+355",#区号
//    "area": "欧洲",
//            "filter": "A",#大写首字母

    public int c_m_p_id;

    public String country;
    public boolean isCheck;
    public String country_e;

    public String area;

    public String mobile_prefix;

    public String filter;
    public String en_filter;

    public String getEn_filter() {
        return en_filter;
    }

    public void setEn_filter(String en_filter) {
        this.en_filter = en_filter;
    }

    public String getCountry_e() {
        return country_e;
    }

    public void setCountry_e(String country_e) {
        this.country_e = country_e;
    }

    public int getC_m_p_id() {

        return c_m_p_id;
    }

    public void setC_m_p_id(int c_m_p_id) {
        this.c_m_p_id = c_m_p_id;

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;

    }

    public String getMobile_prefix() {
        return mobile_prefix;
    }

    public void setMobile_prefix(String mobile_prefix) {
        this.mobile_prefix = mobile_prefix;

    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {

        this.filter = filter;
    }

    public static PREFIX fromJson(JSONObject jo) {
        PREFIX item = new PREFIX();
        item.c_m_p_id = jo.optInt("c_m_p_id");
        item.country = jo.optString("country");
        item.area = jo.optString("area");
        item.mobile_prefix = jo.optString("mobile_prefix");
        item.filter = jo.optString("filter");



        return item;
    }

}
