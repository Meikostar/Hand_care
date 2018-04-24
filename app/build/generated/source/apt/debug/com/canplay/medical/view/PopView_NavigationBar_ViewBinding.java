// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PopView_NavigationBar_ViewBinding implements Unbinder {
  private PopView_NavigationBar target;

  @UiThread
  public PopView_NavigationBar_ViewBinding(PopView_NavigationBar target, View source) {
    this.target = target;

    target.tvNew = Utils.findRequiredViewAsType(source, R.id.tv_new, "field 'tvNew'", TextView.class);
    target.tvPreview = Utils.findRequiredViewAsType(source, R.id.tv_preview, "field 'tvPreview'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PopView_NavigationBar target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvNew = null;
    target.tvPreview = null;
  }
}
