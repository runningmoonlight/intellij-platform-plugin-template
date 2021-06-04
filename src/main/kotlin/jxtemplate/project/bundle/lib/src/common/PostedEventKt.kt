package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 * PostedEvent
 */

fun PostedEventKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

/**
 * Created by liuheng on 2021/6/1.
 * 事件定义
 */
class PostedEvent {

    companion object {
        //隐藏loading
        const val EVENT_HIDE_LOADING = "event_hide_loading"

        //显示loading
        const val EVENT_SHOW_LOADING = "event_show_loading"

        //显示错误状态
        const val EVENT_ERROR_STATE = "event_error_state"

        //显示空数据状态
        const val EVENT_EMPTY_STATE = "event_empty_state"
    }

    /**
     * 事件标识
     */
    var eventType: String? = null

    /**
     * 事件内容
     */
    var message: Any? = null

    constructor(type: String?) {
        eventType = type
    }

    constructor(type: String?, msg: Any?) {
        eventType = type
        message = msg
    }
}
""".trimIndent()
