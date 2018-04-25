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
import com.canplay.medical.view.RegularListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MedicalDetailActivity_ViewBinding implements Unbinder {
  private MedicalDetailActivity target;

  @UiThread
  public MedicalDetailActivity_ViewBinding(MedicalDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MedicalDetailActivity_ViewBinding(MedicalDetailActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.listview = Utils.findRequiredViewAsType(source, R.id.listview, "field 'listview'", RegularListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MedicalDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.ivImg = null;
    target.tvName = null;
    target.listview = null;
  }
}
