package io.cordova.qianshou.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;


/**
 * Created by Administrator on 2017/10/26.
 */

public class SplashActivity extends AppCompatActivity {
    private int status;
    private ImageView img;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        img= (ImageView) findViewById(R.id.iv_img);
        status = getIntent().getIntExtra("status", 0);
       final String userId = SpUtil.getInstance().getUserId();
        status=getIntent().getIntExtra("type",0);

        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtil.isNotEmpty(userId)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("type",status);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.putExtra("type",status);
                    startActivity(intent);
                    finish();
                }


            }
        }, 2000);


    }
}
