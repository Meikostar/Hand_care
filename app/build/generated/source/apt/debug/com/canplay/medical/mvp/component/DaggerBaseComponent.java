// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.component;

import com.canplay.medical.base.AppComponent;
import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.fragment.BloodRecordFragment;
import com.canplay.medical.fragment.BloodRecordFragment_MembersInjector;
import com.canplay.medical.fragment.ChartFragment;
import com.canplay.medical.fragment.ChartFragment_MembersInjector;
import com.canplay.medical.fragment.ChartPressFragment;
import com.canplay.medical.fragment.ChartPressFragment_MembersInjector;
import com.canplay.medical.fragment.HealthDataFragment;
import com.canplay.medical.fragment.HomeDoctorFragment;
import com.canplay.medical.fragment.HomeDoctorFragment_MembersInjector;
import com.canplay.medical.fragment.HomeFragment;
import com.canplay.medical.fragment.HomeFragment_MembersInjector;
import com.canplay.medical.fragment.LineCharFragment;
import com.canplay.medical.fragment.LineCharFragment_MembersInjector;
import com.canplay.medical.fragment.LineCharSugarFragment;
import com.canplay.medical.fragment.LineCharSugarFragment_MembersInjector;
import com.canplay.medical.fragment.MeasureRemindFragment;
import com.canplay.medical.fragment.MeasureRemindFragment_MembersInjector;
import com.canplay.medical.fragment.RemindMedicatFragment;
import com.canplay.medical.fragment.RemindMedicatFragment_MembersInjector;
import com.canplay.medical.fragment.SetFragment;
import com.canplay.medical.fragment.SetFragment_MembersInjector;
import com.canplay.medical.mvp.activity.MainActivity;
import com.canplay.medical.mvp.activity.MainActivity_MembersInjector;
import com.canplay.medical.mvp.activity.RemindFirstDetailActivity;
import com.canplay.medical.mvp.activity.RemindFirstDetailActivity_MembersInjector;
import com.canplay.medical.mvp.activity.RemindSecondDetailActivity;
import com.canplay.medical.mvp.activity.RemindSecondDetailActivity_MembersInjector;
import com.canplay.medical.mvp.activity.account.ForgetFirstActivity;
import com.canplay.medical.mvp.activity.account.ForgetFirstActivity_MembersInjector;
import com.canplay.medical.mvp.activity.account.ForgetPswActivity;
import com.canplay.medical.mvp.activity.account.ForgetPswActivity_MembersInjector;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.activity.account.LoginActivity_MembersInjector;
import com.canplay.medical.mvp.activity.account.RegisteredActivity;
import com.canplay.medical.mvp.activity.account.RegisteredActivity_MembersInjector;
import com.canplay.medical.mvp.activity.account.RegisteredSecondActivity;
import com.canplay.medical.mvp.activity.account.RegisteredSecondActivity_MembersInjector;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity_MembersInjector;
import com.canplay.medical.mvp.activity.health.TimeXRecordActivity;
import com.canplay.medical.mvp.activity.health.TimeXRecordActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.AddBloodDataActivity;
import com.canplay.medical.mvp.activity.home.AddBloodDataActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.AddDataActivity;
import com.canplay.medical.mvp.activity.home.AddDataActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.ChooseMedicalActivity;
import com.canplay.medical.mvp.activity.home.ChooseMedicalActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.DoctorDetailActivity;
import com.canplay.medical.mvp.activity.home.DoctorDetailActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.activity.home.MeasureActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.MedicalDetailActivity;
import com.canplay.medical.mvp.activity.home.MedicalDetailActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.MessageActivity;
import com.canplay.medical.mvp.activity.home.MessageActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.SearchMedicalActivity;
import com.canplay.medical.mvp.activity.home.SearchMedicalActivity_MembersInjector;
import com.canplay.medical.mvp.activity.home.SmartKitActivity;
import com.canplay.medical.mvp.activity.home.SmartKitActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.AddFriendActivity;
import com.canplay.medical.mvp.activity.mine.AddFriendActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.BloodPressRecordActivity;
import com.canplay.medical.mvp.activity.mine.BloodPressRecordActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.BloodSugarRecordActivity;
import com.canplay.medical.mvp.activity.mine.BloodSugarRecordActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.FriendDetailActivity;
import com.canplay.medical.mvp.activity.mine.FriendDetailActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.MineEuipmentActivity;
import com.canplay.medical.mvp.activity.mine.MineEuipmentActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.MineHealthCenterActivity;
import com.canplay.medical.mvp.activity.mine.MineHealthCenterActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.MineInfoActivity;
import com.canplay.medical.mvp.activity.mine.MineInfoActivity_MembersInjector;
import com.canplay.medical.mvp.activity.mine.RemindSettingActivity;
import com.canplay.medical.mvp.activity.mine.RemindSettingActivity_MembersInjector;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.mvp.present.BasesPresenter_Factory;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.mvp.present.HomePresenter_Factory;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.mvp.present.LoginPresenter_Factory;
import com.canplay.medical.mvp.present.OtherPresenter;
import com.canplay.medical.mvp.present.OtherPresenter_Factory;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerBaseComponent implements BaseComponent {
  private Provider<ApiManager> apiManagerProvider;

  private Provider<LoginPresenter> loginPresenterProvider;

  private MembersInjector<LoginActivity> loginActivityMembersInjector;

  private Provider<HomePresenter> homePresenterProvider;

  private MembersInjector<DoctorDetailActivity> doctorDetailActivityMembersInjector;

  private Provider<BasesPresenter> basesPresenterProvider;

  private MembersInjector<LineCharSugarFragment> lineCharSugarFragmentMembersInjector;

  private MembersInjector<LineCharFragment> lineCharFragmentMembersInjector;

  private MembersInjector<ChartPressFragment> chartPressFragmentMembersInjector;

  private MembersInjector<ChartFragment> chartFragmentMembersInjector;

  private MembersInjector<BloodSugarRecordActivity> bloodSugarRecordActivityMembersInjector;

  private MembersInjector<BloodPressRecordActivity> bloodPressRecordActivityMembersInjector;

  private MembersInjector<FriendDetailActivity> friendDetailActivityMembersInjector;

  private MembersInjector<RemindSecondDetailActivity> remindSecondDetailActivityMembersInjector;

  private MembersInjector<RemindFirstDetailActivity> remindFirstDetailActivityMembersInjector;

  private MembersInjector<AddDataActivity> addDataActivityMembersInjector;

  private MembersInjector<TimeXRecordActivity> timeXRecordActivityMembersInjector;

  private Provider<OtherPresenter> otherPresenterProvider;

  private MembersInjector<MedicalDetailActivity> medicalDetailActivityMembersInjector;

  private MembersInjector<RemindSettingActivity> remindSettingActivityMembersInjector;

  private MembersInjector<ForgetPswActivity> forgetPswActivityMembersInjector;

  private MembersInjector<ForgetFirstActivity> forgetFirstActivityMembersInjector;

  private MembersInjector<SmartKitActivity> smartKitActivityMembersInjector;

  private MembersInjector<MeasureRemindFragment> measureRemindFragmentMembersInjector;

  private MembersInjector<MineInfoActivity> mineInfoActivityMembersInjector;

  private MembersInjector<SearchMedicalActivity> searchMedicalActivityMembersInjector;

  private MembersInjector<ChooseMedicalActivity> chooseMedicalActivityMembersInjector;

  private MembersInjector<AddBloodDataActivity> addBloodDataActivityMembersInjector;

  private MembersInjector<MeasureActivity> measureActivityMembersInjector;

  private MembersInjector<BloodRecordFragment> bloodRecordFragmentMembersInjector;

  private MembersInjector<TakeMedicineActivity> takeMedicineActivityMembersInjector;

  private MembersInjector<MineEuipmentActivity> mineEuipmentActivityMembersInjector;

  private MembersInjector<AddFriendActivity> addFriendActivityMembersInjector;

  private MembersInjector<MineHealthCenterActivity> mineHealthCenterActivityMembersInjector;

  private MembersInjector<RemindMedicatFragment> remindMedicatFragmentMembersInjector;

  private MembersInjector<HomeFragment> homeFragmentMembersInjector;

  private MembersInjector<MessageActivity> messageActivityMembersInjector;

  private MembersInjector<RegisteredActivity> registeredActivityMembersInjector;

  private MembersInjector<RegisteredSecondActivity> registeredSecondActivityMembersInjector;

  private MembersInjector<MainActivity> mainActivityMembersInjector;

  private MembersInjector<SetFragment> setFragmentMembersInjector;

  private MembersInjector<HomeDoctorFragment> homeDoctorFragmentMembersInjector;

  private DaggerBaseComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.apiManagerProvider =
        new Factory<ApiManager>() {
          private final AppComponent appComponent = builder.appComponent;

          @Override
          public ApiManager get() {
            return Preconditions.checkNotNull(
                appComponent.apiManager(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.loginPresenterProvider = LoginPresenter_Factory.create(apiManagerProvider);

    this.loginActivityMembersInjector =
        LoginActivity_MembersInjector.create(loginPresenterProvider);

    this.homePresenterProvider = HomePresenter_Factory.create(apiManagerProvider);

    this.doctorDetailActivityMembersInjector =
        DoctorDetailActivity_MembersInjector.create(homePresenterProvider);

    this.basesPresenterProvider = BasesPresenter_Factory.create(apiManagerProvider);

    this.lineCharSugarFragmentMembersInjector =
        LineCharSugarFragment_MembersInjector.create(basesPresenterProvider);

    this.lineCharFragmentMembersInjector =
        LineCharFragment_MembersInjector.create(basesPresenterProvider);

    this.chartPressFragmentMembersInjector =
        ChartPressFragment_MembersInjector.create(basesPresenterProvider);

    this.chartFragmentMembersInjector =
        ChartFragment_MembersInjector.create(basesPresenterProvider);

    this.bloodSugarRecordActivityMembersInjector =
        BloodSugarRecordActivity_MembersInjector.create(basesPresenterProvider);

    this.bloodPressRecordActivityMembersInjector =
        BloodPressRecordActivity_MembersInjector.create(basesPresenterProvider);

    this.friendDetailActivityMembersInjector =
        FriendDetailActivity_MembersInjector.create(homePresenterProvider);

    this.remindSecondDetailActivityMembersInjector =
        RemindSecondDetailActivity_MembersInjector.create(basesPresenterProvider);

    this.remindFirstDetailActivityMembersInjector =
        RemindFirstDetailActivity_MembersInjector.create(basesPresenterProvider);

    this.addDataActivityMembersInjector =
        AddDataActivity_MembersInjector.create(basesPresenterProvider);

    this.timeXRecordActivityMembersInjector =
        TimeXRecordActivity_MembersInjector.create(basesPresenterProvider);

    this.otherPresenterProvider = OtherPresenter_Factory.create(apiManagerProvider);

    this.medicalDetailActivityMembersInjector =
        MedicalDetailActivity_MembersInjector.create(otherPresenterProvider);

    this.remindSettingActivityMembersInjector =
        RemindSettingActivity_MembersInjector.create(basesPresenterProvider);

    this.forgetPswActivityMembersInjector =
        ForgetPswActivity_MembersInjector.create(loginPresenterProvider);

    this.forgetFirstActivityMembersInjector =
        ForgetFirstActivity_MembersInjector.create(loginPresenterProvider);

    this.smartKitActivityMembersInjector =
        SmartKitActivity_MembersInjector.create(basesPresenterProvider);

    this.measureRemindFragmentMembersInjector =
        MeasureRemindFragment_MembersInjector.create(homePresenterProvider);

    this.mineInfoActivityMembersInjector =
        MineInfoActivity_MembersInjector.create(basesPresenterProvider);

    this.searchMedicalActivityMembersInjector =
        SearchMedicalActivity_MembersInjector.create(homePresenterProvider);

    this.chooseMedicalActivityMembersInjector =
        ChooseMedicalActivity_MembersInjector.create(basesPresenterProvider);

    this.addBloodDataActivityMembersInjector =
        AddBloodDataActivity_MembersInjector.create(basesPresenterProvider);

    this.measureActivityMembersInjector =
        MeasureActivity_MembersInjector.create(basesPresenterProvider);

    this.bloodRecordFragmentMembersInjector =
        BloodRecordFragment_MembersInjector.create(basesPresenterProvider);

    this.takeMedicineActivityMembersInjector =
        TakeMedicineActivity_MembersInjector.create(basesPresenterProvider);

    this.mineEuipmentActivityMembersInjector =
        MineEuipmentActivity_MembersInjector.create(homePresenterProvider);

    this.addFriendActivityMembersInjector =
        AddFriendActivity_MembersInjector.create(homePresenterProvider);

    this.mineHealthCenterActivityMembersInjector =
        MineHealthCenterActivity_MembersInjector.create(homePresenterProvider);

    this.remindMedicatFragmentMembersInjector =
        RemindMedicatFragment_MembersInjector.create(homePresenterProvider);

    this.homeFragmentMembersInjector = HomeFragment_MembersInjector.create(homePresenterProvider);

    this.messageActivityMembersInjector =
        MessageActivity_MembersInjector.create(homePresenterProvider);

    this.registeredActivityMembersInjector =
        RegisteredActivity_MembersInjector.create(loginPresenterProvider);

    this.registeredSecondActivityMembersInjector =
        RegisteredSecondActivity_MembersInjector.create(loginPresenterProvider);

    this.mainActivityMembersInjector = MainActivity_MembersInjector.create(homePresenterProvider);

    this.setFragmentMembersInjector = SetFragment_MembersInjector.create(homePresenterProvider);

    this.homeDoctorFragmentMembersInjector =
        HomeDoctorFragment_MembersInjector.create(homePresenterProvider);
  }

  @Override
  public void inject(LoginActivity binderActivity) {
    loginActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(DoctorDetailActivity binderActivity) {
    doctorDetailActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(LineCharSugarFragment binderActivity) {
    lineCharSugarFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(LineCharFragment binderActivity) {
    lineCharFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(ChartPressFragment binderActivity) {
    chartPressFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(ChartFragment binderActivity) {
    chartFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(BloodSugarRecordActivity binderActivity) {
    bloodSugarRecordActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(BloodPressRecordActivity binderActivity) {
    bloodPressRecordActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(FriendDetailActivity binderActivity) {
    friendDetailActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(RemindSecondDetailActivity binderActivity) {
    remindSecondDetailActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(RemindFirstDetailActivity binderActivity) {
    remindFirstDetailActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(AddDataActivity binderActivity) {
    addDataActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(TimeXRecordActivity binderActivity) {
    timeXRecordActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MedicalDetailActivity binderActivity) {
    medicalDetailActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(RemindSettingActivity binderActivity) {
    remindSettingActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(ForgetPswActivity binderActivity) {
    forgetPswActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(ForgetFirstActivity binderActivity) {
    forgetFirstActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(SmartKitActivity binderActivity) {
    smartKitActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MeasureRemindFragment binderActivity) {
    measureRemindFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MineInfoActivity binderActivity) {
    mineInfoActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(SearchMedicalActivity binderActivity) {
    searchMedicalActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(ChooseMedicalActivity binderActivity) {
    chooseMedicalActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(AddBloodDataActivity binderActivity) {
    addBloodDataActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MeasureActivity binderActivity) {
    measureActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(BloodRecordFragment binderActivity) {
    bloodRecordFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(TakeMedicineActivity binderActivity) {
    takeMedicineActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MineEuipmentActivity binderActivity) {
    mineEuipmentActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(AddFriendActivity binderActivity) {
    addFriendActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MineHealthCenterActivity binderActivity) {
    mineHealthCenterActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(RemindMedicatFragment binderActivity) {
    remindMedicatFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(HomeFragment binderActivity) {
    homeFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MessageActivity binderActivity) {
    messageActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(RegisteredActivity binderActivity) {
    registeredActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(RegisteredSecondActivity binderActivity) {
    registeredSecondActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(MainActivity binderActivity) {
    mainActivityMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(HealthDataFragment binderActivity) {
    MembersInjectors.<HealthDataFragment>noOp().injectMembers(binderActivity);
  }

  @Override
  public void inject(SetFragment binderActivity) {
    setFragmentMembersInjector.injectMembers(binderActivity);
  }

  @Override
  public void inject(HomeDoctorFragment binderActivity) {
    homeDoctorFragmentMembersInjector.injectMembers(binderActivity);
  }

  public static final class Builder {
    private AppComponent appComponent;

    private Builder() {}

    public BaseComponent build() {
      if (appComponent == null) {
        throw new IllegalStateException(AppComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerBaseComponent(this);
    }

    public Builder appComponent(AppComponent appComponent) {
      this.appComponent = Preconditions.checkNotNull(appComponent);
      return this;
    }
  }
}
