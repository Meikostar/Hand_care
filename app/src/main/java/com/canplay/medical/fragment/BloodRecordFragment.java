package com.canplay.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.activity.home.DoctorDetailActivity;
import com.canplay.medical.mvp.adapter.RemindMeasureAdapter;
import com.canplay.medical.mvp.adapter.recycle.BloodRecordRecycleAdapter;
import com.canplay.medical.mvp.adapter.recycle.HomeDoctorRecycleAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.view.DivItemDecoration;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 血压测量记录 （列表）
 */
public class BloodRecordFragment extends BaseFragment implements  BaseContract.View {
    @Inject
    BasesPresenter presenter;

    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    private BloodRecordRecycleAdapter adapter;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    Unbinder unbinder;

    private int type;//0代表血压记录1代表血糖记录
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void setType(int type){
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_record, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        initView();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private int cout=12;
    private int total=0;
    private void initView() {

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter = new BloodRecordRecycleAdapter(getActivity());
        mSuperRecyclerView.setAdapter(adapter);
        adapter.setType(type);
//        reflash();
        // mSuperRecyclerView.setRefreshing(false);
        reflash();
        // mSuperRecyclerView.setRefreshing(false);
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // mSuperRecyclerView.showMoreProgress();
                if(type==0){
                    presenter.getBloodPressList(TYPE_PULL_REFRESH,total+"",cout+"");
                }else {
                    presenter.getBloodList(TYPE_PULL_REFRESH,total+"",cout+"");
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mSuperRecyclerView!=null){
                            mSuperRecyclerView.hideMoreProgress();
                        }

                    }
                }, 2000);
            }
        };
        mSuperRecyclerView.setRefreshListener(refreshListener);



    }
    public List<Record> list=new ArrayList<>();
    public List<Record> data=new ArrayList<>();
    public void onDataLoaded(int loadtype,final boolean haveNext, List<Record> datas) {

        if (loadtype == TYPE_PULL_REFRESH) {
            currpage=1;
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

                            if(type==0){
                                presenter.getBloodPressList(TYPE_PULL_REFRESH,cout*currpage+"",cout+"");

                            }else {
                                presenter.getBloodList(TYPE_PULL_REFRESH,cout*currpage+"",cout+"");

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
    public int currpage=1;
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public <T> void toEntity(T entity, int type) {
        List<Record>     lists= (List<Record>) entity;

        onDataLoaded(type,data.size()==cout,lists);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
