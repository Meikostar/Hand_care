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

public class OrderAdapter$ViewHolder_ViewBinding implements Unbinder {
  private OrderAdapter.ViewHolder target;

  @UiThread
  public OrderAdapter$ViewHolder_ViewBinding(OrderAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvDetail = Utils.findRequiredViewAsType(source, R.id.tv_detail, "field 'tvDetail'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvLess = Utils.findRequiredViewAsType(source, R.id.tv_less, "field 'tvLess'", ImageView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tvCount'", TextView.class);
    target.tvCounts = Utils.findRequiredViewAsType(source, R.id.tv_counts, "field 'tvCounts'", TextView.class);
    target.tvAdd = Utils.findRequiredViewAsType(source, R.id.tv_add, "field 'tvAdd'", ImageView.class);
    target.llEditor = Utils.findRequiredViewAsType(source, R.id.ll_editor, "field 'llEditor'", LinearLayout.class);
    target.tvRemark = Utils.findRequiredViewAsType(source, R.id.tv_remark, "field 'tvRemark'", TextView.class);
    target.llRemark = Utils.findRequiredViewAsType(source, R.id.ll_remark, "field 'llRemark'", LinearLayout.class);
    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.lines = Utils.findRequiredView(source, R.id.lines, "field 'lines'");
    target.liness = Utils.findRequiredView(source, R.id.liness, "field 'liness'");
    target.tv_orderno = Utils.findRequiredViewAsType(source, R.id.tv_orderno, "field 'tv_orderno'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.llTotal = Utils.findRequiredViewAsType(source, R.id.ll_total, "field 'llTotal'", LinearLayout.class);
    target.ll_order = Utils.findRequiredViewAsType(source, R.id.ll_order, "field 'll_order'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTime = null;
    target.ivImg = null;
    target.tvName = null;
    target.tvDetail = null;
    target.tvPrice = null;
    target.tvLess = null;
    target.tvCount = null;
    target.tvCounts = null;
    target.tvAdd = null;
    target.llEditor = null;
    target.tvRemark = null;
    target.llRemark = null;
    target.line = null;
    target.lines = null;
    target.liness = null;
    target.tv_orderno = null;
    target.tvMoney = null;
    target.llTotal = null;
    target.ll_order = null;
  }
}
