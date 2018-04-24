// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baidu.mapapi.map.MapView;
import com.canplay.medical.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocationBdActivity_ViewBinding implements Unbinder {
  private LocationBdActivity target;

  @UiThread
  public LocationBdActivity_ViewBinding(LocationBdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LocationBdActivity_ViewBinding(LocationBdActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", MapView.class);
    target.topViewBack = Utils.findRequiredViewAsType(source, R.id.top_view_back, "field 'topViewBack'", ImageView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.llGo = Utils.findRequiredViewAsType(source, R.id.ll_go, "field 'llGo'", LinearLayout.class);
    target.llDetail = Utils.findRequiredViewAsType(source, R.id.ll_detail, "field 'llDetail'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LocationBdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.mapView = null;
    target.topViewBack = null;
    target.img = null;
    target.tvName = null;
    target.tvTime = null;
    target.tvAddress = null;
    target.llGo = null;
    target.llDetail = null;
  }
}
