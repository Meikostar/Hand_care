package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.bean.Collect;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.TimeUtil;
import com.canplay.medical.view.RegularListView;
import com.canplay.medical.view.SwipeListLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by honghouyang on 16/12/23.
 */

public class RemindMedicatAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Medicine> list;
    private int type;
    private ListView lv_content;
    private Set<SwipeListLayout> sets = new HashSet();
    private selectItemListener listener;
    public interface selectItemListener{
        void delete(Medicine id, int type, int poistion);
    }
    public void setListener(selectItemListener listener){
        this.listener=listener;
    }
    public void setData( List<Medicine> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public List<Medicine> getDatas(){
        return list;
    }
    public RemindMedicatAdapter(Context context, ArrayList<Medicine> list, ListView lv_content) {
        this.lv_content=lv_content;
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        lv_content.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        if (sets.size() > 0) {
                            for (SwipeListLayout s : sets) {
                                s.setStatus(SwipeListLayout.Status.Close, true);
                                sets.remove(s);
                            }
                        }
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int getCount() {
        return list!=null?(list.size()==0?0:list.size()):0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_remind_medicat, parent, false);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            holder.rl_bg = (LinearLayout) convertView.findViewById(R.id.rl_bg);
            holder.rllist = (RegularListView) convertView.findViewById(R.id.rl_list);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(TextUtil.isNotEmpty(list.get(position).when)){
//            holder.tvTime.setText(list.get(position).when);
        }
        String con="";
        if(position<10){
            con="0"+(position+1);
        }else {
            con=""+(position+1);
        }
        if(!list.get(position).completedForToday){
            String hou = TimeUtil.formatHour(System.currentTimeMillis());
            String when = list.get(position).when;
            String[] split = hou.split(":");
            String[] splits = when.split(":");
            if(splits!=null&&splits.length==2){
                int hours = Integer.valueOf(split[0]) - Integer.valueOf(splits[0]);
                int hour = Integer.valueOf(split[1]) - Integer.valueOf(splits[1]);
                if(hours>0){

                    holder.tvContent.setVisibility(View.VISIBLE);
                }else if(hours==0){
                    if(hour>0){
                        holder.tvContent.setVisibility(View.VISIBLE);
                    }else {
                        holder.tvContent.setVisibility(View.INVISIBLE);
                    }
                }else {
                    holder.tvContent.setVisibility(View.INVISIBLE);
                }
            }


        }else {
            holder.tvContent.setVisibility(View.INVISIBLE);
        }
        if(TextUtil.isNotEmpty(list.get(position).when)){
            holder.tvTime.setText(list.get(position).when);
        }
        RemindItemAdapter adapter=new RemindItemAdapter(context);
        adapter.setData(list.get(position).items);
        holder.rllist.setAdapter(adapter);

           final   SwipeListLayout swipeListLayout = (SwipeListLayout) convertView;

           swipeListLayout.setOnSwipeStatusListener(new MyOnSlipStatusListener(
                   swipeListLayout));

           holder.tv_delete.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                   swipeListLayout.setStatus(SwipeListLayout.Status.Close, true);
                   if(listener!=null){
                       listener.delete(list.get(position),2,position);
                   }


               }
           });
        holder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.delete(list.get(position),3,position);
            }
        });
          holder.rl_bg.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  listener.delete(list.get(position),1,position);
              }
          });

        return convertView;
    }

    class ViewHolder {

        TextView tvTime;
        TextView tvContent;
        LinearLayout rl_bg;
        RegularListView rllist;
        TextView tv_delete;

    }
    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

        private SwipeListLayout slipListLayout;

        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
            } else {
                if (sets.contains(slipListLayout))
                    sets.remove(slipListLayout);
            }
        }

        @Override
        public void onStartCloseAnimation() {

        }

        @Override
        public void onStartOpenAnimation() {

        }

    }
}
