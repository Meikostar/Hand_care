package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Collect;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MCheckBox;
import io.cordova.qianshou.view.RegularListView;
import io.cordova.qianshou.view.SwipeListLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MCheckBox;
import io.cordova.qianshou.view.SwipeListLayout;

/**
 * Created by honghouyang on 16/12/23.
 */

public class RemindMeasureAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Medicine> list;
    private int type;
    private ListView lv_content;
    private Set<SwipeListLayout> sets = new HashSet();
    private selectItemListener listener;

    public interface selectItemListener {
        void delete(Medicine medicine, int type, int poistion);
    }

    public void setListener(selectItemListener listener) {
        this.listener = listener;
    }

    public void setData(List<Medicine> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Medicine> getDatas() {
        return list;
    }

    public RemindMeasureAdapter(Context context, List<Medicine> list, ListView lv_content) {
        this.lv_content = lv_content;
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
        return list != null ? (list.size() == 0 ? 0 : list.size()) : 0;
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
            convertView = inflater.inflate(R.layout.item_remind_mesure, parent, false);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            holder.ivchoose = (MCheckBox) convertView.findViewById(R.id.iv_choose);
            holder.llbg = (LinearLayout) convertView.findViewById(R.id.ll_bg);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final SwipeListLayout swipeListLayout = (SwipeListLayout) convertView;

        swipeListLayout.setOnSwipeStatusListener(new MyOnSlipStatusListener(
                swipeListLayout));

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                swipeListLayout.setStatus(SwipeListLayout.Status.Close, true);
                if (listener != null) {
                    listener.delete(list.get(position), 0, position);
                }


            }
        });
        holder.llbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.delete(list.get(position), 1, position);
            }
        });
        holder.llbg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.delete(list.get(position), 2, position);
                return true;
            }
        });
        if (TextUtil.isNotEmpty(list.get(position).items.get(0).name)) {
            holder.tvContent.setText(list.get(position).items.get(0).name);
        }
        if (TextUtil.isNotEmpty(list.get(position).when)) {
            holder.tvTime.setText(list.get(position).when);
        }
        if (list.get(position).completedForToday) {
            holder.ivchoose.setChecked(true);
        } else {
            holder.ivchoose.setChecked(false);
        }

        return convertView;
    }

    class ViewHolder {

        TextView tvTime;
        TextView tvContent;
        MCheckBox ivchoose;

        TextView tv_delete;

        LinearLayout llbg;
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
