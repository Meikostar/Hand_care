// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.fragment;

import com.canplay.medical.mvp.present.HomePresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MeasureRemindFragment_MembersInjector
    implements MembersInjector<MeasureRemindFragment> {
  private final Provider<HomePresenter> presenterProvider;

  public MeasureRemindFragment_MembersInjector(Provider<HomePresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<MeasureRemindFragment> create(
      Provider<HomePresenter> presenterProvider) {
    return new MeasureRemindFragment_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(MeasureRemindFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      MeasureRemindFragment instance, Provider<HomePresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
