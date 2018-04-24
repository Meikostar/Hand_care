package com.canplay.medical.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.view.HistogramView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by mykar on 17/4/10.
 */
public class LineCharFragment extends BaseFragment {



    @BindView(R.id.hgm_kpi_first)
    HistogramView hgmKpiFirst;
    private String user_class;

    private int type = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, null);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }



}
