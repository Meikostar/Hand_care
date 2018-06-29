package com.canplay.medical.mvp.activity.health;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.adapter.recycle.UserTimeAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.DivItemDecoration;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_TimeRecord;
import com.canplay.medical.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间轴事件
 */
public class TimeXRecordActivity extends BaseActivity implements BaseContract.View {
    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;

    private UserTimeAdapter adapter;
    private PopView_TimeRecord popView_timeRecord;
    private String title;
    private String category;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    public int currpage = 0;
    private int cout = 10;
    private int total = 0;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_timex);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("name");
        if (TextUtil.isNotEmpty(title)) {
            navigationBar.setNaviTitle(title);
        }
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        navigationBar.setNavigationBarListener(this);
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter = new UserTimeAdapter(this);
        mSuperRecyclerView.setAdapter(adapter);

//        reflash();

        reflash();
        // mSuperRecyclerView.setRefreshing(false);
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // mSuperRecyclerView.showMoreProgress();
                if (TextUtil.isEmpty(category)) {
                    presenter.getTimeRecord(TYPE_PULL_REFRESH, total + "", cout + "");
                } else {
                    presenter.getMeasureRecord(TYPE_PULL_REFRESH, category, total + "", cout + "");
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


        popView_timeRecord = new PopView_TimeRecord(this, 1);
        popView_timeRecord.setView(line);

        popView_timeRecord.setClickListener(new PopView_TimeRecord.ItemCliskListeners() {
            @Override
            public void clickListener(int poition) {
                switch (poition) {
                    case 0://全部
                        category = "";
                        reflash();
                        break;
                    case 1://血压
                        category = "Medicine";
                        reflash();
                        break;
                    case 2://服药
                        category = "Measurement";

                        reflash();
                        break;
                    case 3://血糖
                        category = "Measurement";
                        reflash();
                        break;

                }
                popView_timeRecord.dismiss();
            }

        });
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
        if (list.size() == 0) {

            loadingView.showPager(LoadingPager.STATE_EMPTY);

        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
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
                            if (TextUtil.isEmpty(category)) {
                                presenter.getTimeRecord(TYPE_PULL_MORE, cout * currpage + "", cout + "");
                            } else {
                                presenter.getMeasureRecord(TYPE_PULL_MORE, category, cout * currpage + "", cout + "");
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
    public void bindEvents() {
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                popView_timeRecord.showPopView();
            }

            @Override
            public void navigationimg() {

            }
        });


    }


    @Override
    public void initData() {

    }

    @Override
    public <T> void toEntity(T entity, int type) {
        List<Record> lists = (List<Record>) entity;
        data.clear();
        for (Record record : lists) {
            for (Record record1 : record.items) {
                data.add(record1);
            }
        }
        onDataLoaded(type, data.size() == cout, data);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }



}
