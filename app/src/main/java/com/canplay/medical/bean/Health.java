package com.canplay.medical.bean;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class Health {
// "bloodPressure": {
//        "version": "1",
//                "high": 33,
//                "low": 66,
//                "pulse": 63,
//                "timeStamp": "1525917054743"
//    },
//            "bloodGlucoseLevel": {
//        "version": "1",
//                "bgl": 5.2,
//                "timeStamp": "1508434773183"
//    },
//            "medicineRecord": {
//        "version": "1",
//                "medicineName": " -  04 已经错过正确服药时间了",
//                "timeStamp": "1506009564650"
//    }

    public Health bloodPressure;
    public Health bloodGlucoseLevel;
    public Health medicineRecord;
    public long timeStamp;
    public String medicineName;
    public String version;
    public String bgl;
    public String pulse;
    public String low;
    public String high;

}
