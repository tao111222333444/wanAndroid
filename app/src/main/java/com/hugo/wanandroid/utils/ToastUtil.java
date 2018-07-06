package com.hugo.wanandroid.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 提示工具
 * */
public class ToastUtil {
    private static Toast mToast;

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


    public static void showToast(Context context, int resId){
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(context,resId, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToastLong(Context context, int resId){
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(context,resId, Toast.LENGTH_LONG);
        mToast.show();
    }
}
