package io.cordova.qianshou.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.mvp.adapter.viewholder.PressRecordkViewHolder;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;


import io.cordova.qianshou.bean.Record;
import io.cordova.qianshou.mvp.adapter.viewholder.PressRecordkViewHolder;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.util.TimeUtil;

/**
 * Created by mykar on 17/4/12.
 */
public class PressRecordReCycleAdapter extends BaseRecycleViewAdapter {
    private Context context;
    public PressRecordReCycleAdapter(Context context, int type){
        this.type=type;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.press_record_item, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new PressRecordkViewHolder(view);


    }
   private int type;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            PressRecordkViewHolder holders = (PressRecordkViewHolder) holder;
        Record data= (Record) datas.get(position);
        if(type==0){
            if(TextUtil.isNotEmpty(data.high)){
                holders.one.setText("收缩压："+data.high+"mmHg"+"    舒张压："+data.low+"mmHg"+"   心率："+data.pulse+"次/分钟");
            }
        }else {
            holders.one.setText("血糖:"+data.bgl+"mmol/L");

        }

        if(position!=0){
                holders.line1.setVisibility(View.VISIBLE);
            }else {
                holders.line1.setVisibility(View.GONE);

            }



        if(position==0){
            String time = TimeUtil.formatToMf((long)data.timeStamp);
            String[] split = time.split("##");
            holders.tvTime.setVisibility(View.VISIBLE);
            if(split!=null&&split.length==2){
                holders.tvTime.setText(split[0]);
                holders.tvTimeDeal.setText(split[1]);
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
                    holders.tvTimeDeal.setText(split[1]);
                }else {
                    holders.tvTime.setVisibility(View.VISIBLE);
                    holders.tvTime.setText(split[0]);
                    holders.tvTimeDeal.setText(split[1]);
                }
            }
        }
//            if(position!=0){
//                holders.ll_bg.setVisibility(View.VISIBLE);
//
//            }
//            holders.tvTime.setText(TimeUtil.formatToMf(data.getH_add_time())+"\n"+TimeUtil.formatToMD(data.getH_add_time()));
//            holders.tvContent.setText(data.getComment()+"\t\t\t\t\t\t");





    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
    }
}
