package jxtemplate.project.floor

/**
 * Created by liuheng on 2021/6/3.
 * EmptyViewModel
 */

fun EmptyViewModelKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${page}.ui.floor

import ${applicationPackage}.R
import com.jd.pingou.lib.adapter.core.*

/**
 * Created by liuheng on 2021/6/2.
 */
//TODO 根据业务修改
class EmptyViewModel: LayoutViewModel<IDataModel>(R.layout.view_state_empty) {
    init {
        onCreateViewHolder {

            onBindViewHolder {


            }

            onUnBindViewHolder {

            }
        }
    }
}
""".trimIndent()
