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

public class ForgetFirstActivity_ViewBinding implements Unbinder {
  private ForgetFirstActivity target;

  @UiThread
  public ForgetFirstActivity_ViewBinding(ForgetFirstActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetFirstActivity_ViewBinding(ForgetFirstActivity target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", ClearEditText.class);
    target.tvGetcode = Utils.findRequiredViewAsType(source, R.id.tv_getcode, "field 'tvGetcode'", TextView.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.et_code, "field 'etCode'", ClearEditText.class);
    target.tvSave = Utils.findRequiredViewAsType(source, R.id.tv_save, "field 'tvSave'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetFirstActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.etPhone = null;
    target.tvGetcode = null;
    target.etCode = null;
    target.tvSave = null;
  }
}
