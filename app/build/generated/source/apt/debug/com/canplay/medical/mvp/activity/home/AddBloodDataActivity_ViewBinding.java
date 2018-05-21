// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddBloodDataActivity_ViewBinding implements Unbinder {
  private AddBloodDataActivity target;

  @UiThread
  public AddBloodDataActivity_ViewBinding(AddBloodDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddBloodDataActivity_ViewBinding(AddBloodDataActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.llChangePsw = Utils.findRequiredViewAsType(source, R.id.ll_change_psw, "field 'llChangePsw'", LinearLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.etOne = Utils.findRequiredViewAsType(source, R.id.et_one, "field 'etOne'", EditText.class);
    target.etTwo = Utils.findRequiredViewAsType(source, R.id.et_two, "field 'etTwo'", EditText.class);
    target.etThree = Utils.findRequiredViewAsType(source, R.id.et_three, "field 'etThree'", EditText.class);
    target.llType = Utils.findRequiredViewAsType(source, R.id.ll_type, "field 'llType'", LinearLayout.class);
    target.tvType1 = Utils.findRequiredViewAsType(source, R.id.tv_type1, "field 'tvType1'", TextView.class);
    target.tvType3 = Utils.findRequiredViewAsType(source, R.id.tv_type3, "field 'tvType3'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddBloodDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvType = null;
    target.llChangePsw = null;
    target.tvName = null;
    target.etOne = null;
    target.etTwo = null;
    target.etThree = null;
    target.llType = null;
    target.tvType1 = null;
    target.tvType3 = null;
  }
}
