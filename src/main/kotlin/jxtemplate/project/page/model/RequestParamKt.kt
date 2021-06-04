package jxtemplate.project.page.model

/**
 * Created by liuheng on 2021/6/3.
 * RequestParam
 */

fun RequestParamKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.model

/**
 * Created by liuheng on 2021/6/1.
 * 接口参数
 */

//TODO 根据业务修改
data class RequestParam(val param: String) {
}
""".trimIndent()