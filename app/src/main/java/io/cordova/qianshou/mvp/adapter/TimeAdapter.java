package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Euip;

import java.util.List;


public class TimeAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;
    private int type=0;//1是用药记录item
    public TimeAdapter(Context mContext) {

        this.mContext = mContext;
    }


    public void setData(List<String> list){
        this.list=list;
        notifyDataSetChanged();
    }


    public void setType(int type){
        this.type=type;

    }
    @Override
    public int getCount() {
        return list!=null?list.size():2;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_item, parent, false);
            holder.tv_time= (TextView) view.findViewById(R.id.tv_time);


            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
          holder.tv_time.setText(list.get(position));
        return view;


    }

    public  class ResultViewHolder{

        TextView tv_time;



    }
}
