package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.bean.Health;
import com.canplay.medical.bean.Message;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HealthDataAdapter extends BaseAdapter {
    private Context mContext;
    private List<Health> list;
    private int type = 0;//1是用药记录item

    public HealthDataAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks {
        void getItem(Health menu, int type);//type 1表示点击事件2 表示长按事件
    }

    private ItemCliks listener;

    public void setClickListener(ItemCliks listener) {
        this.listener = listener;
    }

    public void setData(List<Health> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void setType(int type) {
        this.type = type;

    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_health_data, parent, false);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
     final    Health health = list.get(position);
        if(TextUtil.isNotEmpty(health.high)){

            holder.tvType.setText(" 血压测量记录");
            if (Double.valueOf(health.high) > 90 && Double.valueOf(health.high) < 139) {
                holder.tvOne.setTextColor(mContext.getResources().getColor(R.color.slow_black));
            }else {
                holder.tvOne.setTextColor(mContext.getResources().getColor(R.color.red_b));

            }    if ( Double.valueOf(health.low) >60 && Double.valueOf(health.low) < 90) {
                holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.slow_black));
            }else {
                holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.red_b));

            }    if ( Double.valueOf(health.pulse) > 60 && Double.valueOf(health.pulse) < 100) {
                holder.tvThree.setTextColor(mContext.getResources().getColor(R.color.slow_black));
            }else {
                holder.tvThree.setTextColor(mContext.getResources().getColor(R.color.red_b));

            }

            holder.tvOnes.setVisibility(View.VISIBLE);
            holder.tvTwos.setVisibility(View.VISIBLE);
            holder.tvContent.setVisibility(View.VISIBLE);
            holder.tvOne.setText(health.high+"mmHg");
            holder.tvOnes.setText("收缩压");
            holder.tvTwo.setText(health.low+"mmHg");
            holder.tvTwos.setText("舒张压");
            holder.tvThree.setText(health.pulse+"次/分钟");
            holder.tvContent.setText("心率");
            holder.llOne.setVisibility(View.VISIBLE);
            holder.llTwo.setVisibility(View.GONE);
        }else if(health.bloodGlucoseLevels!=null){
            holder.tvType.setText(" 血糖测量记录");
            holder.llOne.setVisibility(View.VISIBLE);

            if(health.bloodGlucoseLevels.size()==1){
                holder.llTwo.setVisibility(View.GONE);
                holder.tvOne.setVisibility(View.INVISIBLE);
                holder.tvOnes.setVisibility(View.INVISIBLE);
                holder.tvThree.setVisibility(View.INVISIBLE);
                holder.tvContent.setVisibility(View.INVISIBLE);
                holder.tvTwos.setText(TimeUtil.formatToMD((long) health.bloodGlucoseLevels.get(0).timeStamp));
                holder.tvTwo.setText(health.bloodGlucoseLevels.get(0).bgl+"mmol/L");
                if ( Double.valueOf(health.bloodGlucoseLevels.get(0).bgl) > 3.1 && Double.valueOf(health.bloodGlucoseLevels.get(0).bgl) < 8.1) {
                    holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.slow_black));
                }else {
                    holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.red_b));

                }
            }else   if(health.bloodGlucoseLevels.size()==2){
                holder.llTwo.setVisibility(View.GONE);
                holder.tvOne.setVisibility(View.VISIBLE);
                holder.tvOnes.setVisibility(View.VISIBLE);
                holder.tvThree.setVisibility(View.INVISIBLE);
                holder.tvContent.setVisibility(View.INVISIBLE);
                holder.tvTwos.setText(TimeUtil.formatToMD((long) health.bloodGlucoseLevels.get(1).timeStamp));
                holder.tvOnes.setText(TimeUtil.formatToMD((long) health.bloodGlucoseLevels.get(0).timeStamp));
                holder.tvTwo.setText(health.bloodGlucoseLevels.get(1).bgl+"mmol/L");
                holder.tvOne.setText(health.bloodGlucoseLevels.get(0).bgl+"mmol/L");
                if ( Double.valueOf(health.bloodGlucoseLevels.get(1).bgl) > 3.1 && Double.valueOf(health.bloodGlucoseLevels.get(1).bgl) < 8.1) {
                    holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.slow_black));
                }else {
                    holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.red_b));

                } if ( Double.valueOf(health.bloodGlucoseLevels.get(0).bgl) > 3.1 && Double.valueOf(health.bloodGlucoseLevels.get(0).bgl) < 8.1) {
                    holder.tvOne.setTextColor(mContext.getResources().getColor(R.color.slow_black));
                }else {
                    holder.tvOne.setTextColor(mContext.getResources().getColor(R.color.red_b));

                }
            }else {
                holder.llTwo.setVisibility(View.GONE);
                holder.tvOne.setVisibility(View.VISIBLE);
                holder.tvOnes.setVisibility(View.VISIBLE);
                holder.tvThree.setVisibility(View.VISIBLE);
                holder.tvContent.setVisibility(View.VISIBLE);
                holder.tvTwos.setText(TimeUtil.formatToMD((long) health.bloodGlucoseLevels.get(1).timeStamp));
                holder.tvOnes.setText(TimeUtil.formatToMD((long) health.bloodGlucoseLevels.get(0).timeStamp));
                holder.tvContent.setText(TimeUtil.formatToMD((long) health.bloodGlucoseLevels.get(2).timeStamp));
                holder.tvTwo.setText(health.bloodGlucoseLevels.get(1).bgl+"mmol/L");
                holder.tvOne.setText(health.bloodGlucoseLevels.get(0).bgl+"mmol/L");
                holder.tvThree.setText(health.bloodGlucoseLevels.get(2).bgl+"mmol/L");
                if ( Double.valueOf(health.bloodGlucoseLevels.get(0).bgl) > 3.1 && Double.valueOf(health.bloodGlucoseLevels.get(0).bgl) < 8.1) {
                    holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.slow_black));
                }else {
                    holder.tvTwo.setTextColor(mContext.getResources().getColor(R.color.red_b));

                }     if ( Double.valueOf(health.bloodGlucoseLevels.get(1).bgl) > 3.1 && Double.valueOf(health.bloodGlucoseLevels.get(1).bgl) < 8.1) {
                    holder.tvOne.setTextColor(mContext.getResources().getColor(R.color.slow_black));
                }else {
                    holder.tvOne.setTextColor(mContext.getResources().getColor(R.color.red_b));

                }    if ( Double.valueOf(health.bloodGlucoseLevels.get(2).bgl) > 3.1 && Double.valueOf(health.bloodGlucoseLevels.get(2).bgl) < 8.1) {
                    holder.tvThree.setTextColor(mContext.getResources().getColor(R.color.slow_black));
                }else {
                    holder.tvThree.setTextColor(mContext.getResources().getColor(R.color.red_b));

                }
            }


        }else if(TextUtil.isNotEmpty(health.medicineName)){
            holder.tvType.setText(" 服药记录");
            holder.llOne.setVisibility(View.GONE);
            holder.llTwo.setVisibility(View.VISIBLE);
            if(TextUtil.isNotEmpty(health.medicineName)){
                holder.tvName.setText(health.medicineName);
            }  if(TextUtil.isNotEmpty(health.spec)){
                holder.tvNumber.setText(health.spec);
            }  if(TextUtil.isNotEmpty(health.dosage)){
                holder.tvCout.setText(health.dosage);
            }
            Glide.with(mContext).load(health.image).asBitmap().placeholder(R.drawable.moren).into(holder.ivImg);

        }
        holder.llbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getItem(health,position);
            }
        });
        return view;


    }


    static class ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_one)
        TextView tvOne;
        @BindView(R.id.tv_ones)
        TextView tvOnes;
        @BindView(R.id.tv_two)
        TextView tvTwo;
        @BindView(R.id.tv_twos)
        TextView tvTwos;
        @BindView(R.id.tv_three)
        TextView tvThree;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_one)
        LinearLayout llOne;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_cout)
        TextView tvCout;
        @BindView(R.id.ll_two)
        LinearLayout llTwo;
        @BindView(R.id.ll_bg)
        LinearLayout llbg;
        @BindView(R.id.card)
        CardView card;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
