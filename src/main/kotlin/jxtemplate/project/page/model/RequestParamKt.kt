package jxtemplate.project.page.model

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * RequestParam
 */

fun RequestParamKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.model

/**
 * Created by liuheng on 2021/6/1.
 * 接口参数
 */

//TODO 根据业务修改
data class RequestParam(val param: String) {
}
""".trimIndent()