// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.HistogramPressView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LineCharFragment_ViewBinding implements Unbinder {
  private LineCharFragment target;

  @UiThread
  public LineCharFragment_ViewBinding(LineCharFragment target, View source) {
    this.target = target;

    target.hgmKpiFirst = Utils.findRequiredViewAsType(source, R.id.hgm_kpi_first, "field 'hgmKpiFirst'", HistogramPressView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LineCharFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hgmKpiFirst = null;
  }
}
