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

public class ChartFragment_ViewBinding implements Unbinder {
  private ChartFragment target;

  @UiThread
  public ChartFragment_ViewBinding(ChartFragment target, View source) {
    this.target = target;

    target.viewpagerMain = Utils.findRequiredViewAsType(source, R.id.viewpager_main, "field 'viewpagerMain'", NoScrollViewPager.class);
    target.tvPress = Utils.findRequiredViewAsType(source, R.id.tv_press, "field 'tvPress'", TextView.class);
    target.tvData = Utils.findRequiredViewAsType(source, R.id.tv_data, "field 'tvData'", TextView.class);
    target.textView = Utils.findRequiredViewAsType(source, R.id.textView, "field 'textView'", TextView.class);
    target.llCenter = Utils.findRequiredViewAsType(source, R.id.ll_center, "field 'llCenter'", LinearLayout.class);
    target.tvCone = Utils.findRequiredViewAsType(source, R.id.tv_cone, "field 'tvCone'", TextView.class);
    target.tvCtwo = Utils.findRequiredViewAsType(source, R.id.tv_ctwo, "field 'tvCtwo'", TextView.class);
    target.tvCthree = Utils.findRequiredViewAsType(source, R.id.tv_cthree, "field 'tvCthree'", TextView.class);
    target.tvState2 = Utils.findRequiredViewAsType(source, R.id.tv_state2, "field 'tvState2'", TextView.class);
    target.tvState1 = Utils.findRequiredViewAsType(source, R.id.tv_state1, "field 'tvState1'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChartFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewpagerMain = null;
    target.tvPress = null;
    target.tvData = null;
    target.textView = null;
    target.llCenter = null;
    target.tvCone = null;
    target.tvCtwo = null;
    target.tvCthree = null;
    target.tvState2 = null;
    target.tvState1 = null;
    target.tvState = null;
    target.tvTime = null;
  }
}
