package com.hugo.wanandroid;

import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.hugo.wanandroid.aidl.IMyAidlInterface;
import com.hugo.wanandroid.base.BaseActivity;
import com.hugo.wanandroid.ui.WebActivity;
import com.hugo.wanandroid.utils.ToastUtil;

import butterknife.BindView;

/**
 * @author 主页面
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
         BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    /**
     * 抽屉
     */
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    /**
     * 底部选项栏
     */
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    /**
     * fragment 容器
     */
    @BindView(R.id.container)
    FrameLayout container;


    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

    };


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
        bottomNavigation.setOnNavigationItemSelectedListener(this);
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
                ToastUtil.showToast("关于我们");
                break;
            case R.id.nav_item_exit:
                //退出
                ToastUtil.showToast("退出");
                break;
            case R.id.tab_home:
                //首页
                ToastUtil.showToast("首页");
                break;
            case R.id.tab_goods:
                //知识体系
                ToastUtil.showToast("知识体系");
                break;
            case R.id.tab_cart:
                //导航
                ToastUtil.showToast("导航");
                break;
            case R.id.tab_self:
                //项目
                ToastUtil.showToast("项目");
                break;
                default:
        }
        return true;
    }


    private long exitTime = 0;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 1000) {
            ToastUtil.showToast(this,R.string.exit_program);
            exitTime = System.currentTimeMillis();

        } else {
            App.getInstance().getActivityControl().appExit();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showError(String msg) {

    }
}
