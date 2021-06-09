package jxtemplate.project

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import jxtemplate.project.page.model.AllDataKt
import jxtemplate.project.page.model.IMainRepositoryKt
import jxtemplate.project.page.model.MainRepositoryImplKt
import jxtemplate.project.page.model.RequestParamKt
import jxtemplate.project.page.model.entity.MainDataEntityKt
import jxtemplate.project.page.ui.*
import jxtemplate.project.page.vm.MainViewModelKt
import jxtemplate.util.Constants
import jxtemplate.util.StringUtil
import java.io.File

/**
 * Created by liuheng on 2021/6/9.
 * MVVM页面模板
 */
fun RecipeExecutor.pageRecipe(
        moduleTemplateData: ModuleTemplateData,
        page: String,
        enableFragment: Boolean,
        enablePullToRefresh: Boolean,
        enableRecommendWidget: Boolean
) {
    val (projectData, srcOut, resOut) = moduleTemplateData

    var applicationPackage = projectData.applicationPackage
    val packageName = moduleTemplateData.packageName
    if (applicationPackage == null || applicationPackage.isEmpty()) {
        applicationPackage = packageName
    }

    pageRecipe(
            moduleTemplateData,
            page,
            enableFragment,
            enablePullToRefresh,
            enableRecommendWidget,
            applicationPackage.toString(),
            srcOut.resolve(StringUtil.removeLine(page)),
            resOut
    )
}


fun RecipeExecutor.pageRecipe(
        moduleTemplateData: ModuleTemplateData,
        page: String,
        enableFragment: Boolean,
        enablePullToRefresh: Boolean,
        enableRecommendWidget: Boolean,
        applicationPackage: String,
        srcOut: File,
        resOut: File
) {
    //model
    val modelOut = srcOut.resolve("model")
    save(AllDataKt(applicationPackage, page), modelOut.resolve("AllData${Constants.kotlinExt}"))
    save(IMainRepositoryKt(applicationPackage, page), modelOut.resolve("I${StringUtil.lineToHump(page).capitalize()}Repository${Constants.kotlinExt}"))
    save(MainRepositoryImplKt(applicationPackage, page), modelOut.resolve("${StringUtil.lineToHump(page).capitalize()}RepositoryImpl${Constants.kotlinExt}"))
    save(RequestParamKt(applicationPackage, page), modelOut.resolve("RequestParam${Constants.kotlinExt}"))
    save(MainDataEntityKt(applicationPackage, page), modelOut.resolve("entity/${StringUtil.lineToHump(page).capitalize()}DataEntity${Constants.kotlinExt}"))
    //ui
    val uiOut = srcOut.resolve("ui")
    save(MainActivityKt(applicationPackage, page), uiOut.resolve("${StringUtil.lineToHump(page).capitalize()}Activity${Constants.kotlinExt}"))
    save(MainAdapterKt(applicationPackage, page), uiOut.resolve("${StringUtil.lineToHump(page).capitalize()}MainAdapter${Constants.kotlinExt}"))
    save(activity_page_Xml(applicationPackage, page), resOut.resolve("layout/activity_${page}.xml"))
    srcOut.resolve("floor").mkdirs()
    //vm
    val vmOut = srcOut.resolve("vm")
    save(MainViewModelKt(applicationPackage, page), vmOut.resolve("${StringUtil.lineToHump(page).capitalize()}ViewModel${Constants.kotlinExt}"))

    //string
    mergeXml(string_page_reportId_Xml(page), resOut.resolve("values/string.xml"))

    //activity manifest
    mergeXml(activity_AndroidManifest_Xml(page), moduleTemplateData.manifestDir.resolve("AndroidManifest.xml"))

}