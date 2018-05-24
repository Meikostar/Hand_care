// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BloodRecordFragment_ViewBinding implements Unbinder {
  private BloodRecordFragment target;

  @UiThread
  public BloodRecordFragment_ViewBinding(BloodRecordFragment target, View source) {
    this.target = target;

    target.mSuperRecyclerView = Utils.findRequiredViewAsType(source, R.id.super_recycle_view, "field 'mSuperRecyclerView'", SuperRecyclerView.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loadingView, "field 'loadingView'", LoadingPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BloodRecordFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSuperRecyclerView = null;
    target.loadingView = null;
  }
}
