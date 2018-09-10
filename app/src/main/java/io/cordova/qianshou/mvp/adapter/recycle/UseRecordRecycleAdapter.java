package io.cordova.qianshou.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.view.DashView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.view.DashView;


/**
 * Created by mykar on 17/4/12.
 */
public class UseRecordRecycleAdapter extends BaseRecycleViewAdapter {


    private Context context;

    public UseRecordRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_use_pan_record, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new DoctorItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DoctorItemViewHolder holders = (DoctorItemViewHolder) holder;
//        final ORDER data= (ORDER) datas.get(position);

        if(position!=0){
            holders.line1.setVisibility(View.VISIBLE);
        }else {
            holders.line1.setVisibility(View.GONE);

        }
        if(position%2==0){
            if(position!=0){
                holders.line1.setVisibility(View.VISIBLE);
            }

        }else {

        }
        if(position%2==0){
            holders.tvTimes.setText("15:25:30"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"正常");
            holders.tvData.setText("阿西批零"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1颗"+"\n"+"阿胶颗粒"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t2颗");
        }else {
            holders.tvTimes.setText("15:25:30"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"晚服");
            holders.tvData.setText("阿西批零"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t1颗");

        }
    }

    @Override
    public int getItemCount() {
        int count = 7;

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
