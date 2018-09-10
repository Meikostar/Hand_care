package io.cordova.qianshou.mvp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import io.cordova.qianshou.R;
import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.bean.PREFIX;
import io.cordova.qianshou.util.PinYinUtils;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MCheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.cordova.qianshou.bean.Medicine;
import io.cordova.qianshou.util.PinYinUtils;
import io.cordova.qianshou.util.TextUtil;
import io.cordova.qianshou.view.MCheckBox;


public class Medicaldapter extends BaseAdapter {
    private static final int VIEW_TYPE_COUNT = 3;

    private Context mContext;
    private LayoutInflater inflater;
    private List<Medicine> mCities;
    private HashMap<String, Integer> letterIndexes;
    private String[] sections;
    private OnCityClickListener onCityClickListener;
    private String type = "location";
    private int status;

    private String locatedCity;


    public Medicaldapter(Context mContext, List<Medicine> mCities, int type) {
        status = type;
        this.mContext = mContext;
        this.mCities = mCities;
        this.inflater = LayoutInflater.from(mContext);
        if (mCities == null) {
            mCities = new ArrayList<>();
        }


        int size = mCities.size();
        letterIndexes = new HashMap<>();
        sections = new String[size];
        for (int index = 0; index < size; index++) {
            //当前城市拼音首字母
            String currentLetter = PinYinUtils.getFirstLetter(mCities.get(index).filter);
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? PinYinUtils.getFirstLetter(mCities.get(index - 1).filter) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)) {
                letterIndexes.put(currentLetter, index);
                sections[index] = currentLetter;
            }
        }
    }

    /**
     * 更新定位状态
     *
     * @param state
     */
    public void updateLocateState(int state, String city) {

        this.locatedCity = city;
        notifyDataSetChanged();
    }
   public List<Medicine> getData(){
       return mCities;
   }
    /**
     * 获取字母索引的位置
     *
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter) {
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }


    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public Medicine getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final CityViewHolder holder;

        if (view == null) {
            view = View.inflate(mContext, R.layout.choose_black_items, null);
            holder = new CityViewHolder();
            holder.letter = (TextView) view.findViewById(R.id.tv_item_city_listview_letter);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            holder.number = (TextView) view.findViewById(R.id.tv_number);
            holder.ll_bg = (LinearLayout) view.findViewById(R.id.ll_bg);
            holder.img = (ImageView) view.findViewById(R.id.iv_img);
            holder.check = (MCheckBox) view.findViewById(R.id.iv_choose);
            view.setTag(holder);
        } else {
            holder = (CityViewHolder) view.getTag();
        }

       if(TextUtil.isNotEmpty(mCities.get(position).medicine)){
           holder.name.setText(mCities.get(position).medicine);
       }
        holder.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCities.get(position).isCheck){
                    holder.check.setChecked(false);
                    mCities.get(position).isCheck=false;
                }else {
                    holder.check.setChecked(true);
                    mCities.get(position).isCheck=true;
                }
            }
        });
        Glide.with(mContext).load(mCities.get(position).image).asBitmap().placeholder(R.drawable.moren).into(holder.img);
//        String city = null;
//        if (status != 0) {
//            city = mCities.get(position).displayName;
//        } else {
//            city = mCities.get(position).displayName;
//        }
//
//        if (position >= 1) {
//
////            holder.name.setText(city);
//            String filter ;
//            String lastfilter = "";
//
//                filter = mCities.get(position).filter;
//                if(position>0){
//                    lastfilter = mCities.get(position-1).filter;
//                }
//
//
////            String currentLetter = PinYinUtils.getFirstLetter(filter);
////            String previousLetter = position >= 1 ? PinYinUtils.getFirstLetter(lastfilter) : "";
////            if (!TextUtils.equals(currentLetter, previousLetter)) {
////                holder.letter.setVisibility(View.VISIBLE);
////                holder.letter.setText(currentLetter);
////            } else {
////                holder.letter.setVisibility(View.GONE);
////            }
//            holder.ll_bg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onCityClickListener != null) {
//                        onCityClickListener.onCityClick(null);
//                    }
//                }
//            });
//        } else {
////            holder.name.setText(city);
//
//            holder.letter.setVisibility(View.GONE);
//            holder.letter.setText("A");
//            holder.ll_bg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onCityClickListener != null) {
//                        onCityClickListener.onCityClick(null);
//                    }
//                }
//            });
//
//        }
        return view;
    }

    public static class CityViewHolder {
        TextView letter;
        TextView name;
        TextView number;
        ImageView img;
        MCheckBox check;
        LinearLayout ll_bg;
    }

    public void setOnCityClickListener(OnCityClickListener listener) {
        this.onCityClickListener = listener;
    }

    public interface OnCityClickListener {
        void onCityClick(String name);

        void onLocateClick();
    }
}
