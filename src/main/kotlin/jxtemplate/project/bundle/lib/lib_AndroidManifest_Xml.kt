package jxtemplate.project.bundle.lib

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * AndroidManifest.xml
 */

fun lib_AndroidManifest_Xml(
        bundle: String,
        page: String
): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(
"""
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.jd.pingou.${bundle}"
    android:versionCode="1"
    android:versionName="1.0.0">

    <application
        android:name=".DemoApplication"
        android:label="@string/${bundle}_app_name"
        android:theme="@android:style/Theme.Holo.Light.NoActionBar">


"""
    )

    if (page.isNotEmpty()) {
        stringBuilder.append(
                """

        <activity android:name="${StringUtil.removeLine(page)}.ui.${StringUtil.lineToHump(page)}Activity"></activity>
""".trimIndent())
    }

    stringBuilder.append(
"""

        <!-- TODO: Please copy declarations of all activities to the Manifest.xml in the main project. -->
        <activity
            android:name=".DemoMainActivity"
            android:launchMode="singleTask"></activity>
    </application>

    <supports-screens
        android:anyDensity="true"
        android:largeScreens="true"
        android:normalScreens="true"
        android:smallScreens="true"
        android:xlargeScreens="true" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

</manifest>                
"""
    )

    return stringBuilder.toString()
}

