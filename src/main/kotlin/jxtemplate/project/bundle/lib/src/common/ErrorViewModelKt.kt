package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 * ErrorViewModel
 */

fun ErrorViewModelKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

import com.jd.pingou.${bundle}.R
import com.jd.pingou.lib.adapter.core.IDataModel
import com.jd.pingou.lib.adapter.core.LayoutViewModel
import com.jd.pingou.lib.adapter.core.getView

import com.jd.pingou.widget.loading.PgCommonNetErrorView

/**
 * Created by liuheng on 2021/1/9.
 * 错误状态
 */
class ErrorViewModel(private val onRetryListener: OnRetryListener) : LayoutViewModel<IDataModel>(R.layout.view_state_error) {

    init {
        onCreateViewHolder {
            getView<PgCommonNetErrorView>(R.id.pg_net_error_view).setOnClickListener {
                onRetryListener.onRetryClick()
            }
            onBindViewHolder {

            }
        }
    }

}

interface OnRetryListener {
    fun onRetryClick()
}
""".trimIndent()
