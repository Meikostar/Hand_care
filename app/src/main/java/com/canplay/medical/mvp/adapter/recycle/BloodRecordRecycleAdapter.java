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
import com.canplay.medical.bean.Record;
import com.canplay.medical.bean.Sugar;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.DashView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mykar on 17/4/12.
 */
public class BloodRecordRecycleAdapter extends BaseRecycleViewAdapter {


    private Context context;
    private  int type;
    public BloodRecordRecycleAdapter(Context context) {
        this.context = context;
    }


    public void setType(int type){
        this.type=type;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blooe_record, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new DoctorItemViewHolder(view);
    }
    private Gson mGson=new Gson();
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DoctorItemViewHolder holders = (DoctorItemViewHolder) holder;
        final Record data= (Record) datas.get(position);
        Record customerinfo = mGson.fromJson(data.value, Record.class);
         if(type==0){
           String datas=("收缩压:"+( data.high==null?"":data.high))+("mmHg\t\t舒张压:"+(data.low==null?"":data.low))+("mmHg\t\t心率:"+(data.pulse==null?"":data.pulse)+"次/分钟");
             holders.tvData.setText(datas);
         }else {
             holders.tvData.setText("血糖:"+data.bgl+"mmol/L");
         }


        if(position==0){
            String time = TimeUtil.formatToMf((long)data.timeStamp);
            String[] split = time.split("##");
            holders.tvTime.setVisibility(View.VISIBLE);
            if(split!=null&&split.length==2){
                holders.tvTime.setText(split[0]);
                holders.tvTimes.setText(split[1]);
            }

        }else {
            String time = TimeUtil.formatToMf((long)data.timeStamp);
            String[] split = time.split("##");
            Record dats= (Record) datas.get(position - 1);
            String times = TimeUtil.formatToMf((long)dats.timeStamp);
            String[] splits = times.split("##");
            if(split!=null&&splits!=null){
                if(split[0].equals(splits[0])){
                    holders.tvTime.setVisibility(View.INVISIBLE);
                    holders.tvTimes.setText(split[1]);
                }else {
                    holders.tvTime.setVisibility(View.VISIBLE);
                    holders.tvTime.setText(split[0]);
                    holders.tvTimes.setText(split[1]);
                }
            }
        }

        if (position != 0) {
            holders.line1.setVisibility(View.VISIBLE);
        } else {
            holders.line1.setVisibility(View.GONE);

        }
//        if (position % 2 == 0) {
//
//
//        } else {
//
//            holders.tvTime.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
    }

    public static class DoctorItemViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.line1)
        DashView line1;
        @BindView(R.id.line2)
        DashView line2;
        @BindView(R.id.iv_cyc)
        ImageView ivCyc;
        @BindView(R.id.tv_times)
        TextView tvTimes;
        @BindView(R.id.tv_data)
        TextView tvData;

        public DoctorItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void clickListener(int poiston, String id);
    }
}
