package com.canplay.medical.bean;

import java.util.List;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class Mesure {


//    {  
//   "remindingFor":"Measurement",
//   "name":"血压",
//   "when":"[‘8:30’, ‘18:30’]",
//            "type":"time",
//   "":"xxx"


    public String name;
    public List<String> when;
    public String type;
    public String userId;
    public String remindingFor;

}
