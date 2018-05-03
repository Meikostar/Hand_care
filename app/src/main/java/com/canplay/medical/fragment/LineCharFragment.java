package com.canplay.medical.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.view.HistogramView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by mykar on 17/4/10.
 */
public class LineCharFragment extends BaseFragment implements BaseContract.View{

    @Inject
    BasesPresenter presenter;

    @BindView(R.id.hgm_kpi_first)
    HistogramView hgmKpiFirst;
    private String user_class;

    private int type = 1;
    public LineCharFragment(int type){
        this.type=type;
    }
    public LineCharFragment(){
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

        if(type==1){
            presenter.getDayBloodRecord("7");
        }else if(type==2){
            presenter.getDayBloodRecord("15");
        }else {
            presenter.getDayBloodRecord("30");
        }
        initView();
        return view;
    }

    private void initView() {

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public <T> void toEntity(T entity, int type) {

    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
