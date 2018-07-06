package com.hugo.wanandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hugo.wanandroid.base.BaseActivity;
import com.hugo.wanandroid.ui.WebActivity;
import com.hugo.wanandroid.utils.ToastUtil;

import butterknife.BindView;

/**
 * @author 主页面
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    /**
     * 抽屉
     */
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initView() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_item_mine_collect:
                //我的收藏
                WebActivity.show(this,"https://www.baidu.com");
                break;
            case R.id.nav_item_about_we:
                //关于我们

                break;
            case R.id.nav_item_exit:
                //退出

                break;
                default:
        }
        return false;
    }


    private long exitTime = 0;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 1000) {
            ToastUtil.showToast(this,R.string.exit_program);
            exitTime = System.currentTimeMillis();

        } else {
            App.getAppContext().getActivityControl().appExit();
        }
    }
}
