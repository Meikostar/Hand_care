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

public class RegisteredSecondActivity_ViewBinding implements Unbinder {
  private RegisteredSecondActivity target;

  @UiThread
  public RegisteredSecondActivity_ViewBinding(RegisteredSecondActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisteredSecondActivity_ViewBinding(RegisteredSecondActivity target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", ClearEditText.class);
    target.etFist = Utils.findRequiredViewAsType(source, R.id.et_fist, "field 'etFist'", TextView.class);
    target.etLast = Utils.findRequiredViewAsType(source, R.id.et_last, "field 'etLast'", ClearEditText.class);
    target.etPws = Utils.findRequiredViewAsType(source, R.id.et_pws, "field 'etPws'", ClearEditText.class);
    target.etPwss = Utils.findRequiredViewAsType(source, R.id.et_pwss, "field 'etPwss'", ClearEditText.class);
    target.tvSave = Utils.findRequiredViewAsType(source, R.id.tv_save, "field 'tvSave'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisteredSecondActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.etName = null;
    target.etFist = null;
    target.etLast = null;
    target.etPws = null;
    target.etPwss = null;
    target.tvSave = null;
  }
}
