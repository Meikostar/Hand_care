// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MinePlanRemindActivity_ViewBinding implements Unbinder {
  private MinePlanRemindActivity target;

  @UiThread
  public MinePlanRemindActivity_ViewBinding(MinePlanRemindActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MinePlanRemindActivity_ViewBinding(MinePlanRemindActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.viewpagerMain = Utils.findRequiredViewAsType(source, R.id.viewpager_main, "field 'viewpagerMain'", NoScrollViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MinePlanRemindActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.viewpagerMain = null;
  }
}
