package jxtemplate.project.page.model

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * IMainRepository
 */

fun IMainRepositoryKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.model

import com.jingdong.jdsdk.network.toolbox.HttpGroup

/**
 * Created by liuheng on 2021/6/1.
 * 定义网络接口
 */
interface I${StringUtil.lineToHump(page)}Repository {
    fun sync${StringUtil.lineToHump(page)}Data(listener: HttpGroup.OnCommonListener, requestParam: RequestParam)
}
""".trimIndent()
