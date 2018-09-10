package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Box;
import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.util.TextUtil;

import java.util.List;

import io.cordova.qianshou.bean.Detail;
import io.cordova.qianshou.util.TextUtil;


public class DetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<Detail> list;
    private int type=0;//1是用药记录item
    public DetailAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(Detail menu, int type);//type 1表示点击事件2 表示长按事件
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<Detail> list){
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_item, parent, false);
            holder.name= (TextView) view.findViewById(R.id.tv_name);
            holder.tvNumber= (TextView) view.findViewById(R.id.tv_number);
            holder.img= (ImageView) view.findViewById(R.id.iv_img);
            holder.tvCount= (TextView) view.findViewById(R.id.tv_count);
            holder.line=  view.findViewById(R.id.line);
            holder.llbg=(LinearLayout)view.findViewById(R.id.ll_bg);

            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(TextUtil.isNotEmpty(list.get(position).name)){
            holder.name.setText(list.get(position).name);
        }
        if(TextUtil.isNotEmpty(list.get(position).specs)){
            holder.tvNumber.setText(list.get(position).specs);
        }  if(TextUtil.isNotEmpty(list.get(position).dosage)){
            holder.tvCount.setText(list.get(position).dosage);
        }
        Glide.with(mContext).load(list.get(position).image).asBitmap().placeholder(R.drawable.moren).into(holder.img);

        return view;


    }

    public  class ResultViewHolder{

        TextView name;

        TextView tvNumber;
        TextView tvCount;
        View line;
        ImageView img;
        LinearLayout ll_item;
        LinearLayout llbg;

    }
}
