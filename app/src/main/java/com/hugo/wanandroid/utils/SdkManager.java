package com.hugo.wanandroid.utils;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/5.
 * 版本：v1.0
 * 描述：用于初始化  测试环境用的sdk管理类
 */
public class SdkManager {

    public static void initLeakCanary(Application app) {
        if (LeakCanary.isInAnalyzerProcess(app)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(app);
    }
}
