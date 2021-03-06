package io.cordova.qianshou.mvp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.cordova.qianshou.R;


/**
 * Created by mykar on 17/4/12.
 */
public class HealtCenterHolder extends RecyclerView.ViewHolder  {

    public ImageView img;
    public ImageView iv_arrow;
    public TextView name;
    public TextView phone;
    public TextView tv_address;
    public TextView tv_des;
    public Button add;
    public LinearLayout ll_bg;


    public HealtCenterHolder(View itemView,int type) {
        super(itemView);
        img= (ImageView) itemView.findViewById(R.id.iv_img);
        iv_arrow= (ImageView) itemView.findViewById(R.id.iv_arrow);
        name= (TextView) itemView.findViewById(R.id.tv_name);
        phone= (TextView) itemView.findViewById(R.id.tv_phone);
        add= (Button) itemView.findViewById(R.id.bt_add);
        ll_bg= (LinearLayout) itemView.findViewById(R.id.ll_bg);
       if(type==0){
           tv_address= (TextView) itemView.findViewById(R.id.tv_address);
           tv_des= (TextView) itemView.findViewById(R.id.tv_des);
       }

    }

}
