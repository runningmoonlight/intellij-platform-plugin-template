package jxtemplate.project.bundle.lib.res.values

/**
 * Created by liuheng on 2021/6/3.
 * string.xml
 */

fun string_Xml(
        bundle: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="${bundle}_app_name">bundle-${bundle}</string>
    <string name="item_recommend_title">可能你还想要</string>
</resources>
""".trimIndent()
