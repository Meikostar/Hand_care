package com.canplay.medical.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.KPI;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.view.HistogramView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by mykar on 17/4/10.
 */
public class LineCharSugarFragment extends BaseFragment implements BaseContract.View {

    @Inject
    BasesPresenter presenter;

    @BindView(R.id.hgm_kpi_first)
    HistogramView hgmKpiFirst;
    private String user_class;

    private int type = 1;

    public LineCharSugarFragment(int type) {
        this.type = type;
    }

    public LineCharSugarFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Subscription mSubscription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_sugar, null);
        ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        if (type == 1) {
            presenter.getDayBloodRecord(7);
        } else if (type == 2) {
            presenter.getDayBloodRecord(15);
        } else {
            presenter.getDayBloodRecord(30);
        }
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if (type == 1) {
                    presenter.getDayBloodRecord(7);
                } else if (type == 2) {
                    presenter.getDayBloodRecord(15);
                } else {
                    presenter.getDayBloodRecord(30);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
        initView();
        return view;
    }

    private void initView() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private List<KPI> data = new ArrayList<>();
    private String[] xWeeks;
    private int i;
    private List<Record> list = new ArrayList<>();

    @Override
    public <T> void toEntity(T entity, int type) {
        i = 0;
        list = (List<Record>) entity;
        data.clear();
        if (list != null && list.size() != 0) {


            if (list.size() < type) {
                xWeeks = new String[type];
                for (int i = 0; i < type; i++) {
                    xWeeks[i] = i + 1 + "";
                }
            } else {
                xWeeks = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    xWeeks[i] = i + 1 + "";
                }
            }

            for (Record record : list) {

                KPI kpi = new KPI();
                kpi.xdata = i;
                kpi.ydata = Double.valueOf(record.bgl);

                if (kpi.ydata < 3.9|| kpi.ydata >6.1) {
                    kpi.colorType = 1;
                }

                data.add(kpi);

                i++;
            }
            hgmKpiFirst.setDatas(data,  xWeeks);
            hgmKpiFirst.invalidate();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }
    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
