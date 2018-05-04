// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EquipDetailActivity_ViewBinding implements Unbinder {
  private EquipDetailActivity target;

  @UiThread
  public EquipDetailActivity_ViewBinding(EquipDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EquipDetailActivity_ViewBinding(EquipDetailActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvCode = Utils.findRequiredViewAsType(source, R.id.tv_code, "field 'tvCode'", TextView.class);
    target.tvDetail = Utils.findRequiredViewAsType(source, R.id.tv_detail, "field 'tvDetail'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EquipDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.ivImg = null;
    target.tvName = null;
    target.tvCode = null;
    target.tvDetail = null;
  }
}
