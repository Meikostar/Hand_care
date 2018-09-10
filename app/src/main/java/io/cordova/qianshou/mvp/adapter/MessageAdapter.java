package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Euip;
import io.cordova.qianshou.bean.Message;
import io.cordova.qianshou.util.TextUtil;

import java.util.List;

import io.cordova.qianshou.bean.Message;
import io.cordova.qianshou.util.TextUtil;


public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Message> list;
    private int type=0;//1是用药记录item
    public MessageAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(Message menu, int type);//type 1表示点击事件2 表示长按事件
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<Message> list){
        this.list=list;
        notifyDataSetChanged();
    }


    public void setType(int type){
        this.type=type;

    }
    @Override
    public int getCount() {
        return list!=null?list.size():0;
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
        ResultViewHolder holder;
        if (view == null){
            holder = new ResultViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
            holder.name= (TextView) view.findViewById(R.id.tv_type);
            holder.contnet= (TextView) view.findViewById(R.id.tv_content);
            holder.img= (ImageView) view.findViewById(R.id.iv_img);
            holder.tvTime= (TextView) view.findViewById(R.id.tv_time);
            holder.card= (CardView) view.findViewById(R.id.card);


            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(TextUtil.isNotEmpty(list.get(position).title)){
            holder.name.setText(list.get(position).title);
        }
        if(TextUtil.isNotEmpty(list.get(position).content)){
            holder.contnet.setText(list.get(position).content);
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getItem(new Message(),1);
            }
        });
        return view;


    }

    public  class ResultViewHolder{

        TextView name;

        TextView contnet;
        TextView tvTime;
        CardView card;
        ImageView img;
        LinearLayout ll_item;

    }
}
