package io.cordova.qianshou.mvp.activity.mine;

import android.widget.ListView;

import io.cordova.qianshou.R;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.mvp.adapter.CollectionAdapter;
import io.cordova.qianshou.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.cordova.qianshou.base.BaseActivity;
import io.cordova.qianshou.view.NavigationBar;

/**
 * 我的收藏
 */
public class CollectionActivity extends BaseActivity {


    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.listview)
    ListView listview;
    private CollectionAdapter adapter;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter=new CollectionAdapter(this,null,listview);
        listview.setAdapter(adapter);

    }

    @Override
    public void bindEvents() {
        adapter.setListener(new CollectionAdapter.selectItemListener() {
            @Override
            public void delete(int id, int type, int poistion) {

            }
        });

    }


    @Override
    public void initData() {

    }






}
