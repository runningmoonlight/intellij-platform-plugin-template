package jxtemplate.project.page.model.entity

/**
 * Created by liuheng on 2021/6/3.
 * MainDataEntity
 */

fun MainDataEntityKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.model.entity

/**
 * Created by liuheng on 2021/6/1.
 * 页面接口返回的实体类
 */
//TODO 根据业务修改补充
data class ${page.capitalize()}DataEntity(var code: Int?, var msg: String?, var list: List<String>?)
""".trimIndent()
