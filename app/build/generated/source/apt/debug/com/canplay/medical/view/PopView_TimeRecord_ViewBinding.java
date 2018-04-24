// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PopView_TimeRecord_ViewBinding implements Unbinder {
  private PopView_TimeRecord target;

  @UiThread
  public PopView_TimeRecord_ViewBinding(PopView_TimeRecord target, View source) {
    this.target = target;

    target.tvAll = Utils.findRequiredViewAsType(source, R.id.ll_all, "field 'tvAll'", LinearLayout.class);
    target.tvPress = Utils.findRequiredViewAsType(source, R.id.ll_press, "field 'tvPress'", LinearLayout.class);
    target.tvEat = Utils.findRequiredViewAsType(source, R.id.ll_eat, "field 'tvEat'", LinearLayout.class);
    target.tvSugar = Utils.findRequiredViewAsType(source, R.id.ll_sugar, "field 'tvSugar'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PopView_TimeRecord target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvAll = null;
    target.tvPress = null;
    target.tvEat = null;
    target.tvSugar = null;
  }
}
