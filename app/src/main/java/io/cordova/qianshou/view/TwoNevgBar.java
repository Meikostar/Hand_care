package io.cordova.qianshou.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.cordova.qianshou.R;


/**
 * Created by yunshang on 2016/12/20.
 */

public class TwoNevgBar extends LinearLayout implements View.OnClickListener {

    private RadioButton rdOne;
    private ImageView imgs;
    private ImageView img;
    private  RadioButton rdTwo;
    private  RadioGroup rdGroup;
    private Context context;
    private View view;
    private boolean type;
    private OnChangeListener mListener;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rd_one:
                rdGroup.check(R.id.rd_one);
                img.setVisibility(VISIBLE);
                imgs.setVisibility(INVISIBLE);
                if (mListener!=null){
                    mListener.onChagne(0);
                }
                break;
            case R.id.rd_two:
                rdGroup.check(R.id.rd_two);
                imgs.setVisibility(VISIBLE);
                img.setVisibility(INVISIBLE);
                if (mListener!=null){
                    mListener.onChagne(1);
                }
                break;

        }
    }

    public void setTitle2(String title1, String title2) {
        rdOne.setText(title1);
        rdTwo.setText(title2);

    }

    public TwoNevgBar(Context context) {
        this(context, null);
    }

    public TwoNevgBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoNevgBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray mTypedArray = null;
        if (attrs != null) {
            mTypedArray = getResources().obtainAttributes(attrs,
                    R.styleable.TwoNevgBar);
            type = mTypedArray.getBoolean(R.styleable.TwoNevgBar_types, true);

        }
        init();
        if (mTypedArray != null) {
            mTypedArray.recycle();
        }
    }

    private void init() {

        view = LayoutInflater.from(context).inflate(R.layout.nevagbar_two, this);
        rdOne = (RadioButton) view.findViewById(R.id.rd_one);
        rdTwo = (RadioButton) view.findViewById(R.id.rd_two);
        rdGroup = (RadioGroup) view.findViewById(R.id.rd_group);
        img = (ImageView) view.findViewById(R.id.img0);
        imgs = (ImageView) view.findViewById(R.id.img1);

        rdGroup.check(R.id.rd_one);
        rdOne.setOnClickListener(this);
        rdTwo.setOnClickListener(this);


    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.mListener = listener;
    }
}
