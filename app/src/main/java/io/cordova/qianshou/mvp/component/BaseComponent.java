package io.cordova.qianshou.mvp.component;


import io.cordova.qianshou.base.AppComponent;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.fragment.ChartFragment;
import io.cordova.qianshou.fragment.ChartPressFragment;
import io.cordova.qianshou.fragment.HealthDataFragment;
import io.cordova.qianshou.fragment.HomeDoctorFragment;
import io.cordova.qianshou.fragment.HomeFragment;
import io.cordova.qianshou.fragment.LineCharFragment;
import io.cordova.qianshou.fragment.LineCharSugarFragment;
import io.cordova.qianshou.fragment.MeasureRemindFragment;
import io.cordova.qianshou.fragment.RemindMedicatFragment;
import io.cordova.qianshou.fragment.SetFragment;
import io.cordova.qianshou.mvp.ActivityScope;
import io.cordova.qianshou.mvp.activity.RemindFirstDetailActivity;
import io.cordova.qianshou.mvp.activity.RemindSecondDetailActivity;
import io.cordova.qianshou.mvp.activity.account.ChangePhoneActivity;
import io.cordova.qianshou.mvp.activity.account.ForgetFirstActivity;
import io.cordova.qianshou.mvp.activity.account.ForgetPswActivity;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.mvp.activity.MainActivity;
import io.cordova.qianshou.mvp.activity.account.RegisteredActivity;
import io.cordova.qianshou.mvp.activity.account.RegisteredSecondActivity;
import io.cordova.qianshou.mvp.activity.health.TakeMedicineActivity;
import io.cordova.qianshou.mvp.activity.health.TimeXRecordActivity;
import io.cordova.qianshou.mvp.activity.home.AddBloodDataActivity;
import io.cordova.qianshou.mvp.activity.home.AddDataActivity;
import io.cordova.qianshou.mvp.activity.home.ChooseMedicalActivity;
import io.cordova.qianshou.mvp.activity.home.DoctorDetailActivity;
import io.cordova.qianshou.mvp.activity.home.MeasureActivity;
import io.cordova.qianshou.mvp.activity.home.MeasurePlanActivity;
import io.cordova.qianshou.mvp.activity.home.MedicalDetailActivity;
import io.cordova.qianshou.mvp.activity.home.MessageActivity;
import io.cordova.qianshou.mvp.activity.home.SearchMedicalActivity;
import io.cordova.qianshou.mvp.activity.home.SmartKitActivity;
import io.cordova.qianshou.mvp.activity.home.UsePlanActivity;
import io.cordova.qianshou.mvp.activity.mine.AddFriendActivity;
import io.cordova.qianshou.mvp.activity.mine.AlarmActivity;
import io.cordova.qianshou.mvp.activity.mine.BindPhoneActivity;
import io.cordova.qianshou.mvp.activity.mine.BloodPressRecordActivity;
import io.cordova.qianshou.mvp.activity.mine.BloodSugarRecordActivity;
import io.cordova.qianshou.mvp.activity.mine.EditorPwsActivity;
import io.cordova.qianshou.mvp.activity.mine.FriendDetailActivity;
import io.cordova.qianshou.mvp.activity.mine.MineEuipmentActivity;
import io.cordova.qianshou.mvp.activity.mine.MineHealthCenterActivity;
import io.cordova.qianshou.mvp.activity.mine.MineInfoActivity;
import io.cordova.qianshou.mvp.activity.mine.RemindSettingActivity;

import dagger.Component;
import io.cordova.qianshou.base.AppComponent;
import io.cordova.qianshou.fragment.BloodRecordFragment;
import io.cordova.qianshou.fragment.ChartFragment;
import io.cordova.qianshou.fragment.ChartPressFragment;
import io.cordova.qianshou.fragment.HealthDataFragment;
import io.cordova.qianshou.fragment.HomeDoctorFragment;
import io.cordova.qianshou.fragment.HomeFragment;
import io.cordova.qianshou.fragment.LineCharFragment;
import io.cordova.qianshou.fragment.LineCharSugarFragment;
import io.cordova.qianshou.fragment.MeasureRemindFragment;
import io.cordova.qianshou.fragment.RemindMedicatFragment;
import io.cordova.qianshou.fragment.SetFragment;

/**
 * Created by leo on 2016/9/27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BaseComponent{

    void inject(LoginActivity binderActivity);
    void inject(ChangePhoneActivity binderActivity);
    void inject(UsePlanActivity binderActivity);
    void inject(MeasurePlanActivity binderActivity);
    void inject(AlarmActivity binderActivity);
    void inject(EditorPwsActivity binderActivity);
    void inject(BindPhoneActivity binderActivity);
    void inject(DoctorDetailActivity binderActivity);
    void inject(LineCharSugarFragment binderActivity);
    void inject(LineCharFragment binderActivity);
    void inject(ChartPressFragment binderActivity);
    void inject(ChartFragment binderActivity);
    void inject(BloodSugarRecordActivity binderActivity);
    void inject(BloodPressRecordActivity binderActivity);
    void inject(FriendDetailActivity binderActivity);
    void inject(RemindSecondDetailActivity binderActivity);
    void inject(RemindFirstDetailActivity binderActivity);
    void inject(AddDataActivity binderActivity);
    void inject(TimeXRecordActivity binderActivity);
    void inject(MedicalDetailActivity binderActivity);
    void inject(RemindSettingActivity binderActivity);
    void inject(ForgetPswActivity binderActivity);
    void inject(ForgetFirstActivity binderActivity);
    void inject(SmartKitActivity binderActivity);
    void inject(MeasureRemindFragment binderActivity);
    void inject(MineInfoActivity binderActivity);
    void inject(SearchMedicalActivity binderActivity);
    void inject(ChooseMedicalActivity binderActivity);
    void inject(AddBloodDataActivity binderActivity);
    void inject(MeasureActivity binderActivity);
    void inject(BloodRecordFragment binderActivity);
    void inject(TakeMedicineActivity binderActivity);
    void inject(MineEuipmentActivity binderActivity);
    void inject(AddFriendActivity binderActivity);
    void inject(MineHealthCenterActivity binderActivity);
    void inject(RemindMedicatFragment binderActivity);
    void inject(HomeFragment binderActivity);
    void inject(MessageActivity binderActivity);
    void inject(RegisteredActivity binderActivity);
    void inject(RegisteredSecondActivity binderActivity);
    void inject(MainActivity binderActivity);
    void inject(HealthDataFragment binderActivity);
    void inject(SetFragment binderActivity);

    void inject(HomeDoctorFragment binderActivity);


}
