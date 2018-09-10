package io.cordova.qianshou.base;

import io.cordova.qianshou.base.manager.ApiManager;

import javax.inject.Singleton;

import dagger.Component;
import io.cordova.qianshou.base.manager.ApiManager;

/**
 * 全局控制器
 * Created by peter on 2016/9/11.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent{


    ApiManager apiManager();  // 所有Api请求的管理类

    void inject(BaseApplication application);

    void inject(BaseAllActivity baseActivity);

}
