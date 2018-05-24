package com.canplay.medical.mvp.activity.mine;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.adapter.recycle.PressRecordReCycleAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.DivItemDecoration;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public <T> void toEntity(T entity, int type) {
        List<Record> lists = (List<Record>) entity;
        if (lists.size() == 0) {

            loadingView.showPager(LoadingPager.STATE_EMPTY);


        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
        onDataLoaded(type, data.size() == cout, lists);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }


}
