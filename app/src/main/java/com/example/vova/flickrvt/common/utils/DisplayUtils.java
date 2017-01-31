package com.example.vova.flickrvt.common.utils;

import android.content.Context;

/**
 * Created by vova on 31.01.17.
 */

public class DisplayUtils {

    private int dpi = 0;

    public DisplayUtils(Context context) {
        dpi = context.getResources().getDisplayMetrics().densityDpi;
    }

    public float dpToPx(int dp) {
        if (dpi == 0) {
            return 0;
        }
        return (float) (dp * (dpi / 160.0));
    }
}
