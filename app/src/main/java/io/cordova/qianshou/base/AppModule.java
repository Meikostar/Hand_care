package io.cordova.qianshou.base;

import android.content.Context;

import io.cordova.qianshou.base.manager.ApiManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.cordova.qianshou.base.manager.ApiManager;

/**
 * Created by peter on 2016/9/11.
 */
@Module
public class AppModule {
    private static final String ENDPOINT = "";

    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public ApiManager provideApiManager() {
        return ApiManager.getInstance(application, true);
    }

}
