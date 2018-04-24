// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.tvLogo = Utils.findRequiredViewAsType(source, R.id.tv_logo, "field 'tvLogo'", ImageView.class);
    target.etUser = Utils.findRequiredViewAsType(source, R.id.et_user, "field 'etUser'", ClearEditText.class);
    target.etPws = Utils.findRequiredViewAsType(source, R.id.et_pws, "field 'etPws'", ClearEditText.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tv_login, "field 'tvLogin'", TextView.class);
    target.tvForget = Utils.findRequiredViewAsType(source, R.id.tv_forget, "field 'tvForget'", TextView.class);
    target.tvRegistered = Utils.findRequiredViewAsType(source, R.id.tv_registered, "field 'tvRegistered'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvLogo = null;
    target.etUser = null;
    target.etPws = null;
    target.tvLogin = null;
    target.tvForget = null;
    target.tvRegistered = null;
  }
}
