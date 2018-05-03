package com.canplay.medical.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Editor;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.bean.Province;
import com.canplay.medical.bean.avator;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.AddressSelectBindDialog;
import com.canplay.medical.view.CircleTransform;
import com.canplay.medical.view.EditorNameDialog;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PhotoPopupWindow;
import com.canplay.medical.view.TimeSelectorDialog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.valuesfeng.picker.ImageSelectActivity;
import io.valuesfeng.picker.Picker;
import io.valuesfeng.picker.widget.ImageLoaderEngine;


/**
 * 个人信息
 */
public class MineInfoActivity extends BaseActivity implements View.OnClickListener,BaseContract.View{

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.ll_birth)
    LinearLayout llBirth;
    @BindView(R.id.ll_area)
    LinearLayout llArea;
    @BindView(R.id.ll_code)
    LinearLayout ll_code;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_area)
    TextView tv_area;
    private Friend friend;
    private EditorNameDialog dialog;
    private PhotoPopupWindow mWindowAddPhoto;
    private int sex=0;
    private String name="";
    private Editor editor=new Editor();
    @Override
    public void initViews() {
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        navigationBar.setNavigationBarListener(this);
        friend= (Friend) getIntent().getSerializableExtra("friend");
        dialog=new EditorNameDialog(this,line);

        selectorDialog = new TimeSelectorDialog(MineInfoActivity.this);
        selectorDialog.setDate(new Date(System.currentTimeMillis()))
                .setBindClickListener(new TimeSelectorDialog.BindClickListener() {
                    @Override
                    public void time(String data,int poition,String times) {
                        time=data;
                        tvBirth.setText(times);
                    }
                });
        mWindowAddPhoto = new PhotoPopupWindow(this);
    }
    private  TimeSelectorDialog selectorDialog;
    private  String time;
    @Override
    public void bindEvents() {
        ivPhone.setOnClickListener(this);
        llName.setOnClickListener(this);
        ll_code.setOnClickListener(this);
        llSex.setOnClickListener(this);
        llBirth.setOnClickListener(this);
        llArea.setOnClickListener(this);
        llCenter.setOnClickListener(this);
        llBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectorDialog.show(findViewById(R.id.ll_area));
            }
        });
        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {
               if(TextUtil.isNotEmpty(tv_area.getText().toString())){
                   editor.address=tv_area.getText().toString();
               }else {
                   editor.address="";
               } if(TextUtil.isNotEmpty(tvName.getText().toString())){
                    editor.displayName=tvName.getText().toString();
                }else {
                    editor.displayName="";
                } if(TextUtil.isNotEmpty(time)){
                    editor.dob=time;
                }else {
                    editor.dob="";
                }if(tvSex.getText().toString().equals("男")){
                    editor.gender="male";
                }else {
                    editor.gender="female";
                }
                editor.userId=SpUtil.getInstance().getUserId();
                showProgress("修改中...");
                presenter.editorUser(editor);
            }

            @Override
            public void navigationimg() {

            }
        });
        dialog.setBindClickListener(new EditorNameDialog.BindClickListener() {
            @Override
            public void teaMoney(String names) {

                if (TextUtil.isNotEmpty(names)) {
                     name=names;
                    tvName.setText(names);

                }
                dialog.dismiss();

            }
        });
        llSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowAddPhoto.showAsDropDown(line);
            }
        });
        mWindowAddPhoto.setSureListener(new PhotoPopupWindow.ClickListener() {
            @Override
            public void clickListener(int type) {
              if(type==0){//男
                  tvSex.setText("男");
              }else {//女
                  tvSex.setText("女");

              }
            }
        });

    }

    private Province prov;
    @Override
    public void initData() {
        Glide.with(this).load(BaseApplication.avatar+friend.avatar).asBitmap().placeholder(R.drawable.moren).transform(new CircleTransform(this)).into(ivPhone);
        if(TextUtil.isNotEmpty(friend.displayName)){
            tvName.setText(friend.displayName);
        }   if(TextUtil.isNotEmpty(friend.address)){
            tv_area.setText(friend.address);
        } if(TextUtil.isNotEmpty(friend.dob)){
            String[] split = friend.dob.split("/");
            String birth=split[0]+"."+split[1]+"."+split[2];
            tvBirth.setText(birth);
        }
        if(TextUtil.isNotEmpty(friend.gender)){
            if(friend.gender.equals("male")){//男
                tvSex.setText("男");
            }else {
                tvSex.setText("女");
            }
        }

    }
    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Picker.from(this)
                .count(1)
                .enableCamera(true)
                .setEngine(new ImageLoaderEngine())
                .setAdd_watermark(false)
                .forResult(4);
    }
    public String path;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //上传照片
                case 4:
                    List<String> imgs = data.getStringArrayListExtra(ImageSelectActivity.EXTRA_RESULT_SELECTION);
                    path = imgs.get(0);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap=BitmapFactory.decodeFile(path);


                    try {
                        //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] buffer = baos.toByteArray();
                    System.out.println("图片的大小："+buffer.length);

                    //将图片的字节流数据加密成base64字符输出
                    String photo = Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
                    ava.image=photo;
                    ava.ext="png";
                    showProgress("上传头像...");
                    presenter.upPhotos(ava);
                    Glide.with(this).load(path).asBitmap().transform(new CircleTransform(this)).into(ivPhone);
//                    presenter.getToken(path);
                    break;

            }
        }
    }
   private avator ava=new avator();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_center://
                                PermissionGen.with(MineInfoActivity.this)
                        .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                        .permissions(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
                break;
            case R.id.ll_name://
                dialog.show();
                break;
            case R.id.iv_phone://
                PermissionGen.with(MineInfoActivity.this)
                        .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                        .permissions(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
                break;
            case R.id.ll_birth://
                break;
            case R.id.ll_area://
                showAddressSelector();
                break;
            case R.id.ll_code://
                startActivity(new Intent(MineInfoActivity.this, MineCodeActivity.class));
                break;

        }
    }
    private String proName="";
    private String cityName="";
   private AddressSelectBindDialog mSelectBindDialog;
    public void showAddressSelector() {
        if (mSelectBindDialog==null){
            mSelectBindDialog = new AddressSelectBindDialog(this,proName,cityName);
            mSelectBindDialog.setBindClickListener(new AddressSelectBindDialog.BindClickListener() {
                @Override
                public void site(String proName, String cityName) {
                    tv_area.setText(proName.substring(0,(proName.length()-1))+","+cityName.substring(0,(cityName.length()-1)));

                }
            });
        }
        mSelectBindDialog.show();
    }
   private BASE base;
    @Override
    public <T> void toEntity(T entity, int type) {
     if(type==0){
         dimessProgress();
         showToasts("上传失败");
     } else if(type==1){
         dimessProgress();
         base= (BASE) entity;
         showToasts("上传成功");
         editor.avatar=base.Filename;
         RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.EDITOR,""));
        }else {
         dimessProgress();
         showToasts("编辑成功");
         RxBus.getInstance().send(SubscriptionBean.createSendBean(SubscriptionBean.EDITOR,""));
         finish();
     }
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
