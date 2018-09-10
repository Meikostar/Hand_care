package io.cordova.qianshou.mvp.activity.home;

import android.Manifest;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.mvp.activity.mine.RemindSettingActivity;
import io.cordova.qianshou.mvp.adapter.SearchMedicalAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;
import com.google.zxing.client.android.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.loadingView.BaseLoadingPager;
import io.cordova.qianshou.view.loadingView.LoadingPager;

/**
 * 选择药物
 */
public class ChooseMedicalActivity extends BaseActivity implements BaseContract.View {

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.top_view_back)
    ImageView topViewBack;
    @BindView(R.id.topview_left_layout)
    LinearLayout topviewLeftLayout;
    @BindView(R.id.tv_scan)
    ImageView tvScan;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.listview_all_city)
    ListView listview;
    @BindView(R.id.loadingView)
    LoadingPager loadingView;

    private SearchMedicalAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_choose_medical);
        ButterKnife.bind(this);
        etSearch.setdelteIconHilde();
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        adapter = new SearchMedicalAdapter(this);
        listview.setAdapter(adapter);
        loadingView.showPager(8);
//        presenter.getMedicineList();


    }

    private List<Medicines> datas = new ArrayList<>();

    @Override
    public void bindEvents() {

        adapter.setClickListener(new SearchMedicalAdapter.ClickListener() {
            @Override
            public void clickListener(int type, String id) {
                Intent intent = new Intent(ChooseMedicalActivity.this, MedicalDetailActivity.class);
                intent.putExtra("name",id);
                startActivity(intent);
            }
        });
        etSearch.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    presenter.searchMedicine(s.toString());
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Medicines> data = adapter.getData();
                if (data == null) {
                    showToasts("你还未选择药物");
                    return;
                }
                for (Medicines medicine : data) {
                    if (medicine.isCheck) {

                        datas.add(medicine);
                    }
                }
                if (datas.size() > 0) {
                    Medicines medicines = new Medicines();
                    medicines.data=datas;
                    Intent intent = new Intent();
                    intent.putExtra("data",medicines);
                    setResult(RESULT_OK,intent);
//                    RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.CHOOSMEDICALSS, datas));
                    finish();
                } else {
                    showToasts("你还未选择药物");
                }
            }
        });
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (TextUtil.isNotEmpty(etSearch.getText().toString())) {
                        type=1;
                        loadingView.showPager(BaseLoadingPager.STATE_LOADING);
                        presenter.searchMedicine(etSearch.getText().toString());
                    }


            }
        });
//        mLetterBar.setOverlay(overlay);
//        mLetterBar.setOnLetterChangedListener(new SideLetterBars.OnLetterChangedListener() {
//            @Override
//            public void onLetterChanged(String letter) {
//                int position = adapter.getLetterPosition(letter);
//                listview.setSelection(position);
//            }
//        });
        tvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionGen.with(ChooseMedicalActivity.this)//动态权限
                        .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                        .permissions(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
            }
        });
        topViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private int type;
    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Intent intent = new Intent(ChooseMedicalActivity.this, CaptureActivity.class);
         /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
         * 也可以不传这个参数
         * 不传的话  默认都为默认不震动  其他都为true
         * */

        //ZxingConfig config = new ZxingConfig();
        //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
        //config.setPlayBeep(true);//是否播放提示音
        //config.setShake(true);//是否震动
        //config.setShowAlbum(true);//是否显示相册
        //config.setShowFlashLight(true);//是否显示闪光灯
        //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    private int REQUEST_CODE_SCAN = 6;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra("scan_result");
                showToasts("扫描结果为：" + content);
//                result.setText("扫描结果为：" + content);
            }
        }
    }


    @Override
    public void initData() {

    }


    private List<Medicines> data;

    @Override
    public <T> void toEntity(T entity, int types) {


        data = (List<Medicines>) entity;
        if(type==1){
            type=0;
            if (data.size() == 0) {

                loadingView.showPager(LoadingPager.STATE_EMPTY);

            } else {
                loadingView.showPager(LoadingPager.STATE_SUCCEED);
            }
        }

        if (data.size() > 0) {
            tvSearch.setText("取消");
            loadingView.showPager(LoadingPager.STATE_SUCCEED);
        }
        adapter.setData(data, 0);

    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }



}
