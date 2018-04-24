package com.canplay.medical.bean;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class Euipt {

//    {
//        "patientDeviceId":"e6192688-0b05-e711-b9e4-2c44fd9333fc",
//            "userId":"ccda9cf7-d871-41a5-8548-77022c54f1bf",
//            "userName":"zhangj",
//            "serialNo":"9999031842",
//            "deviceType":"Blood pressure monitor",
//            "deviceTypeDisplayName":"慧医-爱奥乐血压计",
//            "deviceUserId":"0",
//            "status":"Active",
//            "createdDateTime":"1489093277677",
//            "lastModified":"1489093277677"
//    }


    public Boolean isCompleted;
    public String patientDeviceId;
    public String userId;
    public String userName;
    public String serialNo;
    public String deviceType;

    public long createdDateTime;
    public long lastModified;
    public int type;
    public String status;
    public String deviceUserId;
    public String deviceTypeDisplayName;

}
