// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HomeAdapter.ViewHolder target;

  @UiThread
  public HomeAdapter$ViewHolder_ViewBinding(HomeAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tvImg = Utils.findRequiredViewAsType(source, R.id.tv_img, "field 'tvImg'", ImageView.class);
    target.tvDo = Utils.findRequiredViewAsType(source, R.id.tv_do, "field 'tvDo'", TextView.class);
    target.tvHour = Utils.findRequiredViewAsType(source, R.id.tv_hour, "field 'tvHour'", TextView.class);
    target.tvMinter = Utils.findRequiredViewAsType(source, R.id.tv_minter, "field 'tvMinter'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.llBg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llBg'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvImg = null;
    target.tvDo = null;
    target.tvHour = null;
    target.tvMinter = null;
    target.tvState = null;
    target.llBg = null;
  }
}
