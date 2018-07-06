package com.hugo.wanandroid.utils;

import android.content.Context;

/**
 * 作者：hugo
 * 时间：2018/6/7.
 * 版本：v1.0
 * 描述：dp、sp  和  px互换
 */
public class DisplayUtils {

    /**
     * dp值转换为px值
     * */
    public static int dp2px(Context context, float dpValue){
        return (int)(dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     *  px值转换为dp值
     * */
    public static int px2dp(Context context, float pxValue){
        return (int) (pxValue / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     *  px值转换为sp值
     * */
    public static int px2px(Context context, float pxValue){
        return (int) (pxValue / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     *  sp值转换为px值
     * */
    public static int sp2px(Context context, float spValue){
        return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}
