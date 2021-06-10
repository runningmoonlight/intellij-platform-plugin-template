package jxtemplate.project.floor.string


import jxtemplate.util.StringUtil
import java.lang.StringBuilder

/**
 * Created by liuheng on 2021/6/3.
 * StringEntity
 */

fun StringEntityKt(
        applicationPackage: String?,
        packageName: String,
        page: String,
        floor: String,
        floorCap: String,
        buildEntity: Boolean
): String {
    val floorEntityTemplate = StringBuilder()
    if (buildEntity) {
        floorEntityTemplate.append(
"""
package ${packageName}.${StringUtil.removeLine(floor)}
""".trimIndent()
        )
    } else {
        floorEntityTemplate.append(
"""
package $packageName
""".trimIndent()
        )
    }
    floorEntityTemplate.append(
"""

/**
 * Created by liuheng on 2021/6/3.
 */
data class ${floorCap.capitalize()}Entity(var str: String?)
""".trimIndent()
    )

    return floorEntityTemplate.toString()
}


