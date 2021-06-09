package jxtemplate.project.page.model.entity

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * MainDataEntity
 */

fun MainDataEntityKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.model.entity

/**
 * Created by liuheng on 2021/6/1.
 * 页面接口返回的实体类
 */
//TODO 根据业务修改补充
data class ${StringUtil.lineToHump(page)}DataEntity(var code: Int?, var msg: String?, var list: List<String>?)
""".trimIndent()
