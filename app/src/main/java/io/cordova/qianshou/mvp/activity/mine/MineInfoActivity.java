package io.cordova.qianshou.mvp.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.mvp.activity.account.LoginActivity;
import io.cordova.qianshou.mvp.component.DaggerBaseComponent;
import io.cordova.qianshou.mvp.present.BaseContract;
import io.cordova.qianshou.mvp.present.BasesPresenter;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.PhotoUtils;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.AddressSelectBindDialog;
import io.cordova.qianshou.view.CircleTransform;
import io.cordova.qianshou.view.EditorNameDialog;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.PhotoPopupWindow;
import io.cordova.qianshou.view.TimeSelectorDialog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.base.RxBus;
import io.cordova.qianshou.base.SubscriptionBean;
import io.cordova.qianshou.bean.BASE;
import io.cordova.qianshou.bean.Editor;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.bean.Province;
import io.cordova.qianshou.bean.avator;
import io.cordova.qianshou.permission.PermissionConst;
import io.cordova.qianshou.permission.PermissionGen;
import io.cordova.qianshou.permission.PermissionSuccess;
import io.cordova.qianshou.util.PhotoUtils;
import io.cordova.qianshou.util.SpUtil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.AddressSelectBindDialog;
import io.cordova.qianshou.view.CircleTransform;
import io.cordova.qianshou.view.EditorNameDialog;
import io.cordova.qianshou.view.NavigationBar;
import io.cordova.qianshou.view.PhotoPopupWindow;
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
                editor.userId= SpUtil.getInstance().getUserId();
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
    private String name;
    private String names;
    @Override
    public void initData() {
        String paths = SpUtil.getInstance().getString("paths");
        if(TextUtil.isNotEmpty(paths)){
            File file = new File(paths);
            if(file.exists()){
                Glide.with(this).load(paths).asBitmap().placeholder(R.drawable.dingdantouxiang).transform(new CircleTransform(this)).into(ivPhone);
            }else {
//                Glide.with(getActivity()).load(BaseApplication.avatar + friend.avatar).asBitmap().transform(new CircleTransform(getActivity())).placeholder(R.drawable.dingdantouxiang).into(ivImg);
                Glide.with(this).load(BaseApplication.avatar + friend.avatar).asBitmap().transform(new CircleTransform(this)).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

                        //图片加载完成
                        ivPhone.setImageBitmap(bitmap);
                        String path = PhotoUtils.compressBitmap(bitmap);

                        SpUtil.getInstance().putString("pahts",path);
                    }
                });
            }
        }else {
            Glide.with(this).load(BaseApplication.avatar + friend.avatar).asBitmap().transform(new CircleTransform(this)).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

                    //图片加载完成
                    ivPhone.setImageBitmap(bitmap);
                    String path = PhotoUtils.compressBitmap(bitmap);
                    SpUtil.getInstance().putString("pahts",path);
                }
            });
        }

        if(TextUtil.isNotEmpty(friend.displayName)){
            tvName.setText(friend.displayName);
            names=friend.displayName;
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
                    String commpressPath = PhotoUtils.commpressImg(path);
                    SpUtil.getInstance().putString("paths",commpressPath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap=BitmapFactory.decodeFile(commpressPath);


                    try {
                        //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
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
            case R.id.ll_code://names
                Intent intent = new Intent(MineInfoActivity.this, MineCodeActivity.class);
                intent.putExtra("name",names);
                startActivity(intent);
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

                    tv_area.setText(proName.substring(0,(proName.length()))+","+cityName.substring(0,(cityName.length())));

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
         BaseApplication.avatar=base.Filename;
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
