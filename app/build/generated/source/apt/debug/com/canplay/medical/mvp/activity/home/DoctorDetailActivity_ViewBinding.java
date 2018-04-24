// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

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

public class DoctorDetailActivity_ViewBinding implements Unbinder {
  private DoctorDetailActivity target;

  @UiThread
  public DoctorDetailActivity_ViewBinding(DoctorDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DoctorDetailActivity_ViewBinding(DoctorDetailActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvSex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tvSex'", TextView.class);
    target.tvJob = Utils.findRequiredViewAsType(source, R.id.tv_job, "field 'tvJob'", TextView.class);
    target.tvDepartment = Utils.findRequiredViewAsType(source, R.id.tv_department, "field 'tvDepartment'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvHospital = Utils.findRequiredViewAsType(source, R.id.tv_hospital, "field 'tvHospital'", TextView.class);
    target.llBg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llBg'", LinearLayout.class);
    target.tvDetail = Utils.findRequiredViewAsType(source, R.id.tv_detail, "field 'tvDetail'", TextView.class);
    target.ll_back = Utils.findRequiredViewAsType(source, R.id.ll_back, "field 'll_back'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DoctorDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.ivImg = null;
    target.tvName = null;
    target.tvSex = null;
    target.tvJob = null;
    target.tvDepartment = null;
    target.tvPhone = null;
    target.tvHospital = null;
    target.llBg = null;
    target.tvDetail = null;
    target.ll_back = null;
  }
}
