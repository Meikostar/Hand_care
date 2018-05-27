package com.canplay.medical.bean;

import java.io.Serializable;
import java.util.List;


public class Medicines extends BaseData implements Serializable{


//    "medicine":{
//        "certifiedMedicineId":"de8542c5-5ea8-e611-bb54-2c44fd9333fc",
//                "medicineCategoryId":"fe7e9c05-5ba8-e611-bb54-2c44fd9333fc",
//                "category":{
//            "medicineCategoryId":"fe7e9c05-5ba8-e611-bb54-2c44fd9333fc",
//                    "name":"Medicine3",
//                    "englishName":null,
//                    "pinyinName":null,
//                    "approvalNumber":null,
//                    "composition":"本品主要成分为硫酸氢氯吡格雷",
//                    "look":"本品为白色或类白色片或薄膜衣片，除去包衣后，显白色或类白色。",
//                    "disease":"氯吡格雷患者用于以下，预防动脉粥硬化血栓形成事件：近期心肌梗死患者（从几天到小于35天），近期缺血性卒中患者（从7天到小于6个月）或确诊外周动脉性疾病的患者。急性冠脉综合征的患者，详见说明书。",
//                    "drugInteraction":null,
//
//      ",
//
//        },


    public boolean isValid;
    public boolean isCheck;
    public Long createdDateTime;
    public Medicines medicine;
    public Medicines category;
    public Com command;
    public String certifiedMedicineId;
    public String instruction;
    public String specs;
    public String forPregnentOrBreastFeeding;
    public String trademark;
    public String forChildren;
    public String forElderly;
    public String adverseReaction;
    public String caution;
    public String drugInteraction;
    public String disease;
    public String look;
    public String composition;
    public String userMedicineId;
    public String approvalNumber;
    public String pinyinName;
    public String englishName;
    public String name;
    public String medicineCategoryId;
    public String unit;
    public String manufacturer;
    public String dose;
    public String description;
    public String image;
    public String code;
    public String storage;
    public String  pack;

    public String displayName;
    public String createdBy;
    public String toxicology;
    public String standard;
    public String pharmacokinetics;
    public String overdose;


}
