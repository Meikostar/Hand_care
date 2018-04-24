// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.base;

import com.canplay.medical.base.manager.ApiManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AppModule_ProvideApiManagerFactory implements Factory<ApiManager> {
  private final AppModule module;

  public AppModule_ProvideApiManagerFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public ApiManager get() {
    return Preconditions.checkNotNull(
        module.provideApiManager(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ApiManager> create(AppModule module) {
    return new AppModule_ProvideApiManagerFactory(module);
  }
}
