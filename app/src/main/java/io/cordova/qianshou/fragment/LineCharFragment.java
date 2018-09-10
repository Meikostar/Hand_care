package io.cordova.qianshou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseFragment;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.KPI;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.view.HistogramPressView;
import io.cordova.qianshou.view.HistogramView;

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
public class LineCharFragment extends BaseFragment implements BaseContract.View {

    @Inject
    BasesPresenter presenter;

    @BindView(R.id.hgm_kpi_first)
    HistogramPressView hgmKpiFirst;
    private String user_class;

    private int type = 1;

    public LineCharFragment(int type) {
        this.type = type;
    }

    public LineCharFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, null);
        ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        if (type == 1) {
            presenter.getDayBloodPressRecord(7);
//            presenter.getDayBloodPressRecord(7);
        } else if (type == 2) {
            presenter.getDayBloodPressRecord(15);
        } else {
            presenter.getDayBloodPressRecord(30);
        }
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.BLOODORSUGAR) {
                    if (type == 1) {
                        presenter.getDayBloodPressRecord(7);
                    } else if (type == 2) {
                        presenter.getDayBloodPressRecord(15);
                    } else {
                        presenter.getDayBloodPressRecord(30);
//                        presenter.getDayBloodPressRecord(30);
//                        presenter.getDayBloodPressRecord(30);
//                        presenter.getDayBloodPressRecord(30);
                    }
                    ;
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
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    private Subscription mSubscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    private List<KPI> data = new ArrayList<>();
    private List<KPI> data2 = new ArrayList<>();
    private List<KPI> data3 = new ArrayList<>();
    private String[] xWeeks;
    //"high":95.0,"low":130.0,"pulse":75.0,
    private List<Record> list = new ArrayList<>();
    private int i;

    @Override
    public <T> void toEntity(T entity, int type) {
        i = 0;
        list = (List<Record>) entity;
        data.clear();
        data2.clear();
        data3.clear();
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
                kpi.ydata = Double.valueOf(record.high);
                KPI kpi1 = new KPI();
                kpi1.xdata = i;
                kpi1.ydata = Double.valueOf(record.low);
                KPI kpi2 = new KPI();
                kpi2.xdata = i;
                kpi2.ydata = Double.valueOf(record.pulse);
                if (kpi.ydata < 90 || kpi.ydata > 139) {
                    kpi.colorType = 1;
                }
                if (kpi1.ydata < 60 || kpi1.ydata > 90) {
                    kpi1.colorType = 1;
                }
                if (kpi1.ydata < 60 || kpi1.ydata > 100) {
                    kpi2.colorType = 1;
                }
                data.add(kpi);
                data2.add(kpi1);
                data3.add(kpi2);
                i++;
            }
            hgmKpiFirst.setDatas(data, data2, data3, xWeeks);
            hgmKpiFirst.invalidate();
        }


    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
