// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindPhoneActivity_ViewBinding implements Unbinder {
  private BindPhoneActivity target;

  @UiThread
  public BindPhoneActivity_ViewBinding(BindPhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindPhoneActivity_ViewBinding(BindPhoneActivity target, View source) {
    this.target = target;

    target.navigationbar = Utils.findRequiredViewAsType(source, R.id.navigationbar, "field 'navigationbar'", NavigationBar.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvChange = Utils.findRequiredViewAsType(source, R.id.tv_change, "field 'tvChange'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BindPhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.navigationbar = null;
    target.tvPhone = null;
    target.tvChange = null;
  }
}
