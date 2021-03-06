package io.cordova.qianshou.mvp.activity.home;

import android.text.Editable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.mvp.adapter.SearchMedicalAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;

/**
 * 选择药物search
 */
public class SearchMedicalActivity extends BaseActivity implements HomeContract.View{

    @Inject
    HomePresenter presenter;

    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.topview_left_layout)
    LinearLayout topviewLeftLayout;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.listview_all_city)
    ListView listview;
    private SearchMedicalAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_search_medical);
        ButterKnife.bind(this);

        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        adapter = new SearchMedicalAdapter(this);
        listview.setAdapter(adapter);


    }

    @Override
    public void bindEvents() {
        etSearch.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(TextUtil.isNotEmpty(s.toString())){
                    presenter.searchMedicine(s.toString());
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==0){
                    if(TextUtil.isNotEmpty(etSearch.getText().toString())){
                        presenter.searchMedicine(etSearch.getText().toString());
                    }
                }else {
                    type=0;
                    etSearch.setText("");
                    tvSearch.setText("搜索");
                }

            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<ORDER> data = adapter.getData();
//                for(ORDER order:data){
//
//                }
            }
        });

    }


    @Override
    public void initData() {

    }

    private int type;
    private List<Medicines> data;
    @Override
    public <T> void toEntity(T entity,int type) {
        type=1;
        tvSearch.setText("取消");
        data= (List<Medicines>) entity;
        adapter.setData(data,0);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
