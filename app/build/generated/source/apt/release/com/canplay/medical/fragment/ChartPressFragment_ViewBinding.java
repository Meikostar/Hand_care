// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChartPressFragment_ViewBinding implements Unbinder {
  private ChartPressFragment target;

  @UiThread
  public ChartPressFragment_ViewBinding(ChartPressFragment target, View source) {
    this.target = target;

    target.tvPress = Utils.findRequiredViewAsType(source, R.id.tv_press, "field 'tvPress'", TextView.class);
    target.tvData = Utils.findRequiredViewAsType(source, R.id.tv_data, "field 'tvData'", TextView.class);
    target.tvRate = Utils.findRequiredViewAsType(source, R.id.tv_rate, "field 'tvRate'", TextView.class);
    target.llCenter = Utils.findRequiredViewAsType(source, R.id.ll_center, "field 'llCenter'", LinearLayout.class);
    target.tvOne = Utils.findRequiredViewAsType(source, R.id.tv_one, "field 'tvOne'", TextView.class);
    target.tvTwo = Utils.findRequiredViewAsType(source, R.id.tv_two, "field 'tvTwo'", TextView.class);
    target.tvCone = Utils.findRequiredViewAsType(source, R.id.tv_cone, "field 'tvCone'", TextView.class);
    target.tvCtwo = Utils.findRequiredViewAsType(source, R.id.tv_ctwo, "field 'tvCtwo'", TextView.class);
    target.tvCthree = Utils.findRequiredViewAsType(source, R.id.tv_cthree, "field 'tvCthree'", TextView.class);
    target.viewpagerMain = Utils.findRequiredViewAsType(source, R.id.viewpager_main, "field 'viewpagerMain'", NoScrollViewPager.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.tvState1 = Utils.findRequiredViewAsType(source, R.id.tv_state1, "field 'tvState1'", TextView.class);
    target.tvState2 = Utils.findRequiredViewAsType(source, R.id.tv_state2, "field 'tvState2'", TextView.class);
    target.tvState3 = Utils.findRequiredViewAsType(source, R.id.tv_state3, "field 'tvState3'", TextView.class);
    target.tvState4 = Utils.findRequiredViewAsType(source, R.id.tv_state4, "field 'tvState4'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChartPressFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPress = null;
    target.tvData = null;
    target.tvRate = null;
    target.llCenter = null;
    target.tvOne = null;
    target.tvTwo = null;
    target.tvCone = null;
    target.tvCtwo = null;
    target.tvCthree = null;
    target.viewpagerMain = null;
    target.tvTime = null;
    target.tvState = null;
    target.tvState1 = null;
    target.tvState2 = null;
    target.tvState3 = null;
    target.tvState4 = null;
  }
}
