package com.huazi.gtads_vivo.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.vivo.mobilead.util.VOpenLog;

import java.lang.reflect.Method;

/**
 * description ： TODO:类的作用
 * author : 72060925
 * date : 2019/09/21
 */
public class Utils {

    public static final String TAG = "Utils";
    private static DisplayMetrics getDisplayMetrics(Context context) {
        if (context == null) {
            return null;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return null;
        }
        return resources.getDisplayMetrics();
    }
    /**
     * dp转px
     *
     * @param context
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        if (displayMetrics == null) {
            return (int) dpVal;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, displayMetrics);
    }

    /**
     * 收起软件盘
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() == null || activity.getCurrentFocus().getWindowToken() == null) {
            return;
        }

        try {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            VOpenLog.w(TAG, "" + e.getMessage());
        }
    }

    /**
     * 返回屏幕宽度dp值
     * @param context
     * @return
     */
    public static int getScreenDp(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        if (displayMetrics == null) {
            return 0;
        }
        return Math.round(displayMetrics.widthPixels / displayMetrics.density);
    }

    /**
     *  用于判断模式当前activity 是否处于小窗模式
     *  不同的Android版本，判断方式不同。见文档： http://km.vivo.xyz/pages/viewpage.action?pageId=153972478&token=NKO2AVM.1brD_N_InrgmAhgTY9r9RcP_Qb.wf56M4hCt50htQJVXmt6fRHLQGWfpYkI7wsFXUkI*
     *  具体疑问可以咨询王冬冬
     * @param activity
     * @return
     */
    public static boolean isWindowModeFreeForm(Activity activity) {
        boolean ret = false;
        try {
            if (Build.VERSION.SDK_INT >= 30) {
                Window phoneWindow = activity.getWindow();
                Method[] methodsDeclared = null;
                Class<?> className = null;
                Method targetMethod = null;
                try {
                    className = phoneWindow.getClass();
                    if (null != className && className.getSuperclass() != null) {
                        methodsDeclared = className.getSuperclass().getDeclaredMethods();
                        for (Method method : methodsDeclared) {
                            if (method != null && "getWindowControllerCallback".equals(method.getName())) {
                                targetMethod = method;
                            }
                        }
                    }

                    if (null != targetMethod) {
                        Object a = targetMethod.invoke(phoneWindow);
                        if (a != null) {
                            className = a.getClass();
                            methodsDeclared = className.getDeclaredMethods();
                            for (Method method : methodsDeclared) {
                                if (method != null && "isInVivoFreeformMode".equals(method.getName())) {
                                    targetMethod = method;
                                }
                            }
                            ret = (boolean) targetMethod.invoke(a);
                        }
                    } else {
                        VOpenLog.e(TAG, "<isWindowModeFreeForm> registerActivityObserver not implement in IActivityManager");
                    }
                } catch (Exception e) {
                    VOpenLog.e(TAG, "<isWindowModeFreeForm> registerActivityObserver-e = " + e);
                }
            }else if (Build.VERSION.SDK_INT >= 28) {
                Object isInVivoFreeformModeObj = invokeMethodCustom(activity, "android.app.Activity", "isInVivoFreeformMode");
                if (isInVivoFreeformModeObj != null) {
                    ret = (boolean) isInVivoFreeformModeObj;
                }
            } else {
                int stackid = -1;
                Object windowStackIdObj = invokeMethodCustom(activity, "android.app.Activity", "getWindowStackId");
                if (windowStackIdObj != null) {
                    stackid = (int) windowStackIdObj;
                    if (stackid == 2) {
                        ret = true;
                    }
                }
            }
        } catch (Throwable throwable) {
        }
        VOpenLog.d(TAG, "isWindowModeFreeForm，ret = " + ret);
        return ret;
    }

    private static Object invokeMethodCustom(Object owner, String className, String methodName) {
        Object object = null;
        try {
            Class<?> ownerClass = Class.forName(className);
            Method method = ownerClass.getMethod(methodName);
            if (method != null) {
                method.setAccessible(true);
                object = method.invoke(owner);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return object;
    }
}
