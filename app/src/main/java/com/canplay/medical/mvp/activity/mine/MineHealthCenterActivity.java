package com.canplay.medical.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Euip;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.mvp.adapter.EuipmentAdapter;
import com.canplay.medical.mvp.adapter.recycle.HealthCenterAdapter;
import com.canplay.medical.mvp.adapter.recycle.HomeDoctorRecycleAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.view.DivItemDecoration;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PhotoPopupWindow;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 健康关爱中心
 */
public class MineHealthCenterActivity extends BaseActivity implements HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private HealthCenterAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    private PhotoPopupWindow mWindowAddPhoto;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_healt_center);
        ButterKnife.bind(this);

        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        navigationBar.setNavigationBarListener(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter = new HealthCenterAdapter(this, 1);
        mSuperRecyclerView.setAdapter(adapter);
        presenter.getFriendList();

//        reflash();

       //         mSuperRecyclerView.setRefreshing(false);

//        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
//
//            @Override
//            public void onRefresh() {
//                // mSuperRecyclerView.showMoreProgress();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        presenter.getFriendList();
//                        mSuperRecyclerView.hideMoreProgress();
//                    }
//                }, 2000);
//            }
//        };
//        mSuperRecyclerView.setRefreshListener(refreshListener);
        mWindowAddPhoto = new PhotoPopupWindow(this);
        mWindowAddPhoto.setCont("解除绑定", "取消");
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
                Intent intent = new Intent(MineHealthCenterActivity.this, AddFriendActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }

            @Override
            public void navigationimg() {
                Intent intent = new Intent(MineHealthCenterActivity.this, AddFriendActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });
        adapter.setClickListener(new HealthCenterAdapter.OnItemClickListener() {
            @Override
            public void clickListener(int poiston, Friend id) {
                startActivity(new Intent(MineHealthCenterActivity.this, FriendDetailActivity.class));
            }
        });

        mWindowAddPhoto.setSureListener(new PhotoPopupWindow.ClickListener() {
            @Override
            public void clickListener(int type) {
                if (type == 1) {//解除绑定

                } else {//取消

                }
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

    public int currpage = 1;


    @Override
    public void initData() {

    }
//    public void onDataLoaded(int loadtype,final boolean haveNext, List<Friend> datas) {
//
//        if (loadtype == TYPE_PULL_REFRESH) {
//            currpage=1;
//            list.clear();
//            for (Friend info : datas) {
//                list.add(info);
//            }
//        } else {
//            for (Friend info : datas) {
//                list.add(info);
//            }
//        }
//        adapter.setDatas(list);
//        adapter.notifyDataSetChanged();
////        mSuperRecyclerView.setLoadingMore(false);
//        mSuperRecyclerView.hideMoreProgress();
//        /**
//         * 判断是否需要加载更多，与服务器的总条数比
//         */
//        if (haveNext) {
//            mSuperRecyclerView.setupMoreListener(new OnMoreListener() {
//                @Override
//                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
//                    currpage++;
//                    mSuperRecyclerView.showMoreProgress();
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (haveNext)
//                                mSuperRecyclerView.hideMoreProgress();
////                            presenter.getAppOrderList("",currpage,state,TYPE_PULL_MORE);
//
//                        }
//                    }, 2000);
//                }
//            }, 1);
//        } else {
//            mSuperRecyclerView.removeMoreListener();
//            mSuperRecyclerView.hideMoreProgress();
//
//        }
//    }
    private List<Friend> list ;
    @Override
    public <T> void toEntity(T entity) {

        list= (List<Friend>) entity;
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
