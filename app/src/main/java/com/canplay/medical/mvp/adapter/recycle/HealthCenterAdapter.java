package com.canplay.medical.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.platform.comapi.map.E;
import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.bean.ORDER;
import com.canplay.medical.mvp.adapter.viewholder.HealtCenterHolder;
import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.CircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mykar on 17/4/12.
 */
public class HealthCenterAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private int type;
    private int status;
    public HealthCenterAdapter(Context context,int type) {
        this.context = context;
        this.type=type;
    }
    public void setStatus(int status){
        this.status=status;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if(type==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, null);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health, null);
        }


        return new HealtCenterHolder(view,type);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HealtCenterHolder holders = (HealtCenterHolder) holder;


        final Friend data= (Friend) datas.get(position);
        if(type==0){

            if(TextUtil.isNotEmpty(data.name)){
                holders.name.setText(data.name);
            }  if(TextUtil.isNotEmpty(data.familyAndFriendsUserName)){
                holders.phone.setText(data.familyAndFriendsUserName);
            }
            Glide.with(context).load(data.avatar).asBitmap()
                    .placeholder(R.drawable.moren).transform(new CircleTransform(context)).into(holders.img);
            if(status==0){
                holders.iv_arrow.setVisibility(View.VISIBLE);
                holders.add.setVisibility(View.VISIBLE);
                if(data.status.equals("waiting")){
                    holders.add.setTextColor(context.getResources().getColor(R.color.color6));
                    holders.add.setText("接受");
                    holders.add.setBackground(context.getResources().getDrawable(R.drawable.line_regle_blue));
                }else {
                    holders.add.setVisibility(View.GONE);
                }
            }else if(status==1) {
                holders.iv_arrow.setVisibility(View.GONE);
                holders.add.setVisibility(View.VISIBLE);
                holders.add.setTextColor(context.getResources().getColor(R.color.white));
                holders.add.setText("添加");
                holders.add.setBackground(context.getResources().getDrawable(R.drawable.shape_bg_lin_cancel));
            }
            holders.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickListener(position,data);
                }
            });
        }else {

            if(TextUtil.isNotEmpty(data.nickname)){
                holders.name.setText(data.nickname);
            } else{
                if(TextUtil.isNotEmpty(data.name)){
                    holders.name.setText(data.name);
                } else{
                    if(TextUtil.isNotEmpty(data.displayName)){
                        holders.name.setText(data.displayName);
                    }
                }
            } if(TextUtil.isNotEmpty(data.phone)){
                holders.phone.setText(data.phone);
            }
            Glide.with(context).load(data.avatar).asBitmap()
                    .placeholder(R.drawable.moren).transform(new CircleTransform(context)).into(holders.img);
            if(status==0){
                holders.iv_arrow.setVisibility(View.VISIBLE);
                holders.add.setVisibility(View.GONE);
            }else if(status==1) {
                holders.iv_arrow.setVisibility(View.GONE);
                holders.add.setVisibility(View.VISIBLE);
                if(TextUtil.isNotEmpty(data.status)){
                    if(data.status.equals("Waiting")){
                        holders.add.setText("接受");
                        holders.add.setVisibility(View.VISIBLE);
                        holders.add.setTextColor(context.getResources().getColor(R.color.white));
                        holders.add.setBackground(context.getResources().getDrawable(R.drawable.shape_bg_lin_cancel));
                    }else if(data.status.equals("Pending")) {
                        holders.add.setTextColor(context.getResources().getColor(R.color.color6));
                        holders.add.setText("已发送");
                        holders.add.setVisibility(View.VISIBLE);
                        holders.add.setBackground(context.getResources().getDrawable(R.drawable.line_regle_blue));
                    }else if(data.status.equals("Active")) {
                        holders.add.setTextColor(context.getResources().getColor(R.color.color6));
                        holders.add.setVisibility(View.INVISIBLE);
                        holders.add.setBackground(context.getResources().getDrawable(R.drawable.line_regle_blue));
                    }
                }else {

                        holders.add.setText("添加");
                        holders.add.setTextColor(context.getResources().getColor(R.color.white));
                        holders.add.setBackground(context.getResources().getDrawable(R.drawable.shape_bg_lin_cancel));

                }
                String userId = SpUtil.getInstance().getUserId();
//                if(userId.equals(data.userId)){
//                    holders.add.setVisibility(View.GONE);
//                }


            }else if(status==2) {
                holders.iv_arrow.setVisibility(View.GONE);
                holders.add.setVisibility(View.VISIBLE);
                holders.add.setTextColor(context.getResources().getColor(R.color.color6));
                holders.add.setText("已添加");
                holders.add.setBackground(context.getResources().getDrawable(R.drawable.line_regle_blue));
            }
            holders.ll_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickListener(0,data);//1代表未添加2代表已添加
                }
            });

            holders.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickListener(1,data);//1代表未添加2代表已添加
                }
            });
        }
     }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
    }

    public void setClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public OnItemClickListener listener;
    public interface  OnItemClickListener{
        void clickListener(int type, Friend data);
    }
}
