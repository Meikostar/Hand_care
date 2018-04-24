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

import com.canplay.medical.R;
import com.canplay.medical.bean.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HealthDataAdapter extends BaseAdapter {
    private Context mContext;
    private List<Message> list;
    private int type = 0;//1是用药记录item

    public HealthDataAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks {
        void getItem(Message menu, int type);//type 1表示点击事件2 表示长按事件
    }

    private ItemCliks listener;

    public void setClickListener(ItemCliks listener) {
        this.listener = listener;
    }

    public void setData(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void setType(int type) {
        this.type = type;

    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 3;
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
        if(position==0){
            holder.tvType.setText(" 血压测量记录");
            holder.llOne.setVisibility(View.VISIBLE);
            holder.llTwo.setVisibility(View.GONE);
        }else if(position==1){
            holder.tvType.setText(" 血糖测量记录");
            holder.llOne.setVisibility(View.VISIBLE);
            holder.llTwo.setVisibility(View.GONE);
        }else if(position==2){
            holder.tvType.setText(" 服药记录");
            holder.llOne.setVisibility(View.GONE);
            holder.llTwo.setVisibility(View.VISIBLE);
        }
        holder.llbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getItem(new Message(),position);
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
