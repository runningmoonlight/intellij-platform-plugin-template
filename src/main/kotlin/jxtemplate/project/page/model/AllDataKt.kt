package jxtemplate.project.page.model

/**
 * Created by liuheng on 2021/6/3.
 * AllData
 */

fun AllDataKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.model

import com.jd.pingou.${bundle}.${page}.model.entity.${page.capitalize()}DataEntity
import com.jd.pingou.${bundle}.${page}.ui.floor.TextModel
import com.jd.pingou.${bundle}.${page}.ui.floor.TextViewModel
import com.jd.pingou.lib.adapter.core.ViewModelType

/**
 * Created by liuheng on 2021/6/1.
 * 接口数据整理
 */
class AllData {

    var mRecommendParams: String = ""
    var mFloorList = ArrayList<ViewModelType>()
    var isEmpty: Boolean = false

    /**
     * @param entity 接口实体数据
     * 解析接口数据
     */
    fun parseData(entity: ${page.capitalize()}DataEntity) {
        entity.list?.let {
            for (text in it)
                mFloorList.add(TextViewModel().apply {
                    model = TextModel(text)
                })
        }
    }
}
""".trimIndent()
