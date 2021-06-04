package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 *
 * BaseViewModel
 */

fun BaseViewModelKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by liuheng on 2021/6/1.
 * ViewModel 封装loading处理
 */
open class BaseViewModel: ViewModel() {
    val loadingEventLiveData = MutableLiveData<PostedEvent>() //发送状态和其他事件
}
""".trimIndent()
