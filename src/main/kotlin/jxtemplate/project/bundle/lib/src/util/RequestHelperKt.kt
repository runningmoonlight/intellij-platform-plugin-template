package jxtemplate.project.bundle.lib.src.util

/**
 * Created by liuheng on 2021/6/3.
 * RequestHelper
 */

fun RequestHelperKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.util

import com.jd.pingou.${bundle}.common.RequestState
import com.jd.pingou.utils.NetworkHostUtil
import com.jingdong.jdsdk.network.config.CacheTimeConfig
import com.jingdong.jdsdk.network.toolbox.HttpGroup
import com.jingdong.jdsdk.network.toolbox.HttpSetting

object RequestHelper {
    fun requestColorHttpSection(state: RequestState, listener: HttpGroup.OnCommonListener): HttpSetting {
        val httpSetting = HttpSetting()
        httpSetting.functionId = state.functionId
        httpSetting.host = NetworkHostUtil.getNetworkHost()
        httpSetting.effect = HttpSetting.EFFECT_DEFAULT
        httpSetting.setJsonParams(state.jSONParam)
        httpSetting.cacheMode = HttpSetting.CACHE_MODE_ONLY_NET
        httpSetting.isLocalFileCache = true
        httpSetting.localFileCacheTime = CacheTimeConfig.DAY * 30
        httpSetting.md5 = state.jSONParam.toString() + RequestState.versionCode
        httpSetting.callTimeout = 5000
        httpSetting.isNeedLoal = false
        httpSetting.isPost = true
        httpSetting.isUseFastJsonParser = true

        httpSetting.setListener(listener)
        return httpSetting
    }
}

""".trimIndent()
