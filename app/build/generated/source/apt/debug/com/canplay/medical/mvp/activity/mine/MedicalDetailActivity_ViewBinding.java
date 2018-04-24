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
    target.ivPhone = Utils.findRequiredViewAsType(source, R.id.iv_phone, "field 'ivPhone'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.tv_distance, "field 'tvDistance'", TextView.class);
    target.llCollection = Utils.findRequiredViewAsType(source, R.id.ll_collection, "field 'llCollection'", LinearLayout.class);
    target.llCenter = Utils.findRequiredViewAsType(source, R.id.ll_center, "field 'llCenter'", LinearLayout.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.llPhone = Utils.findRequiredViewAsType(source, R.id.ll_phone, "field 'llPhone'", LinearLayout.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MedicalDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.ivPhone = null;
    target.tvName = null;
    target.tvDistance = null;
    target.llCollection = null;
    target.llCenter = null;
    target.tvTime = null;
    target.tvPhone = null;
    target.llPhone = null;
    target.tvAddress = null;
    target.ivImg = null;
  }
}
