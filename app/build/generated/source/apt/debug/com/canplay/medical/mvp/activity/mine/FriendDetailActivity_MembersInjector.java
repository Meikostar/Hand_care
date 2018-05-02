// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.mine;

import com.canplay.medical.mvp.present.HomePresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class FriendDetailActivity_MembersInjector
    implements MembersInjector<FriendDetailActivity> {
  private final Provider<HomePresenter> presenterProvider;

  public FriendDetailActivity_MembersInjector(Provider<HomePresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<FriendDetailActivity> create(
      Provider<HomePresenter> presenterProvider) {
    return new FriendDetailActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(FriendDetailActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      FriendDetailActivity instance, Provider<HomePresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}