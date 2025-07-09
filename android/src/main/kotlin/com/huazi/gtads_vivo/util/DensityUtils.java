package com.huazi.gtads_vivo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DensityUtils {
    /**
     * dp转px
     *
     * @param context
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        if (metrics == null) {
            return (int)dpVal;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, metrics);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        if (context == null) {
            return null;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return null;
        }
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics;
    }

}
