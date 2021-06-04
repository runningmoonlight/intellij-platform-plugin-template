package jxtemplate.project.page.model

/**
 * Created by liuheng on 2021/6/3.
 * IMainRepository
 */

fun IMainRepositoryKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.model

import com.jingdong.jdsdk.network.toolbox.HttpGroup

/**
 * Created by liuheng on 2021/6/1.
 * 定义网络接口
 */
interface I${page.capitalize()}Repository {
    fun sync${page.capitalize()}Data(listener: HttpGroup.OnCommonListener, requestParam: RequestParam)
}
""".trimIndent()
