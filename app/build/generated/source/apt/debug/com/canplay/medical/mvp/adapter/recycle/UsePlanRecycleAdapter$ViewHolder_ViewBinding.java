// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.adapter.recycle;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.DashView;
import com.canplay.medical.view.RegularListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UsePlanRecycleAdapter$ViewHolder_ViewBinding implements Unbinder {
  private UsePlanRecycleAdapter.ViewHolder target;

  @UiThread
  public UsePlanRecycleAdapter$ViewHolder_ViewBinding(UsePlanRecycleAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvCout = Utils.findRequiredViewAsType(source, R.id.tv_cout, "field 'tvCout'", TextView.class);
    target.line1 = Utils.findRequiredViewAsType(source, R.id.line1, "field 'line1'", DashView.class);
    target.line2 = Utils.findRequiredViewAsType(source, R.id.line2, "field 'line2'", DashView.class);
    target.ivCyc = Utils.findRequiredViewAsType(source, R.id.iv_cyc, "field 'ivCyc'", ImageView.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", RegularListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UsePlanRecycleAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTime = null;
    target.tvCout = null;
    target.line1 = null;
    target.line2 = null;
    target.ivCyc = null;
    target.rlMenu = null;
  }
}
