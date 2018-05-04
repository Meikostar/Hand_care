// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RemindHealthActivity_ViewBinding implements Unbinder {
  private RemindHealthActivity target;

  @UiThread
  public RemindHealthActivity_ViewBinding(RemindHealthActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RemindHealthActivity_ViewBinding(RemindHealthActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.viewpagerMain = Utils.findRequiredViewAsType(source, R.id.viewpager_main, "field 'viewpagerMain'", NoScrollViewPager.class);
    target.tvUse = Utils.findRequiredViewAsType(source, R.id.tv_use, "field 'tvUse'", TextView.class);
    target.tvMerure = Utils.findRequiredViewAsType(source, R.id.tv_merure, "field 'tvMerure'", TextView.class);
    target.tvAdd = Utils.findRequiredViewAsType(source, R.id.tv_add, "field 'tvAdd'", TextView.class);
    target.ivline = Utils.findRequiredViewAsType(source, R.id.ivline, "field 'ivline'", ImageView.class);
    target.ivlines = Utils.findRequiredViewAsType(source, R.id.ivlines, "field 'ivlines'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RemindHealthActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.viewpagerMain = null;
    target.tvUse = null;
    target.tvMerure = null;
    target.tvAdd = null;
    target.ivline = null;
    target.ivlines = null;
  }
}
