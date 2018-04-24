package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.bean.DATA;
import com.canplay.medical.util.TextUtil;

import java.util.List;


public class TimeAddAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;
    private int type=0;//1是用药记录item
    public TimeAddAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(String menu, int type);//type 1表示点击事件2 表示长按事件
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<String> list){
        this.list=list;
        notifyDataSetChanged();
    }


    public void setType(int type){
        this.type=type;

    }

    public List<String> getData(){
        return list;

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
    public View getView(final int position, View view, final ViewGroup parent) {
        ResultViewHolder holder;
        if (view == null){
            holder = new ResultViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_time_add, parent, false);
            holder.data= (TextView) view.findViewById(R.id.tv_time);
            holder.close= (ImageView) view.findViewById(R.id.iv_close);


            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(TextUtil.isNotEmpty(list.get(position))){
            holder.data.setText(list.get(position));
        }
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
//                listener.getItem(list.get(position),position);
            }
        });
        return view;


    }


    public  class ResultViewHolder{

        TextView data;
        ImageView close;



    }
}
