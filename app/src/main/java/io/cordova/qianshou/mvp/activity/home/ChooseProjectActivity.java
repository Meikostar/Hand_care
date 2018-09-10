package io.cordova.qianshou.mvp.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.mvp.adapter.TimeAddAdapter;
import io.cordova.qianshou.view.MCheckBox;
import io.cordova.qianshou.view.NavigationBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.view.MCheckBox;
import io.cordova.qianshou.view.NavigationBar;

/**
 * 选择测量项目
 */
public class ChooseProjectActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.cb_press)
    MCheckBox cbPress;
    @BindView(R.id.ll_press)
    LinearLayout llPress;
    @BindView(R.id.cb_sugar)
    MCheckBox cbSugar;
    @BindView(R.id.ll_sugar)
    LinearLayout llSugar;

    private int status;//1血压2 血糖
    @Override
    public void initViews() {
        setContentView(R.layout.activity_choose_project);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        status=getIntent().getIntExtra("status",1);
        if(status!=1){
            cbPress.setChecked(false);
            cbSugar.setChecked(true);
        }


    }

    @Override
    public void bindEvents() {
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
                Intent intent = new Intent();

                if(cbPress.isCheck()){
                  intent.putExtra("type",1);
              }else {
                    intent.putExtra("type",2);
              }
              setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void navigationimg() {

            }
        });
        llPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbPress.isCheck()){
                    cbPress.setChecked(false);
                    cbSugar.setChecked(true);
                }else {
                    cbPress.setChecked(true);
                    cbSugar.setChecked(false);
                }
            }
        });
        llSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbSugar.isCheck()){
                    cbPress.setChecked(true);
                    cbSugar.setChecked(false);
                }else {
                    cbPress.setChecked(false);
                    cbSugar.setChecked(true);
                }
            }
        });
    }

    private Map<String, String> map = new HashMap<>();
    private List<String> data = new ArrayList<>();

    @Override
    public void initData() {

    }



}
