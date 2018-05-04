// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeasureActivity_ViewBinding implements Unbinder {
  private MeasureActivity target;

  @UiThread
  public MeasureActivity_ViewBinding(MeasureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeasureActivity_ViewBinding(MeasureActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.llAgain = Utils.findRequiredViewAsType(source, R.id.ll_again, "field 'llAgain'", LinearLayout.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.llRing = Utils.findRequiredViewAsType(source, R.id.ll_ring, "field 'llRing'", LinearLayout.class);
    target.tvRing = Utils.findRequiredViewAsType(source, R.id.tv_ring, "field 'tvRing'", TextView.class);
    target.ll_project = Utils.findRequiredViewAsType(source, R.id.ll_project, "field 'll_project'", LinearLayout.class);
    target.tvAdd = Utils.findRequiredViewAsType(source, R.id.tv_add, "field 'tvAdd'", TextView.class);
    target.listview = Utils.findRequiredViewAsType(source, R.id.listview_all_city, "field 'listview'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasureActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvType = null;
    target.llAgain = null;
    target.tvTime = null;
    target.llRing = null;
    target.tvRing = null;
    target.ll_project = null;
    target.tvAdd = null;
    target.listview = null;
  }
}
