package com.hugo.wanandroid.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * 提示工具
 * */
public class ToastUtil {
    private static Toast mToast;

    private static Handler mainHandler;

    private static Application application;

    /**
     * 在自定义Application里   onCreate()里 初始化
     *  mainHandler = new Handler(getMainLooper());
     * 设置Application下的Handler
     * @param mainHander
     */
    public static void initToast(Handler mainHander, Application application){
        ToastUtil.mainHandler = mainHander;
        ToastUtil.application = application;
    }

    /**
     * 在自定义Application onTerminate()调用
     * 在App退出时 清除mainHander
     */
    public static void clear(){
        ToastUtil.mainHandler = null;
        ToastUtil.application = null;
    }

    public static void showToast(final String msg){
        if(mainHandler == null ||application == null){
            return;
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(application.getApplicationContext(), msg, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    public static void showToastLong(final String msg){
        if(mainHandler == null ||application == null){
            return;
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mToast != null){
                    mToast.cancel();
                }
                mToast = Toast.makeText(application.getApplicationContext(),msg, Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }


    public static void showToast(@StringRes final int resId){
        if(mainHandler == null ||application == null){
            return;
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mToast != null){
                    mToast.cancel();
                }
                mToast = Toast.makeText(application.getApplicationContext(),resId, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    public static void showToastLong(@StringRes final int resId){
        if(mainHandler == null ||application == null){
            return;
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mToast != null){
                    mToast.cancel();
                }
                mToast = Toast.makeText(application.getApplicationContext(),resId, Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }

    public static void showToast(Context context, String msg){
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(context,msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToastLong(Context context, String msg){
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(context,msg, Toast.LENGTH_LONG);
        mToast.show();
    }


    public static void showToast(Context context, @StringRes int resId){
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(context,resId, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToastLong(Context context, @StringRes int resId){
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(context,resId, Toast.LENGTH_LONG);
        mToast.show();
    }
}
