package com.canplay.medical.mvp.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;

public class TuranActivity extends BaseActivity {



    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_info);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent1 = this.getIntent();
        byte[] alarm_clocks = intent1.getByteArrayExtra("alarm_clock");

        String time = intent1.getStringExtra("alarm_ids");
        String times = intent1.getStringExtra("msg");

    }

    @Override
    public void bindEvents() {

    }

    @Override
    public void initData() {

    }
}
