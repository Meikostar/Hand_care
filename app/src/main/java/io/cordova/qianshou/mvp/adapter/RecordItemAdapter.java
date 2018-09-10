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
import io.cordova.qianshou.bean.Item;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;

import java.util.List;

import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;


public class RecordItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<Record> list;
    private int type=0;//1是用药记录item
    public RecordItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(Record menu, int type);//type 1表示点击事件2 表示长按事件
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<Record> list){
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_remind_item, parent, false);
            holder.name= (TextView) view.findViewById(R.id.tv_name);
            holder.tvNumber= (TextView) view.findViewById(R.id.tv_number);
            holder.llbg= (LinearLayout) view.findViewById(R.id.ll_bgs);
            holder.img= (ImageView) view.findViewById(R.id.iv_img);
            holder.tvCount= (TextView) view.findViewById(R.id.tv_cout);
            holder.tvTime= (TextView) view.findViewById(R.id.tv_times);
            holder.tvState= (TextView) view.findViewById(R.id.tv_state);
            holder.line=  view.findViewById(R.id.line);
            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(TextUtil.isNotEmpty(list.get(position).content)){
            holder.name.setText(list.get(position).content);
        }
        if(TextUtil.isNotEmpty(list.get(position).dosage)){
            holder.tvCount.setText(list.get(position).dosage);
        }
        holder.llbg.setVisibility(View.VISIBLE);
        holder.tvTime.setText(TimeUtil.formatToMs(list.get(position).createdDateTime));

         if(position==list.size()-1){
             holder.line.setVisibility(View.INVISIBLE);
         }else {
             holder.line.setVisibility(View.VISIBLE);
         }
        return view;


    }

    public  class ResultViewHolder{

        TextView name;
        TextView tvNumber;
        TextView tvCount;
        TextView tvState;
        TextView tvTime;
        LinearLayout llbg;
        View line;
        ImageView img;

    }
}
