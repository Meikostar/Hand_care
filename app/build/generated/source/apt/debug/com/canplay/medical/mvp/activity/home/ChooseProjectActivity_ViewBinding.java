// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.MCheckBox;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChooseProjectActivity_ViewBinding implements Unbinder {
  private ChooseProjectActivity target;

  @UiThread
  public ChooseProjectActivity_ViewBinding(ChooseProjectActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChooseProjectActivity_ViewBinding(ChooseProjectActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.cbPress = Utils.findRequiredViewAsType(source, R.id.cb_press, "field 'cbPress'", MCheckBox.class);
    target.llPress = Utils.findRequiredViewAsType(source, R.id.ll_press, "field 'llPress'", LinearLayout.class);
    target.cbSugar = Utils.findRequiredViewAsType(source, R.id.cb_sugar, "field 'cbSugar'", MCheckBox.class);
    target.llSugar = Utils.findRequiredViewAsType(source, R.id.ll_sugar, "field 'llSugar'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooseProjectActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.cbPress = null;
    target.llPress = null;
    target.cbSugar = null;
    target.llSugar = null;
  }
}
