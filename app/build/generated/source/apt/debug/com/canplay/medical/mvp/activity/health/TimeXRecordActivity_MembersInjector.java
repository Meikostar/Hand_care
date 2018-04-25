// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.health;

import com.canplay.medical.mvp.present.BasesPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TimeXRecordActivity_MembersInjector
    implements MembersInjector<TimeXRecordActivity> {
  private final Provider<BasesPresenter> presenterProvider;

  public TimeXRecordActivity_MembersInjector(Provider<BasesPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<TimeXRecordActivity> create(
      Provider<BasesPresenter> presenterProvider) {
    return new TimeXRecordActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(TimeXRecordActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      TimeXRecordActivity instance, Provider<BasesPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}