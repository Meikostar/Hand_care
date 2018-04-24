package com.canplay.medical.bean;

import java.io.Serializable;
import java.util.List;


public class Medicine extends BaseData implements Serializable{

//    {
//        "reminderId":"6b3d6425-b54f-e711-b9e4-2c44fd9333fc",
//            "remindingFor":"Measurement",
//            "schedule":[
//        {
//            "reminderTimeId":"6046a4b3-27d5-4d1a-8741-117b4bf3a117",
//                "when":"15:30",
//                "type":"time",
//                "items":[
//            {
//                "reminderItemId":"29f5f1b6-88e2-41eb-b573-3a83b0f8693f",
//                    "name":"血压"
//
//            }
//                ],
//            "completedForToday":false
//        },
//        {
//            "reminderTimeId":"e9f2c847-f632-4b96-9daa-d653c4da6d6d",
//                "when":"08:30",
//                "type":"time",
//                "items":[
//            {
//                "reminderItemId":"4061e5ca-c5d8-4a06-ab71-2f09208720a0",
//                    "name":"血压"
//
//            }
//                ],
//            "completedForToday":false
//        }
//        ],
//        "userId":"22ee3f4c-6393-41f6-9d92-86b23ad9a09c",
//            "userName":"testr",
//            "status":"Active",
//            "version":1,
//            "createdDateTime":"1497302510270",
//            "lastModified":"1497302510270",
//            "completedForToday":false


//   "userMedicineId":"5f1f0c36-9bff-e611-b9e4-2c44fd9333fc",
//           "userName":"zhangj",
//           "medicine":"Medicine4",
//           "medicineCode":"11504908-02",
//           "isCertified": true,
//           "image":"2000.jpg",
//           "adverseReaction":"


    public boolean completedForToday;
    public boolean isValid;
    public boolean isCertified;
    public boolean isCheck;
    public Long createdDateTime;
    public Long lastModified;
    public String userMedicineId;
    public String reminderTimeId;
    public String adverseReaction;
    public String image;
    public String filter;

    public String medicine;
    public String medicineCode;
    public String when;
    public String message;
    public String type;
    public String reminderId;
    public List<Medicine> schedule;
    public List<Item> items;
    public String remindingFor;
    public String messageId;
    public String userId;
    public String userName;
    public String tystatuspe;
    public String version;
    public String from;
    public String caution;
    public String instruction;



}
