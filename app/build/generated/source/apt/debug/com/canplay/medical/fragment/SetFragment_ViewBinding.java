// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetFragment_ViewBinding implements Unbinder {
  private SetFragment target;

  @UiThread
  public SetFragment_ViewBinding(SetFragment target, View source) {
    this.target = target;

    target.ivBox = Utils.findRequiredViewAsType(source, R.id.iv_box, "field 'ivBox'", ImageView.class);
    target.ivSetting = Utils.findRequiredViewAsType(source, R.id.iv_setting, "field 'ivSetting'", ImageView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvBirth = Utils.findRequiredViewAsType(source, R.id.tv_birth, "field 'tvBirth'", TextView.class);
    target.tvSex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tvSex'", TextView.class);
    target.card = Utils.findRequiredViewAsType(source, R.id.card, "field 'card'", CardView.class);
    target.llBg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llBg'", LinearLayout.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", ListView.class);
    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
  }

  @Override
  @CallSuper
  public void unbind() {
    SetFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBox = null;
    target.ivSetting = null;
    target.ivImg = null;
    target.tvName = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.tvBirth = null;
    target.tvSex = null;
    target.card = null;
    target.llBg = null;
    target.rlMenu = null;
    target.line = null;
  }
}
