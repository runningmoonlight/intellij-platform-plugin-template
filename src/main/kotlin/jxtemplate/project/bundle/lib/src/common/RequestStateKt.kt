package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 * RequestState
 */

fun RequestStateKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

import com.jd.framework.json.JDJSONObject

/**
 * Created by liuheng on 2021/6/1.
 * 封装请求参数
 */
class RequestState {
    var functionId: String? = null
    var jSONParam: JDJSONObject = JDJSONObject()

    fun putJSONParam(key: String, value: Any) {
        jSONParam[key] = value
    }

    companion object {
        @JvmField
        var versionCode: Int = 0
    }
}
""".trimIndent()