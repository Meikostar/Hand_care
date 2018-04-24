// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineCodeActivity_ViewBinding implements Unbinder {
  private MineCodeActivity target;

  @UiThread
  public MineCodeActivity_ViewBinding(MineCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineCodeActivity_ViewBinding(MineCodeActivity target, View source) {
    this.target = target;

    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.ivCode = Utils.findRequiredViewAsType(source, R.id.iv_code, "field 'ivCode'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.navigationBar = null;
    target.img = null;
    target.tvName = null;
    target.ivCode = null;
  }
}
