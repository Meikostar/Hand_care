// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.HistogramView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LineCharSugarFragment_ViewBinding implements Unbinder {
  private LineCharSugarFragment target;

  @UiThread
  public LineCharSugarFragment_ViewBinding(LineCharSugarFragment target, View source) {
    this.target = target;

    target.hgmKpiFirst = Utils.findRequiredViewAsType(source, R.id.hgm_kpi_first, "field 'hgmKpiFirst'", HistogramView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LineCharSugarFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hgmKpiFirst = null;
  }
}
