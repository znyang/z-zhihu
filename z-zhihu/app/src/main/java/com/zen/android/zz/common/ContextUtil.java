package com.zen.android.zz.common;

import android.content.Context;

/**
 * @author zen yang - 2016/12/11
 */
public class ContextUtil {

    private static Context sContext;

    public static Context get() {
        return sContext;
    }

    public static void setAppContext(Context context) {
        sContext = context.getApplicationContext();
    }
}
