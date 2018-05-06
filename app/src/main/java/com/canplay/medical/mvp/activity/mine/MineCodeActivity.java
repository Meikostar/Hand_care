package com.canplay.medical.mvp.activity.mine;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.NavigationBar;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.decode.CodeCreator;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的二维码
 */
public class MineCodeActivity extends BaseActivity {


    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    private String imgs;


    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_code);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        imgs=SpUtil.getInstance().getAva();

        Glide.with(this).load(imgs).asBitmap().placeholder(R.drawable.moren).into(img);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = null;
                try {
                    Bitmap myBitmap = Glide.with(MineCodeActivity.this)
                            .load(imgs)
                            .asBitmap() //必须
                            .centerCrop()
                            .into(500, 500)
                            .get();
                    final Bitmap qrCode = CodeCreator.createQRCode("user###"+SpUtil.getInstance().getUserId(), 400, 400, myBitmap);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ivCode.setImageBitmap(qrCode);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Bitmap qrCode;
                            try {
                                qrCode = CodeCreator.createQRCode("user###"+SpUtil.getInstance().getUserId(), 400, 400, null);
                                ivCode.setImageBitmap(qrCode);
                            } catch (WriterException e1) {
                                e1.printStackTrace();
                            }

                        }
                    });
                }
            }
        }).start();


    }

    @Override
    public void bindEvents() {


    }


    @Override
    public void initData() {

    }


}
