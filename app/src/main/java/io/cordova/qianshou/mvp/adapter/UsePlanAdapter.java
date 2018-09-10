package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.ORDER;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.DashView;
import io.cordova.qianshou.view.RegularListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.DashView;
import io.cordova.qianshou.view.RegularListView;


public class UsePlanAdapter extends BaseAdapter {
    private Context mContext;
    private List<Record> list;

    public UsePlanAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface addListener {
        void getItem(String total);
    }

    public List<Record> getDatas() {
        return list;
    }

    private Map<Integer, Integer> map = new HashMap<>();
    private int type;

    public void setData(List<Record> list, int type) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }



    public void setType(int type) {
        this.type = type;

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

            view = LayoutInflater.from(mContext).inflate(R.layout.item_use_plan, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if(type!=0){
            holder.tvCout.setVisibility(View.INVISIBLE);
        }else {
            holder.tvCout.setVisibility(View.VISIBLE);
        }
        holder.tvTime.setText(TimeUtil.formatToMs(list.get(position).date));
        RemindItemAdapter adapter = new RemindItemAdapter(mContext);
        holder.rlMenu.setAdapter(adapter);
        if (position != 0) {
            holder.line1.setVisibility(View.VISIBLE);
        } else {
            holder.line1.setVisibility(View.GONE);

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
        @BindView(R.id.tv_cout)
        TextView tvCout;
        @BindView(R.id.line1)
        DashView line1;
        @BindView(R.id.line2)
        DashView line2;
        @BindView(R.id.iv_cyc)
        ImageView ivCyc;
        @BindView(R.id.rl_menu)
        RegularListView rlMenu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
