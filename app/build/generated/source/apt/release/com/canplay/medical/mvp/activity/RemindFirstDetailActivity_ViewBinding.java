// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RemindFirstDetailActivity_ViewBinding implements Unbinder {
  private RemindFirstDetailActivity target;

  @UiThread
  public RemindFirstDetailActivity_ViewBinding(RemindFirstDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RemindFirstDetailActivity_ViewBinding(RemindFirstDetailActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvTimes = Utils.findRequiredViewAsType(source, R.id.tv_times, "field 'tvTimes'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tvCount'", TextView.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", RegularListView.class);
    target.tvSure = Utils.findRequiredViewAsType(source, R.id.tv_sure, "field 'tvSure'", TextView.class);
    target.ivExpend = Utils.findRequiredViewAsType(source, R.id.iv_expend, "field 'ivExpend'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RemindFirstDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvTime = null;
    target.tvTimes = null;
    target.tvState = null;
    target.ivImg = null;
    target.tvType = null;
    target.tvCount = null;
    target.rlMenu = null;
    target.tvSure = null;
    target.ivExpend = null;
  }
}
