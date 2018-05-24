package com.canplay.medical.view.loadingView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.canplay.medical.R;


/**
 * Created by linquandong on 15/3/26.
 */
public class LoadingPager extends BaseLoadingPager{
    public Button btn_retry;
    private View btn_empty_retry;
    private TextView text;

    public LoadingPager(Context context) {
        super(context);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    protected View creatLoadingView(LayoutInflater inflater) {
        mLoadingView = inflater.inflate(R.layout.fragment_baseloader, null);
        return mLoadingView;
    }
     public void setContent(String content){
         text.setText(content);
     }
    protected View creatErrorView(LayoutInflater inflater) {
        mErrorView = inflater.inflate(R.layout.fragment_baseerror, null);
        btn_retry = (Button) mErrorView.findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPager(STATE_LOADING);
                if(mNetworkListener != null){
                    mNetworkListener.networkRetry();
                }
            }
        });
        return mErrorView;
    }

    protected View creatEmptyView(LayoutInflater inflater) {
        mEmptyView = inflater.inflate(R.layout.fragment_emtyview, null);
        btn_empty_retry = mEmptyView.findViewById(R.id.btn_empty_retry);
        text = mEmptyView.findViewById(R.id.txt_desc);
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
}
