package io.cordova.qianshou.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.BaseFragment;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.Boxs;
import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.bean.unBind;
import io.cordova.qianshou.mvp.activity.mine.MineInfoActivity;
import io.cordova.qianshou.mvp.activity.mine.SettingActivity;
import io.cordova.qianshou.mvp.adapter.EuipmentAdapter;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.HomeContract;
import io.cordova.qianshou.mvp.present.HomePresenter;
import io.cordova.qianshou.util.PhotoUtils;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.CircleImageView;
import io.cordova.qianshou.view.CircleTransform;
import io.cordova.qianshou.view.EditorNameDialog;
import io.cordova.qianshou.view.PhotoPopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by mykar on 17/4/10.
 */
public class SetFragment extends BaseFragment implements View.OnClickListener, HomeContract.View {
    @Inject
    HomePresenter presenter;
    Unbinder unbinder;
    @BindView(R.id.iv_box)
    ImageView ivBox;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_img)
    CircleImageView ivImg;
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
    @BindView(R.id.ll_set)
    LinearLayout llSet;
    @BindView(R.id.img_empty)
    ImageView imgEmpty;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;


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
        user_id = SpUtil.getInstance().getUserId();
        presenter.getFriendInfo(user_id);
        presenter.getSmartList();
        presenter.myMedicineBox();

        adapter = new EuipmentAdapter(getActivity());
        rlMenu.setAdapter(adapter);

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
    private unBind unbind = new unBind();
    private Subscription mSubscription;

    private void initListener() {

        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.EDITOR) {

                    presenter.getFriendInfo(user_id);

                    String paths = SpUtil.getInstance().getString("paths");
                    if(TextUtil.isNotEmpty(paths)){
                        File file = new File(paths);
                        if(file.exists()){
                            Glide.with(getActivity()).load(paths).asBitmap().placeholder(R.drawable.dingdantouxiang).into(ivImg);
                        }
                    }
                } else if (bean.type == SubscriptionBean.EUIP_REFASH) {
                    presenter.myMedicineBox();
                    presenter.getSmartList();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
        ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (friend == null) {
                    return;
                }
                Intent intent = new Intent(getActivity(), MineInfoActivity.class);
                intent.putExtra("friend", friend);
                startActivity(intent);

            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (friend == null) {
                    return;
                }
                Intent intent = new Intent(getActivity(), MineInfoActivity.class);
                intent.putExtra("friend", friend);
                startActivity(intent);

            }
        });

        ivSetting.setOnClickListener(this);
        ivBox.setOnClickListener(this);
        adapter.setClickListener(new EuipmentAdapter.ItemCliks() {
            @Override
            public void getItem(Euipt menu, int type) {

                patientDeviceId = menu.patientDeviceId;
                mWindowAddPhoto.showAsDropDown(line);


            }
        });
        mWindowAddPhoto.setSureListener(new PhotoPopupWindow.ClickListener() {
            @Override
            public void clickListener(int type) {
                if (type == 0) {
                    unbind.patientDeviceId = patientDeviceId;
                    unbind.userId = user_id;
                    presenter.UnbindDevice(unbind);
                } else {

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
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
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

                break;
//           case R.id.iv_code://我的二维码
//                startActivity(new Intent(getActivity(), MineCodeActivity.class));
//                break;
        }
    }

    private List<Euipt> list;
    private Friend friend;
    private Boxs box;

    @Override
    public <T> void toEntity(T entity, int type) {
        if (type == 0) {
            list = (List<Euipt>) entity;
             if(list!=null&&list.size()!=0){
                 rlBg.setVisibility(View.GONE);
             }else {
                 rlBg.setVisibility(View.VISIBLE);
             }
            adapter.setData(list);
        } else if (type == 9) {
            box = (Boxs) entity;
            BaseApplication.isOwn=box.owned;
            if (box.owned) {
                ivBox.setImageResource(R.drawable.cw);
            } else {
                ivBox.setImageResource(R.drawable.cw1);
            }
        } else {
            friend = (Friend) entity;
            setData();
        }

    }

    public void setData() {
        String paths = SpUtil.getInstance().getString("paths");
        if(TextUtil.isNotEmpty(paths)){
            File file = new File(paths);
            if(file.exists()){
                Glide.with(getActivity()).load(paths).asBitmap().placeholder(R.drawable.dingdantouxiang).transform(new CircleTransform(getActivity())).into(ivImg);
            }else {
//                Glide.with(getActivity()).load(BaseApplication.avatar + friend.avatar).asBitmap().transform(new CircleTransform(getActivity())).placeholder(R.drawable.dingdantouxiang).into(ivImg);
                Glide.with(getActivity()).load(BaseApplication.avatar + friend.avatar).asBitmap().transform(new CircleTransform(getActivity())).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

                        //图片加载完成
                        ivImg.setImageBitmap(bitmap);
                        String path = PhotoUtils.compressBitmap(bitmap);
                        SpUtil.getInstance().putString("pahts",path);
                    }
                });
            }
        }else {
            Glide.with(getActivity()).load(BaseApplication.avatar + friend.avatar).asBitmap().transform(new CircleTransform(getActivity())).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

                    //图片加载完成
                    ivImg.setImageBitmap(bitmap);
                    String path = PhotoUtils.compressBitmap(bitmap);
                    SpUtil.getInstance().putString("pahts",path);
                }
            });
        }
        if (TextUtil.isNotEmpty(friend.avatar)) {
            SpUtil.getInstance().putString("avator", friend.avatar);
        }

        if (TextUtil.isNotEmpty(friend.displayName)) {
            tvName.setText(friend.displayName);
        }
        if (TextUtil.isNotEmpty(friend.mobile)) {
            tvPhone.setText(friend.mobile);
            BaseApplication.phone = friend.mobile;
        }
        if (TextUtil.isNotEmpty(friend.dob)) {
            String[] split = friend.dob.split("/");
            String birth = split[0] + "." + split[1] + "." + split[2];
            tvBirth.setText(birth);
        }
        if (TextUtil.isNotEmpty(friend.address)) {
            tvAddress.setText(friend.address);
        }

        if (friend.gender.equals("male")) {//男
            tvSex.setText("男");
        } else {
            tvSex.setText("女");
        }
    }

    @Override
    public void toNextStep(int type) {

        showToast("移除成功");
        dimessProgress();
        presenter.myMedicineBox();
        presenter.getSmartList();
    }

    @Override
    public void showTomast(String msg) {

        showToast(msg);

    }
}
