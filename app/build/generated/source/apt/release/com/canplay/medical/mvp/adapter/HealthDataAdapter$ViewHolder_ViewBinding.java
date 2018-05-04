// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HealthDataAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HealthDataAdapter.ViewHolder target;

  @UiThread
  public HealthDataAdapter$ViewHolder_ViewBinding(HealthDataAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvOne = Utils.findRequiredViewAsType(source, R.id.tv_one, "field 'tvOne'", TextView.class);
    target.tvOnes = Utils.findRequiredViewAsType(source, R.id.tv_ones, "field 'tvOnes'", TextView.class);
    target.tvTwo = Utils.findRequiredViewAsType(source, R.id.tv_two, "field 'tvTwo'", TextView.class);
    target.tvTwos = Utils.findRequiredViewAsType(source, R.id.tv_twos, "field 'tvTwos'", TextView.class);
    target.tvThree = Utils.findRequiredViewAsType(source, R.id.tv_three, "field 'tvThree'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.llOne = Utils.findRequiredViewAsType(source, R.id.ll_one, "field 'llOne'", LinearLayout.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvCout = Utils.findRequiredViewAsType(source, R.id.tv_cout, "field 'tvCout'", TextView.class);
    target.llTwo = Utils.findRequiredViewAsType(source, R.id.ll_two, "field 'llTwo'", LinearLayout.class);
    target.llbg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llbg'", LinearLayout.class);
    target.card = Utils.findRequiredViewAsType(source, R.id.card, "field 'card'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HealthDataAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvType = null;
    target.tvOne = null;
    target.tvOnes = null;
    target.tvTwo = null;
    target.tvTwos = null;
    target.tvThree = null;
    target.tvContent = null;
    target.llOne = null;
    target.ivImg = null;
    target.tvName = null;
    target.tvNumber = null;
    target.tvCout = null;
    target.llTwo = null;
    target.llbg = null;
    target.card = null;
  }
}
