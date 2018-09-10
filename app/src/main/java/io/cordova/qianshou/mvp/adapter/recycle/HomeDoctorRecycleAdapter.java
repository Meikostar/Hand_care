package io.cordova.qianshou.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.CircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseApplication;
import io.cordova.qianshou.bean.Friend;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.CircleTransform;


/**
 * Created by mykar on 17/4/12.
 */
public class HomeDoctorRecycleAdapter extends BaseRecycleViewAdapter {


    private Context context;

    public HomeDoctorRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_doctor_item, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new DoctorItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DoctorItemViewHolder holders = (DoctorItemViewHolder) holder;
        final Friend data= (Friend) datas.get(position);
        holders.llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickListener(position, data);
            }
        });

        if(TextUtil.isNotEmpty(data.participant.user.displayName)){
            holders.tvName.setText(data.participant.user.displayName);
        }if(TextUtil.isNotEmpty(data.participant.user.mobile)){
            holders.tvPhone.setText(data.participant.user.mobile);
        }if(TextUtil.isNotEmpty(data.participant.hospital)){
            holders.tvLocation.setText(data.participant.hospital);
        }if(TextUtil.isNotEmpty(data.participant.position)){
            holders.tvPoistion.setText(data.participant.position+(data.participant.lineManager==null?"":"|"+data.participant.lineManager));
        }
        Glide.with(context).load(BaseApplication.avatar+data.participant.user.avatar).asBitmap().transform(new CircleTransform(context)).placeholder(R.drawable.dingdantouxiang).into(holders.ivImg);

    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
    }

    public static class DoctorItemViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_poistion)
        TextView tvPoistion;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.ll_bg)
        LinearLayout llBg;

        public DoctorItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void clickListener(int poiston, Friend data);
    }
}
