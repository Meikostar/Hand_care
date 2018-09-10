package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.util.PinYinUtils;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.MCheckBox;

import java.util.List;

import io.cordova.qianshou.bean.Medicines;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.ClearEditText;
import io.cordova.qianshou.view.MCheckBox;


public class MedicaldTurnapter extends BaseAdapter {
    private Context mContext;
    private List<Medicines> list;

    public MedicaldTurnapter(Context mContext) {

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
    public View getView(final int position, View view, final ViewGroup parent) {
        final MedicaldTurnapter.ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.medical_turn_item, null);
            holder = new MedicaldTurnapter.ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            holder.number = (TextView) view.findViewById(R.id.tv_number);
            holder.tv_count = (ClearEditText) view.findViewById(R.id.tv_count);
            holder.ll_bg = (LinearLayout) view.findViewById(R.id.ll_bg);
            holder.img = (ImageView) view.findViewById(R.id.iv_img);
            holder.iv_del = (ImageView) view.findViewById(R.id.iv_del);
            holder.check = (MCheckBox) view.findViewById(R.id.iv_choose);
            view.setTag(holder);
        } else {
            holder = (MedicaldTurnapter.ViewHolder) view.getTag();
        }
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickListener(list.get(position),position);
            }
        });
        holder.tv_count.setdelteIconHilde();
        holder.tv_count.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(TextUtil.isNotEmpty(s.toString())){
                    list.get(position).message=s.toString()+"粒";
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
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
                listener.clickListener(list.get(position),-1);
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
        ClearEditText tv_count;
        ImageView img;
        ImageView iv_del;

        MCheckBox check;
        LinearLayout ll_bg;

    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    private ClickListener listener;

    public interface ClickListener {
        void clickListener(Medicines medicines, int poition);
    }



}
