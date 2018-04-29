package com.canplay.medical.bean;

import java.io.Serializable;

/**
 * Created by mykar on 17/4/26.
 */
public class Friend implements Serializable{
//    {
//        "familyAndFriendsId":"8b2f5291-ea12-e711-b9e4-2c44fd9333fc",
//            "userId":"ccda9cf7-d871-41a5-8548-77022c54f1bf",
//            "userName":"zhangj",
//            "familyAndFriendsUserId":"e391798e-7e8a-4ecb-bf85-8e5196b4a969",
//            "familyAndFriendsUserName":"18078499686",
//            "name":"阿国",
//            "nickname":"阿国",
//            "avatar":"imagec49c3cd1-759c-4a57-b033-ae88d5ea4872.jpg",
//            "":"Active",
//            "createdDateTime":"1490647099667",
//            "lastModified":"1490618308043",
//            "hasData":false


    public Friend user;
    public Friend participant;
    public String familyAndFriendsId;
    public String familyDoctorId;
    public String userId;
    public String lastName;
    public String password;
    public String firstName;
    public boolean isSuperUser;
    public String lineManagerName;
    public String familyAndFriendsUserId;

    public String familyAndFriendsUserName;
    public String name;
    public String gender;
    public String mobile;
    public String phone;
    public String nickname;
    public String position;
    public String avatar;
    public String userName;
    public String lineManager;
    public String email;
    public String dob;
    public String hospitalId;
    public String hospital;
    public String personalId;
    public String status;
    public String message;
    public String notificationRegId;
    public Long createdDateTime;
    public Long lastModified;
    public int type;
    public Boolean hasData;

}
