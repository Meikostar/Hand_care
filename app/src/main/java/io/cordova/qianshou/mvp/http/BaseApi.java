package io.cordova.qianshou.mvp.http;



import io.cordova.qianshou.bean.Add;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.BaseData;
import io.cordova.qianshou.bean.BaseResult;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Boxs;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.bean.Health;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.bean.Message;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.bean.Recovery;
import io.cordova.qianshou.bean.RecoveryPsw;
import io.cordova.qianshou.bean.Righter;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.Sugar;
import io.cordova.qianshou.bean.USER;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.bean.unBind;

import java.util.List;
import java.util.Map;

import io.cordova.qianshou.bean.Add;
import io.cordova.qianshou.bean.AddMedical;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.BaseData;
import io.cordova.qianshou.bean.BaseResult;
import io.cordova.qianshou.bean.Bind;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Boxs;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.bean.Health;
import io.cordova.qianshou.bean.Medic;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.bean.Message;
import io.cordova.qianshou.bean.Mesure;
import io.cordova.qianshou.bean.Phone;
import io.cordova.qianshou.bean.Phones;
import io.cordova.qianshou.bean.Press;
import io.cordova.qianshou.bean.Pws;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.bean.Recovery;
import io.cordova.qianshou.bean.RecoveryPsw;
import io.cordova.qianshou.bean.Righter;
import io.cordova.qianshou.bean.Sug;
import io.cordova.qianshou.bean.Sugar;
import io.cordova.qianshou.bean.USER;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.bean.unBind;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


public interface BaseApi {


    /**
     * Login
     * @param options
     * @return
     */


    @FormUrlEncoded
    @POST("Flow/Token")
    Observable<USER> Login(@FieldMap Map<String, String> options);

    /**
     * 获取验证码
     */
    @POST("Flow/v2/VerifyMobileNumber/{name}")
    Observable<BASE> getCode(@Path("name") String name);
    /**
     * 获取验证码(忘记密码)
     */
    @GET("Flow/v2/PasswordRecovery/{name}")
    Observable<BaseResult> getRecoveryCode(@Path("name") String name);
    /**
     * 校验验证码
     */
    @POST("Flow/v2/VerifyMobileNumber/Verify/{code}/{phone}")
    Observable<BASE> checkCode(@Path("phone") String phone,@Path("code") String code);
    /**
     * 校验验证码(忘记密码)
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/PasswordRecovery/Validate")
    Observable<BaseResult> checkCodeRecovery(@Body Recovery body);

    /**
     * 注册
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("flow/v2/register")
    Observable<BASE> righter(@Body Righter body);


    /**
     * 编辑用户信息
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/user")
    Observable<BASE> editorUser(@Body Editor body);

    /**
     * 忘记密码
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/PasswordRecovery/Reset")
    Observable<BASE> recoveryPsw(@Body RecoveryPsw body);
    /**
     * 添加智能设备
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Device/Link")
    Observable<BASE> bindDevice(@Body Bind body);

    /**
     * 移除智能设备
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Device/Unlink")
    Observable<BASE> UnbindDevice(@Body unBind body);



    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Medicine")
    Observable<Medicines> addMedical(@Body Medic body);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Medicine/Uncertified")
    Observable<Medicines> Uncertified(@Body Medic body);

    /**
     * 首页
     */
    @GET("Flow/v2/ReminderStatus/{name}")
    Observable<BASE> getUserdata(@Path("name") String name);
    /**
     * messagecout
     */
    @GET("Flow/v2/Message/Count")
    Observable<BASE> getMessageCout();
    /**
     * messageList
     */
    @GET("Flow/v2/Message")
    Observable<List<Message>> getMessageList();
    /**
     * 用药提醒
     */
    @GET("Flow/v2/Reminder/{userId}/Medicine")
    Observable<Medicine> MedicineRemindList(@Path("userId") String userId);
    /**
     * 测量提醒
     */
    @GET("Flow/v2/Reminder/{userId}/Measurement")
    Observable<Medicine> MeasureRemindList(@Path("userId") String userId);
    /**
     * 测量提醒
     */
    @GET("Flow/v2/Reminder/{userId}/Measurement/{name}")
    Observable<List<Medicine>> MeasureRemindDetail(@Path("userId") String userId,@Path("name") String name);

