// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineInfoActivity_ViewBinding implements Unbinder {
  private MineInfoActivity target;

  @UiThread
  public MineInfoActivity_ViewBinding(MineInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineInfoActivity_ViewBinding(MineInfoActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.ivPhone = Utils.findRequiredViewAsType(source, R.id.iv_phone, "field 'ivPhone'", ImageView.class);
    target.llCenter = Utils.findRequiredViewAsType(source, R.id.ll_center, "field 'llCenter'", LinearLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.llName = Utils.findRequiredViewAsType(source, R.id.ll_name, "field 'llName'", LinearLayout.class);
    target.tvSex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tvSex'", TextView.class);
    target.llSex = Utils.findRequiredViewAsType(source, R.id.ll_sex, "field 'llSex'", LinearLayout.class);
    target.llBirth = Utils.findRequiredViewAsType(source, R.id.ll_birth, "field 'llBirth'", LinearLayout.class);
    target.llArea = Utils.findRequiredViewAsType(source, R.id.ll_area, "field 'llArea'", LinearLayout.class);
    target.ll_code = Utils.findRequiredViewAsType(source, R.id.ll_code, "field 'll_code'", LinearLayout.class);
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.tvBirth = Utils.findRequiredViewAsType(source, R.id.tv_birth, "field 'tvBirth'", TextView.class);
    target.tv_area = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tv_area'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.ivPhone = null;
    target.llCenter = null;
    target.tvName = null;
    target.llName = null;
    target.tvSex = null;
    target.llSex = null;
    target.llBirth = null;
    target.llArea = null;
    target.ll_code = null;
    target.navigationBar = null;
    target.tvBirth = null;
    target.tv_area = null;
  }
}
