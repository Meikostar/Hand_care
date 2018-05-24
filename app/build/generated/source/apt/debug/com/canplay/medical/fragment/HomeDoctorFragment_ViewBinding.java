// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeDoctorFragment_ViewBinding implements Unbinder {
  private HomeDoctorFragment target;

  @UiThread
  public HomeDoctorFragment_ViewBinding(HomeDoctorFragment target, View source) {
    this.target = target;

    target.mSuperRecyclerView = Utils.findRequiredViewAsType(source, R.id.super_recycle_view, "field 'mSuperRecyclerView'", SuperRecyclerView.class);
    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.ivAdd = Utils.findRequiredViewAsType(source, R.id.iv_add, "field 'ivAdd'", ImageView.class);
    target.navigationbarTitle = Utils.findRequiredViewAsType(source, R.id.navigationbar_title, "field 'navigationbarTitle'", TextView.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loadingView, "field 'loadingView'", LoadingPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeDoctorFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSuperRecyclerView = null;
    target.line = null;
    target.ivAdd = null;
    target.navigationbarTitle = null;
    target.loadingView = null;
  }
}
