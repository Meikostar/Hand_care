// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.adapter.recycle;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.DashView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserTimeAdapter$ViewHolders_ViewBinding implements Unbinder {
  private UserTimeAdapter.ViewHolders target;

  @UiThread
  public UserTimeAdapter$ViewHolders_ViewBinding(UserTimeAdapter.ViewHolders target, View source) {
    this.target = target;

    target.line1 = Utils.findRequiredViewAsType(source, R.id.line1, "field 'line1'", DashView.class);
    target.line2 = Utils.findRequiredViewAsType(source, R.id.line2, "field 'line2'", DashView.class);
    target.ivCyc = Utils.findRequiredViewAsType(source, R.id.iv_cyc, "field 'ivCyc'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserTimeAdapter.ViewHolders target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line1 = null;
    target.line2 = null;
    target.ivCyc = null;
    target.tvName = null;
    target.tvContent = null;
    target.tvTime = null;
  }
}
