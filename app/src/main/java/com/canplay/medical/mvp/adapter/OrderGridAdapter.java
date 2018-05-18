package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.bean.BASEBEAN;
import com.canplay.medical.bean.Medil;
import com.canplay.medical.util.TextUtil;

import java.util.ArrayList;
import java.util.List;


public class OrderGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Medil> lists;


    private Boolean aBoolean = false;

    private String[] content;

    public OrderGridAdapter(Context mContext) {

        this.mContext = mContext;

    }

    public void setData(List<Medil> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public void setDatas(List<Medil> mCities) {
        this.lists = mCities;
    }

    @Override
    public int getCount() {

        return lists == null ? 6 : lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists == null ? null : lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ResultViewHolder holder;

            if (view == null) {
                holder = new ResultViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.grid_imge_view, parent, false);

                holder.img = (ImageView) view.findViewById(R.id.img);
                holder.tvCount = (TextView) view.findViewById(R.id.tv_count);
                holder.tvName = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(holder);
            } else {
                holder = (ResultViewHolder) view.getTag();
            }
            if(TextUtil.isNotEmpty(lists.get(position).medicine)){
                holder.tvName.setText(lists.get(position).medicine);
            }  if(TextUtil.isNotEmpty(lists.get(position).dosage)){
            holder.tvCount.setText(lists.get(position).dosage);
        }
            Glide.with(mContext).load("").asBitmap().placeholder(R.drawable.moren).into(holder.img);
            return view;


    }

    public static class ResultViewHolder {
        ImageView img;
        TextView tvCount;
        TextView tvName;

    }

}
