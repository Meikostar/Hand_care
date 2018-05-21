// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;
import com.canplay.medical.view.scrollView.StickyScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UsePlanActivity_ViewBinding implements Unbinder {
  private UsePlanActivity target;

  @UiThread
  public UsePlanActivity_ViewBinding(UsePlanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UsePlanActivity_ViewBinding(UsePlanActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvHour = Utils.findRequiredViewAsType(source, R.id.tv_hour, "field 'tvHour'", TextView.class);
    target.tvMinter = Utils.findRequiredViewAsType(source, R.id.tv_minter, "field 'tvMinter'", TextView.class);
    target.tvSecond = Utils.findRequiredViewAsType(source, R.id.tv_second, "field 'tvSecond'", TextView.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", RegularListView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.stubGird = Utils.findRequiredViewAsType(source, R.id.stub_gird, "field 'stubGird'", ViewStub.class);
    target.ivState = Utils.findRequiredViewAsType(source, R.id.iv_state, "field 'ivState'", ImageView.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", StickyScrollView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.llBg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llBg'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UsePlanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvHour = null;
    target.tvMinter = null;
    target.tvSecond = null;
    target.rlMenu = null;
    target.tvTime = null;
    target.tvName = null;
    target.stubGird = null;
    target.ivState = null;
    target.scrollView = null;
    target.tvState = null;
    target.llBg = null;
  }
}
