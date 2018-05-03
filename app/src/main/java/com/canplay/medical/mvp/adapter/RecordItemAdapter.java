package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.bean.Item;
import com.canplay.medical.bean.Record;
import com.canplay.medical.util.TextUtil;

import java.util.List;


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
            holder.img= (ImageView) view.findViewById(R.id.iv_img);
            holder.tvCount= (TextView) view.findViewById(R.id.tv_cout);
            holder.line=  view.findViewById(R.id.line);

            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(TextUtil.isNotEmpty(list.get(position).title)){
            holder.name.setText(list.get(position).title);
        }
//        if(TextUtil.isNotEmpty(list.get(position).content)){
//            holder.tvCount.setText(list.get(position).content);
//        }
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
        View line;
        ImageView img;

    }
}
