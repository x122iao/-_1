package com.tongshang.cloudphone.utils;

import android.content.Context;
import android.content.res.Resources;

import com.tongshang.cloudphone.MyApplication;

import java.lang.reflect.Method;


public class ScreenUtils {

    private static ScreenUtils mInstance;

    private ScreenUtils() {
    }

    public static ScreenUtils getInstance() {
        if (mInstance == null) {
            mInstance = new ScreenUtils();
        }
        return mInstance;
    }
    public static float getDensity() {
        // 获取应用的上下文
        Context context = MyApplication.Companion.getApplication();
        return context.getResources().getDisplayMetrics().density; // 访问 density
    }

    public int dip2px(float f) {
        return (int) (0.5D + (double) (f * getDensity()));
    }

    public int dip2px(int i) {
        return (int) (0.5D + (double) (getDensity() * (float) i));
    }

    public int getScreenDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int px2dip(Context context,float f) {
        float f1 = getDensity();
        return (int) (((double) f - 0.5D) / (double) f1);
    }

    public int px2dip(Context context,int i) {
        float f = getDensity();
        return (int) (((double) i - 0.5D) / (double) f);
    }

    public float getScaledDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public int px2sp(Context context,float pxValue) {
        final float fontScale = getScaledDensity(context);
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public int sp2px(Context context,float spValue) {
        final float fontScale = getScaledDensity(context);
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获得状态栏高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getNavigationBarHeight(Context context){
        int result = 0;
        int resourceId=context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
        }
        return result;
    }

    //获取是否存在NavigationBar
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }


}
