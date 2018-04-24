// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.account;

import com.canplay.medical.mvp.present.LoginPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ForgetPswActivity_MembersInjector implements MembersInjector<ForgetPswActivity> {
  private final Provider<LoginPresenter> presenterProvider;

  public ForgetPswActivity_MembersInjector(Provider<LoginPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<ForgetPswActivity> create(
      Provider<LoginPresenter> presenterProvider) {
    return new ForgetPswActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(ForgetPswActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      ForgetPswActivity instance, Provider<LoginPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
