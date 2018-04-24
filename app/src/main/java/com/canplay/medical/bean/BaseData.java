package com.canplay.medical.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class BaseData implements Serializable {

//
// {  
//   "":"2161311b-f601-e711-b9e4-2c44fd9333fc",
//   "remindingFor":"Medicine",
//   "name":"Medicine4",
//   "message":"1粒",
//   "timewhen":[  
//      "11:30"
//   ],
//        "type":"time",
//   "userId":null,
//   "userFullName":null,
//   "version":"3",
//   “status”:”Pending”,
//   "":"3"
//    }   


    public int latestVersion;
    public int version;
    public String  userFullName;
    public String  reminderId;
    public String  name;
    public String  message;
    public String  type;
    public String  status;
    public String  userId;
    public List<String> timewhen;
}
