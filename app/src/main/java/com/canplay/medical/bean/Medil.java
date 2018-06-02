package com.canplay.medical.bean;

import java.io.Serializable;
import java.util.List;


public class Medil {

//"object": {
//        "name": "Measurement",
//                "nextPlan": {
//            "when": "2018-05-19 09:56",
//                    "status": "incomplete",
//                    "code": "早",
//                    "items": [
//            {
//                "name": "血压",
//                    "timeStamp": "",
//                    "status": "incomplete"
//            }
//      ]
//        },
//        "plans": [
//        {
//            "when": "09:56",
//                "status": "incomplete",
//                "code": "早",
//                "items": [
//            {
//                "high": 0,
//                    "low": 0,
//                    "pulse": 0,
//                    "name": "血压",
//                    "timeStamp": "",
//                    "status": "incomplete"
//            }
//        ]
//        },
//        {
//            "when": "10:36",
//                "status": "incomplete",
//                "code": "早",
//                "items": [
//            {
//                "bloodGlucoseLevel": 0,
//                    "name": "血糖",
//                    "timeStamp": "",
//                    "status": "incomplete"
//            }
//        ]
//        }
//    ],
//        "actions": [],
//        "status": "incomplete"
//    },
//            "isSucceeded": true,
//            "message": ""
//   "medicine": "维生素B6片",
//           "dosage": "2颗",
//           "name": "用药",
//           "timeStamp": "",
//           "status": "incomplete"
    public boolean isSucceeded;
    public boolean isValid;
    public boolean isCertified;
    public boolean isCheck;
    public Long createdDateTime;
    public double timeStamp;
    public String dosage;
    public String message;
    public String medicine;
    public String status;
    public String when;
    public String name;
    public String bloodGlucoseLevel;

    public String pulse;
    public String low;
    public String high;
    public String code;
    public String reminderTimeId;
    public String type;
    public String reminderId;
    public List<Medil> plans;
    public List<Medil> items;
    public List<Medil> actions;
    public Medil object;
    public Medil nextPlan;



}
