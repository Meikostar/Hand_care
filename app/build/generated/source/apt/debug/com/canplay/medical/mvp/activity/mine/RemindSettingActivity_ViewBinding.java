// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.HourSelector;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RemindSettingActivity_ViewBinding implements Unbinder {
  private RemindSettingActivity target;

  @UiThread
  public RemindSettingActivity_ViewBinding(RemindSettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RemindSettingActivity_ViewBinding(RemindSettingActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvHave = Utils.findRequiredViewAsType(source, R.id.tv_have, "field 'tvHave'", TextView.class);
    target.llBox = Utils.findRequiredViewAsType(source, R.id.ll_box, "field 'llBox'", LinearLayout.class);
    target.tvAdd = Utils.findRequiredViewAsType(source, R.id.tv_add, "field 'tvAdd'", TextView.class);
    target.lvInfo = Utils.findRequiredViewAsType(source, R.id.lv_info, "field 'lvInfo'", RegularListView.class);
    target.tvFrequency = Utils.findRequiredViewAsType(source, R.id.tv_frequency, "field 'tvFrequency'", TextView.class);
    target.llAgain = Utils.findRequiredViewAsType(source, R.id.ll_again, "field 'llAgain'", LinearLayout.class);
    target.tvRing = Utils.findRequiredViewAsType(source, R.id.tv_ring, "field 'tvRing'", TextView.class);
    target.llRing = Utils.findRequiredViewAsType(source, R.id.ll_ring, "field 'llRing'", LinearLayout.class);
    target.selector = Utils.findRequiredViewAsType(source, R.id.selector, "field 'selector'", HourSelector.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RemindSettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvHave = null;
    target.llBox = null;
    target.tvAdd = null;
    target.lvInfo = null;
    target.tvFrequency = null;
    target.llAgain = null;
    target.tvRing = null;
    target.llRing = null;
    target.selector = null;
  }
}
