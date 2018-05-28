package com.canplay.medical.bean;

import java.util.List;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class AddMedical {
//    {  
//   "remindingFor":"Medicine",
//   "name":"Medicine1",
//   "message":"1颗",
//   "when":[‘8:30’, ‘18:30’],
//        "type":"time",
//   "userId":"xxx"

//    medicines	药物清单。数据格式是队列。队列内每个药物包括4个字段，name, image, dosage和specs。注意specs和image直段平台暂不保存
//    repetition	默认为1，即每天提醒。目前平台只支持每天提醒。
//    when	提醒时间，为24小时时间格式,比如'19:40'
//    type	只支持'time'
//    ringTone	铃声。目前版本平台不保存铃声
    public List<Decs> medicines;
    public String repetition;

    public String type;
    public String ringTone;
    public String when;


}
