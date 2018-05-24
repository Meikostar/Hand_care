// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.loadingView.LoadingPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RemindMedicatFragment_ViewBinding implements Unbinder {
  private RemindMedicatFragment target;

  @UiThread
  public RemindMedicatFragment_ViewBinding(RemindMedicatFragment target, View source) {
    this.target = target;

    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", ListView.class);
    target.loadingView = Utils.findRequiredViewAsType(source, R.id.loadingView, "field 'loadingView'", LoadingPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RemindMedicatFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlMenu = null;
    target.loadingView = null;
  }
}
