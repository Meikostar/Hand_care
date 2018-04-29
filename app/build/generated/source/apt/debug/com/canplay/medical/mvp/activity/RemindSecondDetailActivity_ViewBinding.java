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
import com.canplay.medical.view.NoScrollGridView;
import com.canplay.medical.view.RegularListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RemindSecondDetailActivity_ViewBinding implements Unbinder {
  private RemindSecondDetailActivity target;

  @UiThread
  public RemindSecondDetailActivity_ViewBinding(RemindSecondDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RemindSecondDetailActivity_ViewBinding(RemindSecondDetailActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvTimes = Utils.findRequiredViewAsType(source, R.id.tv_times, "field 'tvTimes'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.ivExpend = Utils.findRequiredViewAsType(source, R.id.iv_expend, "field 'ivExpend'", ImageView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tvCount'", TextView.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", RegularListView.class);
    target.gridView = Utils.findRequiredViewAsType(source, R.id.grid_view, "field 'gridView'", NoScrollGridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RemindSecondDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvTime = null;
    target.tvTimes = null;
    target.tvState = null;
    target.ivImg = null;
    target.tvType = null;
    target.ivExpend = null;
    target.tvCount = null;
    target.rlMenu = null;
    target.gridView = null;
  }
}
