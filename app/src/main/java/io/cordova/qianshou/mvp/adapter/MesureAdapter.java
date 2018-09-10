package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.bean.ORDER;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.DashView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.DashView;


public class MesureAdapter extends BaseAdapter {
    private Context mContext;
    private List<Medil> list;

    public MesureAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface addListener {
        void getItem(String total);
    }

    public List<Medil> getDatas() {
        return list;
    }

    private Map<Integer, Integer> map = new HashMap<>();
    private int type;
    private int state = -1;

    public void setData(List<Medil> list) {
        this.list = list;
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

    private double totalMoney;

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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

            view = LayoutInflater.from(mContext).inflate(R.layout.item_use_record, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Medil medil = list.get(position);

        if (position != 0) {
            holder.line1.setVisibility(View.VISIBLE);
        } else {
            holder.line1.setVisibility(View.GONE);

        }
        if(medil.name.equals("血压")){
            holder.tvData.setText("收缩压:"+(medil.high!=null?medil.high+"mmHg":"\t\t\t")+"\t\t舒张压:"+(medil.low!=null?medil.low+"mmHg":"\t\t\t")+"\t\t心率:"+(medil.pulse!=null?medil.pulse+"次/分钟":"\t\t\t"));
        }else {
            holder.tvData.setText("血糖："+medil.bloodGlucoseLevel+"mmol/L");
        }
        holder.tvTime.setText(TimeUtil.formatHour((long) medil.timeStamp));
        if(TextUtil.isNotEmpty(medil.name)){
            holder.tvName.setText(medil.name);
        } if(TextUtil.isNotEmpty(medil.status)){
            if(medil.status.equals("completed")){
                holder.tvState.setText("已完成");
            }else {
                holder.tvState.setText("未完成");
            }

        }
        if (position  == 0) {
            holder.tvState.setVisibility(View.VISIBLE);
            holder.tvAmout.setVisibility(View.VISIBLE);
            holder.tvTime.setVisibility(View.VISIBLE);
        } else {
            holder.tvState.setVisibility(View.INVISIBLE);
            holder.tvAmout.setVisibility(View.INVISIBLE);
            holder.tvTime.setVisibility(View.INVISIBLE);
        }
        return view;


    }

    //0待接单，1待结账 2已完成，4已撤销
    public class ResultViewHolder {

        TextView name;
        TextView tv_count;

    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    private ClickListener listener;

    public interface ClickListener {
        void clickListener(int type, String id);
    }


    static class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.line1)
        DashView line1;
        @BindView(R.id.line2)
        DashView line2;
        @BindView(R.id.iv_cyc)
        ImageView ivCyc;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_amout)
        TextView tvAmout;
        @BindView(R.id.tv_data)
        TextView tvData;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
