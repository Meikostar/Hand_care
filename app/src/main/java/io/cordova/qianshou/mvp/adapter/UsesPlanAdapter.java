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
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;
import io.cordova.qianshou.view.DashView;
import io.cordova.qianshou.view.RegularListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.bean.Medil;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.DashView;
import io.cordova.qianshou.view.RegularListView;


public class UsesPlanAdapter extends BaseAdapter {
    private Context mContext;
    private List<Medil> list;

    public UsesPlanAdapter(Context mContext) {

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

    public void setData(List<Medil> list) {
        this.list = list;

        notifyDataSetChanged();
    }


   private String time;
   private String state;
    public void setType(String time,String state) {
        this.time = time;
        this.state = state;

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
        if(TextUtil.isNotEmpty(list.get(position).when)){
            holder.tvTime.setText(list.get(position).when);
        }  if(TextUtil.isNotEmpty(list.get(position).code)){
            String poi="";
            if(position<10){
                poi="0"+(position+1);
            }else {
                poi=""+(position+1);
            }
            holder.tvCout.setText(list.get(position).code+"\n"+poi);
        }

        PlanItemAdapter adapter = new PlanItemAdapter(mContext);
        holder.rlMenu.setAdapter(adapter);
        adapter.setData(list.get(position).items);
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
