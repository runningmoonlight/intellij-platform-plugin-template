package jxtemplate.project.page.ui

import jxtemplate.util.StringUtil


/**
 * Created by liuheng on 2021/6/9.
 */
fun activity_AndroidManifest_Xml(
        page: String
) = """
<activity android:name=".${StringUtil.removeLine(page)}.ui.${StringUtil.lineToHump(page)}Activity"></activity>
""".trimIndent()