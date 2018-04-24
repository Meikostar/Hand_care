// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditorPwsActivity_ViewBinding implements Unbinder {
  private EditorPwsActivity target;

  @UiThread
  public EditorPwsActivity_ViewBinding(EditorPwsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditorPwsActivity_ViewBinding(EditorPwsActivity target, View source) {
    this.target = target;

    target.navigationbar = Utils.findRequiredViewAsType(source, R.id.navigationbar, "field 'navigationbar'", NavigationBar.class);
    target.etOldPsw = Utils.findRequiredViewAsType(source, R.id.et_old_psw, "field 'etOldPsw'", ClearEditText.class);
    target.etPsw = Utils.findRequiredViewAsType(source, R.id.et_psw, "field 'etPsw'", ClearEditText.class);
    target.etSurePsw = Utils.findRequiredViewAsType(source, R.id.et_sure_psw, "field 'etSurePsw'", ClearEditText.class);
    target.tvSave = Utils.findRequiredViewAsType(source, R.id.tv_save, "field 'tvSave'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditorPwsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.navigationbar = null;
    target.etOldPsw = null;
    target.etPsw = null;
    target.etSurePsw = null;
    target.tvSave = null;
  }
}