    /**
     * 好友列表
     */
    @GET("Flow/v2/Circle")
    Observable<List<Friend>> getFriendList();

    /**
     * 添加好友
     */

    @GET("Flow/v2/User/Search/{search}")
    Observable<List<Friend>> searchFriend(@Path("search") String search);

    /**
     * 添加好友
     */

    @GET("Flow/v2/User/{userId}")
    Observable<Friend> getFriendInfo(@Path("userId") String userId);


    /**
     * 医生列表
     */

    @GET("Flow/v2/FamilyDoctor")
    Observable<List<Friend>> getDoctorList();

    /**
     * 添加好友
     */
    @GET("flow/v2/doctor/name/{search}")
    Observable<List<Friend> > searchDoctor(@Path("search") String search);

    /**
     * 手搜索药品
     */
    @GET("Flow/v2/MedicineCatalog/Search/{search}")
    Observable<List<Medicines> > searchMedicine(@Path("search") String search);

    /**
     * 体醒详情
     */
    @GET("Flow/v2/ReminderSummary/{reminderTimeId}")
    Observable<Detail> getDetail(@Path("reminderTimeId") String reminderTimeId);
    /**
     * 药品名称或取药品信息
     */
    @GET("Flow/v2/MedicineCatalog/{search}")
    Observable<Medicines> getMedicalDetail(@Path("search") String search);
    /**
     * 添加好友
     */

    @GET("Flow/v2/Doctor/{userId}")
    Observable<Friend> getDoctorInfo(@Path("userId") String userId);


    /**
     * 添加家庭医生flow/v2/familydoctor/
     */
    @POST("flow/v2/familydoctor/{userId}")
    Observable<Friend> AddDoctor(@Path("userId") String userId);


    /**
     * 移除家庭医生
     */
    @DELETE("Flow/v2/FamilyDoctor/{userId}")
    Observable<Friend> DelDoctor(@Path("userId") String userId);

    /**
     * 智能设备列表
     */
    @GET("Flow/v2/Device/{userId}")
    Observable<List<Euipt>> getSmartList(@Path("userId") String userId);

    /**
     * 智能设备列表
     */
    @GET("flow/v2/Timeline/Summary/{userId}")
    Observable<Health> getHealthData(@Path("userId") String userId);
    /**
     * 测量记录
     */
    @GET("Flow/v2/Timeline/{userId}/{category}/{from}/{take}")
    Observable<List<Record>> getMeasureRecord(@Path("userId") String userId, @Path("category") String category,
                                              @Path("from") String from, @Path("take") String take);
    /**
     * 事件轴
     */
    @GET("Flow/v2/Timeline/{userId}/{from}/{take}")
    Observable<List<Record>> getTimeRecord(@Path("userId") String userId,
                                              @Path("from") String from, @Path("take") String take);

    /**
     * 血压测量记录
     */
    @GET("Flow/v2/BloodPressure/{userId}/{from}/{take}")
    Observable<List<Record>> getBloodPressList(@Path("userId") String userId,
                                              @Path("from") String from, @Path("take") String take);


    /**
     * 验证药监码并返回药品信息
     */
    @GET("Flow/v2/Medicine/Verify/{code}")
    Observable<List<Record>> getMedicalInfo(@Path("code") String code);
    /**
     * 验证药监码并返回药品信息
     */
    @GET("flow/v2/plan/{type}")
    Observable<Medil> getDetails(@Path("type") String type);


