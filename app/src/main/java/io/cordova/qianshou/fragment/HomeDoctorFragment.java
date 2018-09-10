package io.cordova.qianshou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseFragment;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.mvp.activity.home.DoctorDetailActivity;
import io.cordova.qianshou.mvp.activity.mine.AddFriendActivity;
import io.cordova.qianshou.mvp.adapter.recycle.HomeDoctorRecycleAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.view.DivItemDecoration;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by mykar on 17/4/10.
 */
public class HomeDoctorFragment extends BaseFragment implements View.OnClickListener, HomeContract.View {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.navigationbar_title)
    TextView navigationbarTitle;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;


    //    private List<AD> list = new ArrayList<>();
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private HomeDoctorRecycleAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
//        loadingView.showPager(BaseLoadingPager.STATE_LOADING);
        presenter.getDoctorList();
        initView();
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private Subscription mSubscription;

    private void initListener() {


        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.DOCTOR) {
                    reflash();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddFriendActivity.class));
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

    private void initView() {

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter = new HomeDoctorRecycleAdapter(getActivity());
        mSuperRecyclerView.setAdapter(adapter);
        reflash();
        // mSuperRecyclerView.setRefreshing(false);
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // mSuperRecyclerView.showMoreProgress();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mSuperRecyclerView != null) {
                            mSuperRecyclerView.hideMoreProgress();
                        }

                        presenter.getDoctorList();
                    }
                }, 2000);
            }
        };
        mSuperRecyclerView.setRefreshListener(refreshListener);

        adapter.setClickListener(new HomeDoctorRecycleAdapter.OnItemClickListener() {
            @Override
            public void clickListener(int poiston, Friend data) {
                Intent intent = new Intent(getActivity(), DoctorDetailActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    private List<Friend> list;

    @Override
    public <T> void toEntity(T entity, int type) {
        list = (List<Friend>) entity;
        if (list!=null&&list.size() == 0) {
            loadingView.setContent("你还没有设置家庭医生，点击上方+号键签约家庭医生服务");
            loadingView.showPager(LoadingPager.STATE_EMPTY);

        } else {
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
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
