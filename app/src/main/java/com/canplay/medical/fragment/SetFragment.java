package com.canplay.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.bean.Euipt;
import com.canplay.medical.bean.unBind;
import com.canplay.medical.mvp.activity.mine.MineInfoActivity;
import com.canplay.medical.mvp.activity.mine.SettingActivity;
import com.canplay.medical.mvp.adapter.EuipmentAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.view.EditorNameDialog;
import com.canplay.medical.view.PhotoPopupWindow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by mykar on 17/4/10.
 */
public class SetFragment extends BaseFragment implements View.OnClickListener , HomeContract.View {
@Inject
HomePresenter presenter;
    Unbinder unbinder;
    @BindView(R.id.iv_box)
    ImageView ivBox;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.rl_menu)
    ListView rlMenu;
    @BindView(R.id.line)
    View line;


    private EditorNameDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public List<Euipt> data = new ArrayList<>();
    private EuipmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        presenter.getSmartList();
        adapter = new EuipmentAdapter(getActivity());
        rlMenu.setAdapter(adapter);
        user_id= SpUtil.getInstance().getUserId();
        mWindowAddPhoto = new PhotoPopupWindow(getActivity());
        mWindowAddPhoto.setCont("解除绑定", "取消");
        mWindowAddPhoto.setColor(R.color.red_pop, 0);
        initListener();
        return view;
    }

    public PhotoPopupWindow mWindowAddPhoto;

    @Override
    public void onResume() {
        super.onResume();

    }

    private String patientDeviceId;
    private String user_id;
    private unBind unbind=new unBind();
    private void initListener() {

        ivSetting.setOnClickListener(this);
        ivBox.setOnClickListener(this);
        adapter.setClickListener(new EuipmentAdapter.ItemCliks() {
            @Override
            public void getItem(Euipt menu, int type) {
                patientDeviceId=menu.patientDeviceId;
                mWindowAddPhoto.showAsDropDown(line);
            }
        });
       mWindowAddPhoto.setSureListener(new PhotoPopupWindow.ClickListener() {
           @Override
           public void clickListener(int type) {
               if(type==0){
                   unbind.patientDeviceId = patientDeviceId;
                   unbind.userId = user_id;
                   presenter.UnbindDevice(unbind);
               }else {

               }
           }
       });
    }

    private void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.ll_center://编辑中心
//                startActivity(new Intent(getActivity(), MineInfoActivity.class));
//
//                break;
//            case R.id.ll_reminder://用药提醒
//                startActivity(new Intent(getActivity(), RemindHealthActivity.class));
//                break;
//            case R.id.ll_health://健康中心
//
//                startActivity(new Intent(getActivity(), MineHealthCenterActivity.class));
//                break;
//            case R.id.ll_equipment://我的设备
//                startActivity(new Intent(getActivity(), MineEuipmentActivity.class));
//                break;
//            case R.id.ll_collection://我的收藏
//
//                startActivity(new Intent(getActivity(), CollectionActivity.class));
//                break;
            case R.id.iv_setting://
                startActivity(new Intent(getActivity(), SettingActivity.class));

                break;
            case R.id.iv_box://
                startActivity(new Intent(getActivity(), MineInfoActivity.class));

                break;
//           case R.id.iv_code://我的二维码
//                startActivity(new Intent(getActivity(), MineCodeActivity.class));
//                break;
        }
    }

    private List<Euipt> list;

    @Override
    public <T> void toEntity(T entity) {
        list = (List<Euipt>) entity;

        adapter.setData(list);
    }

    @Override
    public void toNextStep(int type) {
        presenter.getSmartList();
    }

    @Override
    public void showTomast(String msg) {

    }
}
