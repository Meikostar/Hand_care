package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.ORDER;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.MCheckBox;

import org.simpleframework.xml.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchMedicalAdapter extends BaseAdapter {
    private Context mContext;
    private List<Medicines> list;

    public SearchMedicalAdapter(Context mContext) {

        this.mContext = mContext;
    }


    private int type;
    private int state = -1;

    public void setData(List<Medicines> list, int type) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }

    public void setState(int state) {
        this.state = state;
        notifyDataSetChanged();
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }

   public List<Medicines> getData(){
       return list;
   }

    @Override
    public int getCount() {
        return list != null ? list.size() :0;
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
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.search_medcial_item, null);
            holder = new ViewHolder();

            holder.name = (TextView) view.findViewById(R.id.tv_name);
            holder.number = (TextView) view.findViewById(R.id.tv_number);
            holder.ll_bg = (LinearLayout) view.findViewById(R.id.ll_bg);
            holder.img = (ImageView) view.findViewById(R.id.iv_img);
            holder.ivClose = (ImageView) view.findViewById(R.id.iv_close);
            holder.check = (MCheckBox) view.findViewById(R.id.iv_choose);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        if(list.get(position).isCheck){
            holder.check.setChecked(true);
        }else {
            holder.check.setChecked(false);

        }
        holder.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).isCheck){
                    holder.check.setChecked(false);
                    list.get(position).isCheck=false;
                }else {
                    holder.check.setChecked(true);
                    list.get(position).isCheck=true;
                }
            }
        });
        if(TextUtil.isNotEmpty(list.get(position).name)){
            holder.name.setText(list.get(position).name);
        }

        Glide.with(mContext).load(list.get(position).image).asBitmap().placeholder(R.drawable.moren).into(holder.img);
        return view;


    }

    public class ViewHolder {
        TextView letter;
        TextView name;
        TextView number;
        ImageView img;
        ImageView ivClose;
        MCheckBox check;
        LinearLayout ll_bg;

    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    private ClickListener listener;

    public interface ClickListener {
        void clickListener(int type, String id);
    }



}
