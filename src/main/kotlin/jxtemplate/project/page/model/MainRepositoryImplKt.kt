package jxtemplate.project.page.model

/**
 * Created by liuheng on 2021/6/3.
 * MainRepositoryImpl
 */

fun MainRepositoryImplKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.model

import com.jd.pingou.${bundle}.common.RequestState
import com.jd.pingou.${bundle}.util.RequestHelper
import com.jingdong.common.network.HttpGroupUtils
import com.jingdong.jdsdk.network.toolbox.HttpGroup

/**
 * Created by liuheng on 2021/6/1.
 * 网络接口具体实现
 */
class ${page.capitalize()}RepositoryImpl: I${page.capitalize()}Repository {

    override fun sync${page.capitalize()}Data(listener: HttpGroup.OnCommonListener, requestParam: RequestParam) {
        val requestState = RequestState()
        requestState.functionId = "function_id_mock" //TODO 根据业务修改
        requestState.putJSONParam("param", requestParam.param)

        val httpSetting = RequestHelper.requestColorHttpSection(requestState, listener)
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting)

    }
}
""".trimIndent()
