// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BloodPressRecordActivity_ViewBinding implements Unbinder {
  private BloodPressRecordActivity target;

  @UiThread
  public BloodPressRecordActivity_ViewBinding(BloodPressRecordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BloodPressRecordActivity_ViewBinding(BloodPressRecordActivity target, View source) {
    this.target = target;

    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.mSuperRecyclerView = Utils.findRequiredViewAsType(source, R.id.super_recycle_view, "field 'mSuperRecyclerView'", SuperRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BloodPressRecordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.navigationBar = null;
    target.mSuperRecyclerView = null;
  }
}
