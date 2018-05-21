// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.banner.BannerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.ivScan = Utils.findRequiredViewAsType(source, R.id.iv_scan, "field 'ivScan'", ImageView.class);
    target.ivAlarm = Utils.findRequiredViewAsType(source, R.id.iv_alarm, "field 'ivAlarm'", ImageView.class);
    target.navigationbarTitle = Utils.findRequiredViewAsType(source, R.id.navigationbar_title, "field 'navigationbarTitle'", TextView.class);
    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.bannerView = Utils.findRequiredViewAsType(source, R.id.bannerView, "field 'bannerView'", BannerView.class);
    target.llHealth = Utils.findRequiredViewAsType(source, R.id.ll_health, "field 'llHealth'", LinearLayout.class);
    target.llCare = Utils.findRequiredViewAsType(source, R.id.ll_care, "field 'llCare'", LinearLayout.class);
    target.llEquipment = Utils.findRequiredViewAsType(source, R.id.ll_equipment, "field 'llEquipment'", LinearLayout.class);
    target.llShop = Utils.findRequiredViewAsType(source, R.id.ll_shop, "field 'llShop'", LinearLayout.class);
    target.tvHour = Utils.findRequiredViewAsType(source, R.id.tv_hour, "field 'tvHour'", TextView.class);
    target.tvMinter = Utils.findRequiredViewAsType(source, R.id.tv_minter, "field 'tvMinter'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.llBg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'llBg'", LinearLayout.class);
    target.tvHour1 = Utils.findRequiredViewAsType(source, R.id.tv_hour1, "field 'tvHour1'", TextView.class);
    target.tvMinter1 = Utils.findRequiredViewAsType(source, R.id.tv_minter1, "field 'tvMinter1'", TextView.class);
    target.tvState1 = Utils.findRequiredViewAsType(source, R.id.tv_state1, "field 'tvState1'", TextView.class);
    target.llBg1 = Utils.findRequiredViewAsType(source, R.id.ll_bg1, "field 'llBg1'", LinearLayout.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tvCount'", TextView.class);
    target.llM1 = Utils.findRequiredViewAsType(source, R.id.ll_m1, "field 'llM1'", LinearLayout.class);
    target.llM2 = Utils.findRequiredViewAsType(source, R.id.ll_m2, "field 'llM2'", LinearLayout.class);
    target.llM3 = Utils.findRequiredViewAsType(source, R.id.ll_m3, "field 'llM3'", LinearLayout.class);
    target.llM4 = Utils.findRequiredViewAsType(source, R.id.ll_m4, "field 'llM4'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivScan = null;
    target.ivAlarm = null;
    target.navigationbarTitle = null;
    target.line = null;
    target.bannerView = null;
    target.llHealth = null;
    target.llCare = null;
    target.llEquipment = null;
    target.llShop = null;
    target.tvHour = null;
    target.tvMinter = null;
    target.tvState = null;
    target.llBg = null;
    target.tvHour1 = null;
    target.tvMinter1 = null;
    target.tvState1 = null;
    target.llBg1 = null;
    target.tvCount = null;
    target.llM1 = null;
    target.llM2 = null;
    target.llM3 = null;
    target.llM4 = null;
  }
}
