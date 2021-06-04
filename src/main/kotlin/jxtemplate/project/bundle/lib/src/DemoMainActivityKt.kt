package jxtemplate.project.bundle.lib.src

/**
 * Created by liuheng on 2021/6/3.
 * DemoMainActivity
 */

fun DemoMainActivityKt(
        bundle: String
) = """
package com.jd.pingou.${bundle};

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class DemoMainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Test code begins.
        Log.d("MMM", "onCreate");
        Toast.makeText(DemoMainActivity.this, "onCreate!", Toast.LENGTH_LONG).show();

        // TODO: Test code ends.
    }
}

""".trimIndent()