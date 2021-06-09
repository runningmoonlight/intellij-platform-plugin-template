package jxtemplate.project.page.model

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * MainRepositoryImpl
 */

fun MainRepositoryImplKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.model

import ${applicationPackage}.common.RequestState
import ${applicationPackage}.util.RequestHelper
import com.jingdong.common.network.HttpGroupUtils
import com.jingdong.jdsdk.network.toolbox.HttpGroup

/**
 * Created by liuheng on 2021/6/1.
 * 网络接口具体实现
 */
class ${StringUtil.lineToHump(page).capitalize()}RepositoryImpl: I${StringUtil.lineToHump(page).capitalize()}Repository {

    override fun sync${StringUtil.lineToHump(page).capitalize()}Data(listener: HttpGroup.OnCommonListener, requestParam: RequestParam) {
        val requestState = RequestState()
        requestState.functionId = "function_id_mock" //TODO 根据业务修改
        requestState.putJSONParam("param", requestParam.param)

        val httpSetting = RequestHelper.requestColorHttpSection(requestState, listener)
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting)

    }
}
""".trimIndent()
