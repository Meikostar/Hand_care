// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.loadingView.LoadingPager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddFriendActivity_ViewBinding implements Unbinder {
  private AddFriendActivity target;

  @UiThread
  public AddFriendActivity_ViewBinding(AddFriendActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddFriendActivity_ViewBinding(AddFriendActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.mSuperRecyclerView = Utils.findRequiredViewAsType(source, R.id.super_recycle_view, "field 'mSuperRecyclerView'", SuperRecyclerView.class);
    target.search = Utils.findRequiredViewAsType(source, R.id.search, "field 'search'", TextView.class);
    target.etSearch = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'etSearch'", ClearEditText.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loadingView, "field 'loadingView'", LoadingPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddFriendActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.mSuperRecyclerView = null;
    target.search = null;
    target.etSearch = null;
    target.loadingView = null;
  }
}
