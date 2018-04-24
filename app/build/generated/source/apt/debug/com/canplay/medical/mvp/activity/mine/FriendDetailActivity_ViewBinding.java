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
import java.lang.IllegalStateException;
import java.lang.Override;

public class FriendDetailActivity_ViewBinding implements Unbinder {
  private FriendDetailActivity target;

  @UiThread
  public FriendDetailActivity_ViewBinding(FriendDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FriendDetailActivity_ViewBinding(FriendDetailActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.ivAvatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar, "field 'ivAvatar'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.ivSex = Utils.findRequiredViewAsType(source, R.id.iv_sex, "field 'ivSex'", ImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvBind = Utils.findRequiredViewAsType(source, R.id.tv_bind, "field 'tvBind'", TextView.class);
    target.tvBirth = Utils.findRequiredViewAsType(source, R.id.tv_birth, "field 'tvBirth'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.llBloodPress = Utils.findRequiredViewAsType(source, R.id.ll_blood_press, "field 'llBloodPress'", LinearLayout.class);
    target.llBloodSugar = Utils.findRequiredViewAsType(source, R.id.ll_blood_sugar, "field 'llBloodSugar'", LinearLayout.class);
    target.llMedicalPlan = Utils.findRequiredViewAsType(source, R.id.ll_Medical_plan, "field 'llMedicalPlan'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FriendDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.ivBack = null;
    target.ivAvatar = null;
    target.tvName = null;
    target.ivSex = null;
    target.tvPhone = null;
    target.tvBind = null;
    target.tvBirth = null;
    target.tvAddress = null;
    target.llBloodPress = null;
    target.llBloodSugar = null;
    target.llMedicalPlan = null;
  }
}
