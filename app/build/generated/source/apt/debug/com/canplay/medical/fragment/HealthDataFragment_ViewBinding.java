// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.loadingView.LoadingPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HealthDataFragment_ViewBinding implements Unbinder {
  private HealthDataFragment target;

  @UiThread
  public HealthDataFragment_ViewBinding(HealthDataFragment target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.rlList = Utils.findRequiredViewAsType(source, R.id.rl_list, "field 'rlList'", ListView.class);
    target.llbg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llbg'", LinearLayout.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loadingView, "field 'loadingView'", LoadingPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HealthDataFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.rlList = null;
    target.llbg = null;
    target.loadingView = null;
  }
}
