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

public class ForgetPswActivity_ViewBinding implements Unbinder {
  private ForgetPswActivity target;

  @UiThread
  public ForgetPswActivity_ViewBinding(ForgetPswActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPswActivity_ViewBinding(ForgetPswActivity target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvPsw = Utils.findRequiredViewAsType(source, R.id.tv_psw, "field 'tvPsw'", TextView.class);
    target.etPws = Utils.findRequiredViewAsType(source, R.id.et_pws, "field 'etPws'", ClearEditText.class);
    target.etNpws = Utils.findRequiredViewAsType(source, R.id.et_npws, "field 'etNpws'", ClearEditText.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tv_login, "field 'tvLogin'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPswActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvPsw = null;
    target.etPws = null;
    target.etNpws = null;
    target.tvLogin = null;
  }
}
