// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;
import com.canplay.medical.view.loadingView.LoadingPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineEuipmentActivity_ViewBinding implements Unbinder {
  private MineEuipmentActivity target;

  @UiThread
  public MineEuipmentActivity_ViewBinding(MineEuipmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineEuipmentActivity_ViewBinding(MineEuipmentActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", RegularListView.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loadingView, "field 'loadingView'", LoadingPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineEuipmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.rlMenu = null;
    target.loadingView = null;
  }
}
