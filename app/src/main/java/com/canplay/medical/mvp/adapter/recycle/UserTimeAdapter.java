package com.canplay.medical.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.bean.Record;
import com.canplay.medical.mvp.adapter.UseTimeAdapter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.DashView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mykar on 17/4/12.
 */
public class UserTimeAdapter extends BaseRecycleViewAdapter {

    @BindView(R.id.line1)
    DashView line1;
    @BindView(R.id.line2)
    DashView line2;
    @BindView(R.id.iv_cyc)
    ImageView ivCyc;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private Context context;

    private int status;

    public UserTimeAdapter(Context context) {
        this.context = context;

    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timex, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolders holder = (ViewHolders) holders;
        Record record= (Record) datas.get(position);
        if (position != 0) {
            holder.line1.setVisibility(View.VISIBLE);
        } else {
            holder.line1.setVisibility(View.GONE);

        }
        if(TextUtil.isNotEmpty(record.title)){
            holder.tvName.setText(record.title);
            if(record.title.equals("添加药物")){
                holder.ivCyc.setImageResource(R.drawable.cyc1);
            }else if(record.title.equals("血压测量")){
                holder.ivCyc.setImageResource(R.drawable.cyc2);
            }
        }
        if(TextUtil.isNotEmpty(record.content)){
            holder.tvContent.setText(record.content);
        }
        if(TextUtil.isNotEmpty(record.category)){
            if(record.category.equals("Medicine")){
                holder.ivCyc.setImageResource(R.drawable.cyc1);
            }else if(record.category.equals("Measurement")){
                holder.ivCyc.setImageResource(R.drawable.cyc2);

            }
        }
        if(TextUtil.isNotEmpty(record.content)){

            if(record.content.contains("收缩压")){
                holder.ivCyc.setImageResource(R.drawable.cyc2);
            }else if(record.content.contains("血糖读")){
                holder.ivCyc.setImageResource(R.drawable.cyc3);
            }else {
                holder.ivCyc.setImageResource(R.drawable.cyc1);
            }
        }

        holder.tvTime.setText(TimeUtil.formatTimss(record.createdDateTime));
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

    public void setClickListener(UseTimeAdapter.ClickListener listener) {
        this.listener = listener;
    }

    private UseTimeAdapter.ClickListener listener;

    public interface ClickListener {
        void clickListener(int type, String id);
    }


    public static class ViewHolders extends RecyclerView.ViewHolder {
        @BindView(R.id.line1)
        DashView line1;
        @BindView(R.id.line2)
        DashView line2;
        @BindView(R.id.iv_cyc)
        ImageView ivCyc;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolders(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
