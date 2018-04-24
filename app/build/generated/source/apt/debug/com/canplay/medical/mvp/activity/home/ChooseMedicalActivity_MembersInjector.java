// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.home;

import com.canplay.medical.mvp.present.BasesPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ChooseMedicalActivity_MembersInjector
    implements MembersInjector<ChooseMedicalActivity> {
  private final Provider<BasesPresenter> presenterProvider;

  public ChooseMedicalActivity_MembersInjector(Provider<BasesPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<ChooseMedicalActivity> create(
      Provider<BasesPresenter> presenterProvider) {
    return new ChooseMedicalActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(ChooseMedicalActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      ChooseMedicalActivity instance, Provider<BasesPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
