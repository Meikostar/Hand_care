// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeasureRemindFragment_ViewBinding implements Unbinder {
  private MeasureRemindFragment target;

  @UiThread
  public MeasureRemindFragment_ViewBinding(MeasureRemindFragment target, View source) {
    this.target = target;

    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasureRemindFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlMenu = null;
  }
}
