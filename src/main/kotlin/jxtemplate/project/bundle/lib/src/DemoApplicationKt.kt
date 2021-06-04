package jxtemplate.project.bundle.lib.src

/**
 * Created by liuheng on 2021/6/3.
 * DemoApplication
 */

fun DemoApplicationKt(
        bundle: String
) = """
package com.jd.pingou.${bundle};

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class DemoApplication extends Application {

    private static final String TAG = "DemoApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + DemoClass.class.getSimpleName());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.i(TAG, "attachBaseContext: ");
//        MultiDex.install(base);
    }
}

""".trimIndent()