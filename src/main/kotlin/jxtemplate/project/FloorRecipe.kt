package jxtemplate.project


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import jxtemplate.project.floor.TextViewModelKt
import jxtemplate.project.floor.floor_holder_text_Xml
import jxtemplate.project.floor.string.StringEntityKt
import jxtemplate.util.Constants
import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/4.
 * 楼层模板
 */

fun RecipeExecutor.floorRecipe(
        moduleTemplateData: ModuleTemplateData,
        floor: String,
        buildEntity: Boolean
) {
    val (projectData, srcOut, resOut) = moduleTemplateData
//    val ktOrJavaExt = projectData.language.extension
    val applicationPackage = projectData.applicationPackage
    val packageName = moduleTemplateData.packageName
    val page = StringUtil.getPageStr(applicationPackage, packageName)
    val floorCap = StringUtil.lineToHump(floor)

    val floorViewModel = TextViewModelKt(applicationPackage, packageName, page, floor, buildEntity)
    if (buildEntity) {
        save(floorViewModel, srcOut.resolve("${StringUtil.removeLine(floor)}/${floorCap}ViewModel${Constants.kotlinExt}"))
        save(StringEntityKt(applicationPackage, packageName, page, floor, floorCap, buildEntity), srcOut.resolve("${StringUtil.removeLine(floor)}/${floorCap.capitalize()}Entity${Constants.kotlinExt}"))
        save(floor_holder_text_Xml(), resOut.resolve("layout/floor_holder_${floor}.xml"))
    } else {
        save(floorViewModel, srcOut.resolve("${floorCap}ViewModel${Constants.kotlinExt}"))
        save(floor_holder_text_Xml(), resOut.resolve("layout/floor_holder_${floor}${Constants.xmlExt}"))
    }

}