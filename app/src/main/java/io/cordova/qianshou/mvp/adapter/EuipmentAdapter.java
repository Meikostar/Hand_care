package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Euip;
import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.util.TextUtil;

import java.util.List;

import io.cordova.qianshou.bean.Euipt;
import io.cordova.qianshou.util.TextUtil;


public class EuipmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<Euipt> list;
    private int type;
    public EuipmentAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(Euipt menu,int type);//type 1表示点击事件2 表示长按事件
    }
    public void setType(int type){
        this.type=type;
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<Euipt> list){
        this.list=list;
        notifyDataSetChanged();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.equip_item, parent, false);
            holder.name= (TextView) view.findViewById(R.id.tv_name);
            holder.tv_time= (TextView) view.findViewById(R.id.tv_time);
            holder.tv_count= (TextView) view.findViewById(R.id.tv_number);
            holder.img= (ImageView) view.findViewById(R.id.iv_img);
            holder.iv_arrow= (ImageView) view.findViewById(R.id.iv_arrow);
            holder.ll_item= (LinearLayout) view.findViewById(R.id.ll_bg);
            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(type==0){
            holder.iv_arrow.setVisibility(View.VISIBLE);
            holder.tv_time.setVisibility(View.GONE);
        }else {
            holder.iv_arrow.setVisibility(View.GONE);
            holder.tv_time.setVisibility(View.VISIBLE);
        }

        if(TextUtil.isNotEmpty(list.get(position).deviceTypeDisplayName)){
            holder.name.setText(list.get(position).deviceTypeDisplayName);
        }
        if(TextUtil.isNotEmpty(list.get(position).serialNo)){
            holder.tv_count.setText("设备编号:"+list.get(position).serialNo);
        }
        if(list.get(position).type==1){
            holder.img.setImageResource(R.drawable.smart1);
        }else if(list.get(position).type==2){
            holder.img.setImageResource(R.drawable.smart2);
        }else if(list.get(position).type==3){
            holder.img.setImageResource(R.drawable.smart3);
        }
         holder.ll_item.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listener.getItem(list.get(position), 1);
             }
         });
         holder.ll_item.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 listener.getItem(list.get(position), 2);
                 return true;
             }
         });
        return view;


    }

    public  class ResultViewHolder{

        TextView name;
        TextView tv_time;
        TextView tv_count;
        ImageView img;
        ImageView iv_arrow;
        LinearLayout ll_item;

    }
}