    /**
     * 添加测量
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Reminder")
    Observable<BASE> addMesure(@Body Mesure body);
    /**
     * 添加用药提醒
     */
//
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("Flow/v2/Reminder")
//    Observable<BaseData> addMedical(@Body AddMedical body);
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("flow/v3/reminder/medication")
    Observable<BaseData> addMedical(@Body AddMedical body);

    /**
     * 更换手机号
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("flow/v2/user")
    Observable<BASE> editorPhone(@Body Phone body);
    /**
     * 更换手机号
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("flow/v2/user/mobile")
    Observable<BASE> changePhone(@Body Phones body);

    /**
     * 更换密码
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/ChangePassword")
    Observable<BASE> editorPsw(@Body Pws body);
    /**
     * bu不同意友/移除好友关系
     */
    @DELETE("Flow/v2/Reminder/{ReminderId}")
    Observable<BASE> removeRemind(@Path("ReminderId") String ReminderId);

    /**
     *  添加好友
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Circle")
    Observable<List<BASE>> addFriend(@Body Add body);



//    /**
//     * 同意友
//     */
//    @POST("Flow/v2/ReminderResponse/Confirm/Medicine")
//    Observable<BASE > confirmEat(@Path("familyAndFriendsId") String familyAndFriendsId);
    /**
     * 同意友
     */
    @POST("flow/v2/medication/confirm/{reminderTimeId}")
    Observable<BASE > confirmEat(@Path("reminderTimeId") String reminderTimeId);
    /**
     * 同意友
     */
    @POST("Flow/v2/Circle/Approve/{familyAndFriendsId}")
    Observable<String> agree(@Path("familyAndFriendsId") String familyAndFriendsId);
    /**
     * bu不同意友/移除好友关系
     */
    @DELETE("Flow/v2/Circle/{familyAndFriendsId}")
    Observable<BASE> dissAgree(@Path("familyAndFriendsId") String familyAndFriendsId);


    /**
     *  添加血糖数据
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/BloodGlucose")
    Observable<Sugar> addBloodSugar(@Body Sug body);



    /**
     *  添加血压数据
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/BloodPressure")
    Observable<Sugar> addBloodPress(@Body Press body);


    /**
     *  添加血压数据
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Avatar")
    Observable<BASE> upPhotos(@Body avator body);


    /**
     * 血糖数据记录
     */
    @GET("Flow/v2/BloodGlucose/{userId}/{from}/{take}")
    Observable<List<Record>> getBloodList(@Path("userId") String userId,
                                   @Path("from") String from, @Path("take") String take);

    /**
     *指定天数血糖数据记录
     */
    @GET("Flow/v2/BloodGlucose/{userId}/{days}")
    Observable<List<Record>> getDayBloodRecord(@Path("userId") String userId,
                                         @Path("days") String days);

    /**
     *指定天数血糖数据记录
     */

    @GET("Flow/v2/BloodPressure/{userId}/{days}")
    Observable<List<Record>> getDayBloodPressRecord(@Path("userId") String userId,
                                              @Path("days") String days);

    /**
     *药物列表
     */

    @GET("Flow/v2/Medicine/{userId}")
    Observable<List<Medicine>> getMedicalList(@Path("userId") String userId);

    /**
     *药物列表
     */

    @GET("Flow/v2/Device/MedicineBox/{userId}")
    Observable<Boxs> myMedicineBox(@Path("userId") String userId);

    /**
     *药物列表
     */

    @GET("Flow/v2/MedicineBox/Status/{userId}")
    Observable<Box> getBoxInfo(@Path("userId") String userId);
    /**
     *药物列表
     */

    @GET("Flow/v2/MedicineBox/{userId}")
    Observable<Box> isMedicineBox(@Path("userId") String userId);


    /**
     *扫描添加
     */

    @GET("Flow/v2/Medicine/Verify/{meidcineCode}")
    Observable<Medicines> getMedicineInfo(@Path("meidcineCode") String meidcineCode);

}
