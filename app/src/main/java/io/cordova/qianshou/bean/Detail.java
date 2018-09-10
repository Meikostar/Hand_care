package io.cordova.qianshou.bean;

import java.io.Serializable;
import java.util.List;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class Detail implements Serializable{
//    {
//        "reminderTimeId": "e0770e6d-2ef3-4a8b-9ce3-f1387331b827",
//            "type": "02",
//            "time": "17:27",
//            "code": "晚",
//            "message": "请按照智能药盒说明选择正确药杯服用药物",
//            "isMedicineBoxUser": true,
//            "items": [
//        {
//            "name": "维生素B2片",
//                "image": "",
//                "dosage": "",
//                "specs": ""
//        }
//  ]
//    }


    public String reminderTimeId;
    public String type;
    public String time;
    public String code;
    public String name;
    public String image;
    public String dosage;
    public String specs;
    public boolean isMedicineBoxUser;
    public List<Detail> items;



}
