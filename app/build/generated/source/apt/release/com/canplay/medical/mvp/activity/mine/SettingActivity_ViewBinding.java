// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.llChangePsw = Utils.findRequiredViewAsType(source, R.id.ll_change_psw, "field 'llChangePsw'", LinearLayout.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.llPhone = Utils.findRequiredViewAsType(source, R.id.ll_phone, "field 'llPhone'", LinearLayout.class);
    target.tvVerison = Utils.findRequiredViewAsType(source, R.id.tv_verison, "field 'tvVerison'", TextView.class);
    target.llVersion = Utils.findRequiredViewAsType(source, R.id.ll_version, "field 'llVersion'", LinearLayout.class);
    target.tvExit = Utils.findRequiredViewAsType(source, R.id.tv_exit, "field 'tvExit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.llChangePsw = null;
    target.tvPhone = null;
    target.llPhone = null;
    target.tvVerison = null;
    target.llVersion = null;
    target.tvExit = null;
  }
}
