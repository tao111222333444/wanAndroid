package com.hugo.wanandroid;

import android.app.Application;
import android.content.Context;

import com.hugo.wanandroid.utils.ActivityControl;
import com.hugo.wanandroid.utils.SdkManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/4.
 * 版本：v1.0
 * 描述：自定义Application
 */
public class App extends Application{

    /**
     * Activity管理
     * */
    private ActivityControl mActivityControl;

    private static App mBaseApplication ;

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityControl = new ActivityControl();
        mBaseApplication = this;
        if(BuildConfig.DEBUG){
            //初始化内存泄漏检测工具
            SdkManager.initLeakCanary(this);
        }
    }

    /**
     * 获取Application
     * @return
     */
    public static App getAppContext(){
        return mBaseApplication;
    }
    /**
     * 获取activity管理类
     * */
    public ActivityControl getActivityControl() {
        return mActivityControl;
    }
    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        mActivityControl.appExit();
    }


    //这个是刷新控件初始化smartRefreshLayout  static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
