package jxtemplate.project.bundle.application

/**
 * Created by liuheng on 2021/6/3.
 * AndroidManifest.xml
 */

fun application_AndroidManifest_Xml(
        bundle: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.jd.pingou.${bundle}app"
    android:versionCode="1"
    android:versionName="1.0.0">

</manifest>
""".trimIndent()
