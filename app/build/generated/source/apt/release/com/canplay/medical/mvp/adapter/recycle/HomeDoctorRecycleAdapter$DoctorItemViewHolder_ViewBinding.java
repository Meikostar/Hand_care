// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.adapter.recycle;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeDoctorRecycleAdapter$DoctorItemViewHolder_ViewBinding implements Unbinder {
  private HomeDoctorRecycleAdapter.DoctorItemViewHolder target;

  @UiThread
  public HomeDoctorRecycleAdapter$DoctorItemViewHolder_ViewBinding(HomeDoctorRecycleAdapter.DoctorItemViewHolder target, View source) {
    this.target = target;

    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvPoistion = Utils.findRequiredViewAsType(source, R.id.tv_poistion, "field 'tvPoistion'", TextView.class);
    target.tvLocation = Utils.findRequiredViewAsType(source, R.id.tv_location, "field 'tvLocation'", TextView.class);
    target.llBg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llBg'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeDoctorRecycleAdapter.DoctorItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImg = null;
    target.tvName = null;
    target.tvPhone = null;
    target.tvPoistion = null;
    target.tvLocation = null;
    target.llBg = null;
  }
}
