package com.hugo.wanandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 获取屏幕尺寸的工具
 * @author 29794
 */
public class ScreenUtils {
    private static final int MIN_API = 19;
    /**
     * 获取标题栏高度
     * */
    public static int getTitleHeight(Activity activity){
        TypedValue tv = new TypedValue();
        if(activity.getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
            Resources resources = activity.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            return TypedValue.complexToDimensionPixelSize(tv.data,displayMetrics);
        }
        return 0;
    }

    /**
     * 获取状态栏高度
     * */
    public static int getStatusBarHeight(Context context){
        int resourceId = context.getResources().getIdentifier("status_bar_height","dimen","android");
        return resourceId == 0 ? 0 : context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 获取屏幕宽度  Android系统API版本必须 大于17
     * */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager != null){
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.x;
        }
        return 0;
    }

    /**
     * 获取屏幕宽度  推荐使用这个获取
     */
    public static int getScreenWidth2(Context context){
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     *  返回包括虚拟键在内的总的屏幕高度
     *  Android系统API版本必须 大于17
     * */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getTotalScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取不包括虚拟按键在内的屏幕高度
     * */
    public static int getAvailableScreenHeight(Context context){
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics =resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获取不包括虚拟按键在内的屏幕高度
     */
    public static int getAvailableScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取虚拟按键的高度，不论虚拟按键是否显示都会返回其固定高度
     * **/
    public static int getNavigationBarHeight(Context context){
        int resourceId = context.getResources().getIdentifier("navigation_bar_height","dimen","android");
        if(resourceId != 0){
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 获取虚拟按键的高度
     * 会根据当前是否有显示虚拟按键来返回相应的值
     * 即如果隐藏了虚拟按键，则返回零
     *  Android系统API版本必须 大于17
     * */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getNavigationBarHeightIfShow(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int usableHeight = displayMetrics.heightPixels;
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int realHeight = displayMetrics.heightPixels;
        LogUtil.e("getNavigationBarHeightIfShow","\nrealHeight"+realHeight+
        "\nusableHeight"+usableHeight);
        return realHeight - usableHeight;
    }

    /** android 6.0设置字体颜色 */
    @RequiresApi(Build.VERSION_CODES.M)
    private static void darkModeForM(Window window, boolean dark) {
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (dark) {
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }
    /** 增加View的paddingTop,增加的值为状态栏高度 (智能判断，并设置高度)*/
    public static void setPaddingSmart(Context context, View view) {
        if (Build.VERSION.SDK_INT >= MIN_API) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}
