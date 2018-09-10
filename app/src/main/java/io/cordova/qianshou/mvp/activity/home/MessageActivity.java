package io.cordova.qianshou.mvp.activity.home;

import android.view.View;
import android.widget.ListView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Message;
import io.cordova.qianshou.mvp.adapter.MessageAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Message;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;

/**
 * 消息
 */
public class MessageActivity extends BaseActivity implements HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_menu)
    ListView rlMenu;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;


    private MessageAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        presenter.getMessageList();
        loadingView.showPager(BaseLoadingPager.STATE_LOADING);
        navigationBar.setNavigationBarListener(this);
        adapter = new MessageAdapter(this);
        rlMenu.setAdapter(adapter);

    }


    @Override
    public void bindEvents() {
        adapter.setClickListener(new MessageAdapter.ItemCliks() {
            @Override
            public void getItem(Message menu, int type) {

            }
        });

    }


    @Override
    public void initData() {

    }

    private List<Message> data;

    @Override
    public <T> void toEntity(T entity, int type) {
        dimessProgress();
        data = (List<Message>) entity;
        if (data.size() == 0) {

            loadingView.showPager(LoadingPager.STATE_EMPTY);
            loadingView.setContent("暂无消息");
        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
        adapter.setData(data);
    }

    @Override
    public void toNextStep(int type) {
        dimessProgress();
    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
        dimessProgress();
    }


}
