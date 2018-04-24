// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.mine;

import com.canplay.medical.mvp.present.BasesPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class RemindSettingActivity_MembersInjector
    implements MembersInjector<RemindSettingActivity> {
  private final Provider<BasesPresenter> presenterProvider;

  public RemindSettingActivity_MembersInjector(Provider<BasesPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<RemindSettingActivity> create(
      Provider<BasesPresenter> presenterProvider) {
    return new RemindSettingActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(RemindSettingActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      RemindSettingActivity instance, Provider<BasesPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
