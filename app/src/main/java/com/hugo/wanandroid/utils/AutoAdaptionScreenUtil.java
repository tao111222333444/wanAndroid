package com.hugo.wanandroid.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * 自动适配屏幕尺寸
 * @author 29794
 */
public class AutoAdaptionScreenUtil {
    /**设置设计图的尺寸   单位是dp*/
    private final static float DesignWidthSize = 360;
    private final static float DesignHeightSize = 640;

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;


    /**以宽维度来适配*/
    public static void setWidthCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0){
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale  > 0){
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });

            final float targetDensity = appDisplayMetrics.widthPixels/DesignWidthSize;
            final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity/sNoncompatDensity);
            final int targetDensityDpi = (int) (160 * targetDensity);

            appDisplayMetrics.density = targetDensity;
            appDisplayMetrics.scaledDensity = targetScaledDensity;
            appDisplayMetrics.densityDpi = targetDensityDpi;

            LogUtil.d("setWidthCustomDensity","\ntargetDensity:"+targetDensity+
                    "\ntargetScaledDensity:"+targetScaledDensity+
                    "\ntargetDensityDpi:"+targetDensityDpi);

            final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
            activityDisplayMetrics.density = targetDensity;
            activityDisplayMetrics.scaledDensity = targetScaledDensity;
            activityDisplayMetrics.densityDpi = targetDensityDpi;
        }
    }

    /**以高维度来适配*/
    public static void setHeightCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0){
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale  > 0){
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });

            final float targetDensity = appDisplayMetrics.heightPixels/DesignHeightSize;
            final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity/sNoncompatDensity);
            final int targetDensityDpi = (int) (160 * targetDensity);

            LogUtil.d("setHeightCustomDensity","\ntargetDensity:"+targetDensity+
                    "\ntargetScaledDensity:"+targetScaledDensity+
            "\ntargetDensityDpi:"+targetDensityDpi);

            appDisplayMetrics.density = targetDensity;
            appDisplayMetrics.scaledDensity = targetScaledDensity;
            appDisplayMetrics.densityDpi = targetDensityDpi;

            final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
            activityDisplayMetrics.density = targetDensity;
            activityDisplayMetrics.scaledDensity = targetScaledDensity;
            activityDisplayMetrics.densityDpi = targetDensityDpi;
        }
    }

}
