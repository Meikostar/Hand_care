package io.cordova.qianshou.mvp.activity.mine;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.decode.CodeCreator;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.NavigationBar;

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

    private String name;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_code);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        imgs= BaseApplication.avatar+ SpUtil.getInstance().getAva();
        name=getIntent().getStringExtra("name");
        if(TextUtil.isNotEmpty(name)){
            tvName.setText(name);
        }
        Bitmap qrImage = CodeCreator.createQRImage("user###" + SpUtil.getInstance().getUserId(), 400, 400);
        ivCode.setImageBitmap(qrImage);

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
                     img.setImageBitmap(myBitmap);
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
