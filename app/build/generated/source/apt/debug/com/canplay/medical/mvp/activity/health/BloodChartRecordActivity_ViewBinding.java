// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.health;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;
import com.canplay.medical.view.TwoNevgBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BloodChartRecordActivity_ViewBinding implements Unbinder {
  private BloodChartRecordActivity target;

  @UiThread
  public BloodChartRecordActivity_ViewBinding(BloodChartRecordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BloodChartRecordActivity_ViewBinding(BloodChartRecordActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.neBar = Utils.findRequiredViewAsType(source, R.id.ne_bar, "field 'neBar'", TwoNevgBar.class);
    target.viewpagerMain = Utils.findRequiredViewAsType(source, R.id.viewpager_main, "field 'viewpagerMain'", NoScrollViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BloodChartRecordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.neBar = null;
    target.viewpagerMain = null;
  }
}
