package com.canplay.medical.bean;

import java.io.Serializable;

/**
 * Created by mykar on 17/4/26.
 */
public class Item implements Serializable{
// "reminderItemId":"771b6d22-197f-4256-b01b-4c27d5758ac5",
////                "name":"Test Medicine",
////                "message":"1 pcs"
//       "title":"添加药物",
//               "category":"Medicine",
//               "content":"添加药物Medicine4",
//               "createdDateTime":"1489702598763"
    public String reminderItemId;
    public String name;
    public String title;
    public String category;

    public String content;
    public long createdDateTime;
    public String message;

}
