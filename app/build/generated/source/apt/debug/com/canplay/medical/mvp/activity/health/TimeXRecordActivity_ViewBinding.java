// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.health;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimeXRecordActivity_ViewBinding implements Unbinder {
  private TimeXRecordActivity target;

  @UiThread
  public TimeXRecordActivity_ViewBinding(TimeXRecordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TimeXRecordActivity_ViewBinding(TimeXRecordActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.rlList = Utils.findRequiredViewAsType(source, R.id.rl_list, "field 'rlList'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TimeXRecordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.rlList = null;
  }
}
