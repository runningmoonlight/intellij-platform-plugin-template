package jxtemplate.project.page.model

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * AllData
 */

fun AllDataKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.model

import ${applicationPackage}.${StringUtil.removeLine(page)}.model.entity.${StringUtil.lineToHump(page)}DataEntity
import ${applicationPackage}.${StringUtil.removeLine(page)}.ui.floor.TextModel
import ${applicationPackage}.${StringUtil.removeLine(page)}.ui.floor.TextViewModel
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
    fun parseData(entity: ${StringUtil.lineToHump(page)}DataEntity) {
        entity.list?.let {
            for (text in it)
                mFloorList.add(TextViewModel().apply {
                    model = TextModel(text)
                })
        }
    }
}
""".trimIndent()
