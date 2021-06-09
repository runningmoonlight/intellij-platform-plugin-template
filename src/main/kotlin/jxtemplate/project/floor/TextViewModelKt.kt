package jxtemplate.project.floor

import jxtemplate.util.StringUtil
import java.lang.StringBuilder

/**
 * Created by liuheng on 2021/6/3.
 * TextViewModel
 */

fun TextViewModelKt(
        applicationPackage: String?,
        packageName: String,
        page: String,
        floor: String,
        buildEntity: Boolean
): String {
    val floorCap = StringUtil.lineToHump(floor)
    val floorViewModelTemplate = StringBuilder()
    if (buildEntity) {
        floorViewModelTemplate.append(
"""
package ${packageName}.${floor}
""".trimIndent()
        )
    } else {
        floorViewModelTemplate.append(
"""
package $packageName
""".trimIndent()
        )
    }
    floorViewModelTemplate.append(
"""

import android.widget.TextView
import ${applicationPackage}.R
import ${applicationPackage}.${page}.ui.${StringUtil.lineToHump(page)}Adapter
import com.jd.pingou.lib.adapter.core.*

/**
 * Created by liuheng on 2021/6/2.
 * 楼层ViewModel示例
 */

class ${floorCap}Model(val text: String) : IDataModel

class ${floorCap}ViewModel: LayoutViewModel<${floorCap}Model>(R.layout.floor_holder_${floor}) {
    //在这里增加成员变量，一般不需要

    //在init中设置viewHolder的闭包方法
    init {
        onCreateViewHolder {

            val textView = getView<TextView>(R.id.tv_text)

            onBindViewHolder {
                val model = getModel<${floorCap}Model>()  //拿到DataModel
                val data = model?.text  //拿到data
                val adapter = getAdapter<${StringUtil.lineToHump(page)}Adapter>() //拿到adapter
                val recyclerView = getRecyclerView()
                textView.text = model?.text
            }

            onUnBindViewHolder {

            }
        }
    }
}
""".trimIndent()
    )
    return floorViewModelTemplate.toString()
}


