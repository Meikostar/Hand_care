package io.cordova.qianshou.mvp.activity.mine;


import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.mvp.adapter.recycle.PressRecordReCycleAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.DivItemDecoration;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.DivItemDecoration;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.loadingView.LoadingPager;

/**
 * x血压测量记录
 */
public class BloodPressRecordActivity extends BaseActivity implements BaseContract.View {
    @Inject
    BasesPresenter presenter;


    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;

    private PressRecordReCycleAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    private int id;
    private int currpage = 0;

    private String userId;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_blood_press_record);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        String id = getIntent().getStringExtra("id");
        if (TextUtil.isNotEmpty(id)) {
            userId = id;
        } else {
            userId = SpUtil.getInstance().getUserId();
        }
        navigationBar.setNavigationBarListener(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter = new PressRecordReCycleAdapter(this, 0);
        mSuperRecyclerView.setAdapter(adapter);

        reflash();
    }

    private void reflash() {
        if (mSuperRecyclerView != null) {
            //实现自动下拉刷新功能
            mSuperRecyclerView.getSwipeToRefresh().post(new Runnable() {
                @Override
                public void run() {
                    mSuperRecyclerView.setRefreshing(true);//执行下拉刷新的动画
                    refreshListener.onRefresh();//执行数据加载操作
                }
            });
        }
    }

    private int cout = 10;
    private int total = 0;

    @Override
    public void bindEvents() {
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // mSuperRecyclerView.showMoreProgress();
                if (type == 0) {
                    presenter.getBloodPressList(TYPE_PULL_REFRESH, total + "", cout + "", userId);
                } else {
                    presenter.getBloodList(TYPE_PULL_REFRESH, total + "", cout + "", userId);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mSuperRecyclerView != null) {
                            mSuperRecyclerView.hideMoreProgress();
                        }

                    }
                }, 2000);
            }
        };
        mSuperRecyclerView.setRefreshListener(refreshListener);

    }

    public List<Record> list = new ArrayList<>();
    public List<Record> data = new ArrayList<>();

    public void onDataLoaded(int loadtype, final boolean haveNext, List<Record> datas) {

        if (loadtype == TYPE_PULL_REFRESH) {
            currpage = 0;
            list.clear();
            for (Record info : datas) {
                list.add(info);
            }
        } else {
            for (Record info : datas) {
                list.add(info);
            }
        }
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
//        mSuperRecyclerView.setLoadingMore(false);
        mSuperRecyclerView.hideMoreProgress();
        /**
         * 判断是否需要加载更多，与服务器的总条数比
         */
        if (haveNext) {
            mSuperRecyclerView.setupMoreListener(new OnMoreListener() {
                @Override
                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                    currpage++;
                    mSuperRecyclerView.showMoreProgress();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (haveNext)
                                mSuperRecyclerView.hideMoreProgress();

                            if (type == 0) {
                                presenter.getBloodPressList(TYPE_PULL_MORE, cout * currpage + "", cout + "", userId);

                            } else {
                                presenter.getBloodList(TYPE_PULL_MORE, cout * currpage + "", cout + "", userId);

                            }

                        }
                    }, 2000);
                }
            }, 1);
        } else {
            mSuperRecyclerView.removeMoreListener();
            mSuperRecyclerView.hideMoreProgress();

        }
    }


    @Override
    public void initData() {

    }

    @Override
    public <T> void toEntity(T entity, int types) {
        List<Record> lists = (List<Record>) entity;
        if (lists.size() == 0) {
            if (type == 0) {
                loadingView.setContent("暂无血压测量记录");
            } else {
                loadingView.setContent("暂无血糖测量记录");
            }
            loadingView.showPager(LoadingPager.STATE_EMPTY);


        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
        onDataLoaded(types, data.size() == cout, lists);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }


}
