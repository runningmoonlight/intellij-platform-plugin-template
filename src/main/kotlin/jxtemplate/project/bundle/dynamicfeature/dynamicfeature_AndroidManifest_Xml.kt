package jxtemplate.project.bundle.dynamicfeature

/**
 * Created by liuheng on 2021/6/3.
 * AndroidManifest.xml
 */

fun dynamicfeature_AndroidManifest_Xml(
        bundle: String
) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:dist="http://schemas.android.com/apk/distribution"
    package="com.jd.pingou.${bundle}.feature">

    <application>
       <!--声明组件-->
    </application>

</manifest>
""".trimIndent()
