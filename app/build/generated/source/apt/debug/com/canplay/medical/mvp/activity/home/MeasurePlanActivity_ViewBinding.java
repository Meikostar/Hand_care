// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.scrollView.StickyScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeasurePlanActivity_ViewBinding implements Unbinder {
  private MeasurePlanActivity target;

  @UiThread
  public MeasurePlanActivity_ViewBinding(MeasurePlanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeasurePlanActivity_ViewBinding(MeasurePlanActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvHour = Utils.findRequiredViewAsType(source, R.id.tv_hour, "field 'tvHour'", TextView.class);
    target.tvMinter = Utils.findRequiredViewAsType(source, R.id.tv_minter, "field 'tvMinter'", TextView.class);
    target.tvSecond = Utils.findRequiredViewAsType(source, R.id.tv_second, "field 'tvSecond'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", ListView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", StickyScrollView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.imgEmpty = Utils.findRequiredViewAsType(source, R.id.img_empty, "field 'imgEmpty'", ImageView.class);
    target.txtDesc = Utils.findRequiredViewAsType(source, R.id.txt_desc, "field 'txtDesc'", TextView.class);
    target.rlBg = Utils.findRequiredViewAsType(source, R.id.rl_bg, "field 'rlBg'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasurePlanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.tvHour = null;
    target.tvMinter = null;
    target.tvSecond = null;
    target.tvContent = null;
    target.rlMenu = null;
    target.tvTime = null;
    target.scrollView = null;
    target.tvState = null;
    target.imgEmpty = null;
    target.txtDesc = null;
    target.rlBg = null;
  }
}
