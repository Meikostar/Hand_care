// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.DashView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserRecordAdapter$ViewHolder_ViewBinding implements Unbinder {
  private UserRecordAdapter.ViewHolder target;

  @UiThread
  public UserRecordAdapter$ViewHolder_ViewBinding(UserRecordAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.line1 = Utils.findRequiredViewAsType(source, R.id.line1, "field 'line1'", DashView.class);
    target.line2 = Utils.findRequiredViewAsType(source, R.id.line2, "field 'line2'", DashView.class);
    target.ivCyc = Utils.findRequiredViewAsType(source, R.id.iv_cyc, "field 'ivCyc'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvAmout = Utils.findRequiredViewAsType(source, R.id.tv_amout, "field 'tvAmout'", TextView.class);
    target.tvData = Utils.findRequiredViewAsType(source, R.id.tv_data, "field 'tvData'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserRecordAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTime = null;
    target.tvState = null;
    target.line1 = null;
    target.line2 = null;
    target.ivCyc = null;
    target.tvName = null;
    target.tvAmout = null;
    target.tvData = null;
  }
}
