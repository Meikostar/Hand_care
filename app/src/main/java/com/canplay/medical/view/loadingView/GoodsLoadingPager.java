package com.canplay.medical.view.loadingView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;


/**
 * Created by linquandong on 16/1/9.
 */
public class GoodsLoadingPager extends LoadingPager {
    private View btn_empty_retry;
    private TextView txt_desc;

    public GoodsLoadingPager(Context context) {
        super(context);
    }

    public GoodsLoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoodsLoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    protected View creatEmptyView(LayoutInflater inflater) {
        mEmptyView = inflater.inflate(R.layout.goods_emtyview, null);
        btn_empty_retry = mEmptyView.findViewById(R.id.btn_empty_retry);
        txt_desc = (TextView) mEmptyView.findViewById(R.id.txt_desc);
        btn_empty_retry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPager(STATE_LOADING);
                if(mNetworkListener != null) {
                    mNetworkListener.networkRetry();
                }
            }
        });
        return mEmptyView;
    }

    public void setDescText(String str){
        txt_desc.setText(str);
    }

    public void setBtnVisible(int visible){
        btn_empty_retry.setVisibility(visible);
    }
}
