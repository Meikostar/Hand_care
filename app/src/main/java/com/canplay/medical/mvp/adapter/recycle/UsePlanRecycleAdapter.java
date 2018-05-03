package com.canplay.medical.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.bean.Item;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.adapter.RecordItemAdapter;
import com.canplay.medical.mvp.adapter.RemindItemAdapter;
import com.canplay.medical.mvp.adapter.UsePlanAdapter;
import com.canplay.medical.mvp.adapter.UseTimeAdapter;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.DashView;
import com.canplay.medical.view.RegularListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mykar on 17/4/12.
 */
public class UsePlanRecycleAdapter extends BaseRecycleViewAdapter {

    private Context context;

    private int status;
    public UsePlanRecycleAdapter(Context context) {
        this.context = context;

    }
    public void setStatus(int status){
        this.status=status;
    }
    private int type;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_use_plan, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));



        return new ViewHolder(view);
    }
   private List<Item> data=new ArrayList<>();
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        Record list= (Record) datas.get(position);
        if(type!=0){
            holder.tvCout.setVisibility(View.INVISIBLE);
        }else {
            holder.tvCout.setVisibility(View.VISIBLE);
        }
        holder.tvTime.setText(TimeUtil.formatToMs(list.date));
        RecordItemAdapter adapter = new RecordItemAdapter(context);
        holder.rlMenu.setAdapter(adapter);

        adapter.setData(list.items);

        if (position != 0) {
            holder.line1.setVisibility(View.VISIBLE);
        } else {
            holder.line1.setVisibility(View.GONE);

        }

     }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
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


   public static class ViewHolder extends RecyclerView.ViewHolder{
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
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
