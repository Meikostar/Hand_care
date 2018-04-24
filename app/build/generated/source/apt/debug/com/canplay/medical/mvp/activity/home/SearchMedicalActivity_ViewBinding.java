// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchMedicalActivity_ViewBinding implements Unbinder {
  private SearchMedicalActivity target;

  @UiThread
  public SearchMedicalActivity_ViewBinding(SearchMedicalActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchMedicalActivity_ViewBinding(SearchMedicalActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.tvCancel = Utils.findRequiredViewAsType(source, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
    target.topviewLeftLayout = Utils.findRequiredViewAsType(source, R.id.topview_left_layout, "field 'topviewLeftLayout'", LinearLayout.class);
    target.tvSure = Utils.findRequiredViewAsType(source, R.id.tv_sure, "field 'tvSure'", TextView.class);
    target.etSearch = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'etSearch'", ClearEditText.class);
    target.tvSearch = Utils.findRequiredViewAsType(source, R.id.tv_search, "field 'tvSearch'", TextView.class);
    target.listview = Utils.findRequiredViewAsType(source, R.id.listview_all_city, "field 'listview'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchMedicalActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.tvCancel = null;
    target.topviewLeftLayout = null;
    target.tvSure = null;
    target.etSearch = null;
    target.tvSearch = null;
    target.listview = null;
  }
}
