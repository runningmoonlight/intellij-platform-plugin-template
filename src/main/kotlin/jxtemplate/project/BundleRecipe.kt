package jxtemplate.project

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import jxtemplate.project.bundle.application.application_AndroidManifest_Xml
import jxtemplate.project.bundle.application.application_build_gradleKt
import jxtemplate.project.bundle.application.proguard_projectTxt
import jxtemplate.project.bundle.bundle_build_gradle
import jxtemplate.project.bundle.bundle_gitignore
import jxtemplate.project.bundle.dynamicfeature.dynamicfeature_AndroidManifest_Xml
import jxtemplate.project.bundle.dynamicfeature.dynamicfeature_build_gradleKt
import jxtemplate.project.bundle.dynamicfeature.dynamicfeature_gitignore
import jxtemplate.project.bundle.dynamicfeature.resource_mapping_Txt
import jxtemplate.project.bundle.gradle_propertiesKt
import jxtemplate.project.bundle.lib.lib_AndroidManifest_Xml
import jxtemplate.project.bundle.lib.lib_build_gradle
import jxtemplate.project.bundle.lib.res.values.string_Xml
import jxtemplate.project.bundle.lib.src.DemoApplicationKt
import jxtemplate.project.bundle.lib.src.DemoClassKt
import jxtemplate.project.bundle.lib.src.DemoMainActivityKt
import jxtemplate.project.bundle.lib.src.common.*
import jxtemplate.project.bundle.lib.src.util.ExposedHelperKt
import jxtemplate.project.bundle.lib.src.util.RequestHelperKt
import jxtemplate.project.bundle.setting_gradleKt
import jxtemplate.util.Constants
import jxtemplate.util.ResourceHelper
import org.gradle.internal.impldep.org.apache.ivy.plugins.resolver.util.ResolverHelper
import java.io.File

/**
 * Created by liuheng on 2021/6/3.
 * 插件工程模板
 */

fun RecipeExecutor.bundleRecipe(
        moduleTemplateData: ModuleTemplateData,
        bundlePath: String,
        bundleName: String,
        buildMainPage: Boolean,
        pingouLibVersion: String,
        enableFragment: Boolean,
        enablePullToRefresh: Boolean,
        enableRecommendWidget: Boolean
) {
    var page = ""
    if (buildMainPage) {
        page = "main"
    }

    val rootFile = File(bundlePath)
    val bundleOut = rootFile.resolve("bundle-${bundleName}")
    save(bundle_build_gradle(), bundleOut.resolve("build.gradle"))
    save(bundle_gitignore(), bundleOut.resolve(".gitignore"))
    save(gradle_propertiesKt(pingouLibVersion), bundleOut.resolve("gradle.properties"))
    save(setting_gradleKt(bundleName), bundleOut.resolve("settings.gradle"))
    ResourceHelper.copyResourceFile("/gradlewrapper/gradlew", bundleOut.resolve("gradlew"))
    ResourceHelper.copyResourceFile("/gradlewrapper/gradlew.bat", bundleOut.resolve("gradlew.bat"))

    //application
    val applicationOut = bundleOut.resolve("application")
    save(application_AndroidManifest_Xml(bundleName), applicationOut.resolve("AndroidManifest.xml"))
    save(application_build_gradleKt(bundleName), applicationOut.resolve("build.gradle"))
    save(proguard_projectTxt(), applicationOut.resolve("proguard-project.txt"))

    //dynamicfeature
    val dynamicfeatureOut = bundleOut.resolve("dynamicfeature")
    dynamicfeatureOut.resolve("assets").mkdirs()
    save(dynamicfeature_gitignore(), dynamicfeatureOut.resolve(".gitignore"))
    save(dynamicfeature_AndroidManifest_Xml(bundleName), dynamicfeatureOut.resolve("AndroidManifest.xml"))
    save(dynamicfeature_build_gradleKt(bundleName), dynamicfeatureOut.resolve("build.gradle"))
    save(resource_mapping_Txt(), dynamicfeatureOut.resolve("resource_mapping.txt"))

    //gradle wrapper
    ResourceHelper.copyResourceFile("/gradlewrapper/gradle-wrapper.jar", bundleOut.resolve("gradle/wrapper/gradle-wrapper.jar"))
    ResourceHelper.copyResourceFile("/gradlewrapper/gradle-wrapper.properties", bundleOut.resolve("gradle/wrapper/gradle-wrapper.properties"))

    //lib
    val libOut = bundleOut.resolve(bundleName)
    save(lib_AndroidManifest_Xml(bundleName, page), libOut.resolve("AndroidManifest.xml"))
    save(lib_build_gradle(), libOut.resolve("build.gradle"))
    //res
    val libResOut = libOut.resolve("res")
    libResOut.resolve("drawable-hdpi").mkdirs()
    libResOut.resolve("drawable-mdpi").mkdirs()
    libResOut.resolve("drawable-xhdpi").mkdirs()
    ResourceHelper.copyResourceFile("/icons/icon_jx_back_top.png", libResOut.resolve("drawable-xhdpi/icon_jx_back_top.png"))
    libResOut.resolve("drawable-xxhdpi").mkdirs()
    libResOut.resolve("drawable-xxxhdpi").mkdirs()
    //layout
    val libResLayoutOut = libResOut.resolve("layout")
    libResLayoutOut.mkdirs()
    ResourceHelper.copyResourceFile("/layout/view_state_error.xml", libResLayoutOut.resolve("view_state_error.xml"))
    //values
    val libResValuesOut = libResOut.resolve("values")
    libResValuesOut.mkdirs()
    save(string_Xml(bundleName), libResValuesOut.resolve("string.xml"))
    ResourceHelper.copyResourceFile("/values/attrs.xml", libResValuesOut.resolve("attrs.xml"))
    ResourceHelper.copyResourceFile("/values/colors.xml", libResValuesOut.resolve("colors.xml"))
    ResourceHelper.copyResourceFile("/values/styles.xml", libResValuesOut.resolve("styles.xml"))
    //src
    val libSrcOut = libOut.resolve("src/com/jd/pingou/${bundleName}")
    //common
    val libSrcCommonOut = libSrcOut.resolve("common")
    save(BaseViewModelKt(bundleName), libSrcCommonOut.resolve("BaseViewModel${Constants.kotlinExt}"))
    save(ErrorViewModelKt(bundleName), libSrcCommonOut.resolve("ErrorViewModel${Constants.kotlinExt}"))
    save(FloorAdapterKt(bundleName), libSrcCommonOut.resolve("FloorAdapter${Constants.kotlinExt}"))
    save(PostedEventKt(bundleName), libSrcCommonOut.resolve("PostedEvent${Constants.kotlinExt}"))
    save(PullToRefreshViewKt(bundleName), libSrcCommonOut.resolve("PullToRefreshView${Constants.kotlinExt}"))
    save(RecommendParentRecyclerKt(bundleName), libSrcCommonOut.resolve("RecommendParentRecycler${Constants.kotlinExt}"))
    save(RequestStateKt(bundleName), libSrcCommonOut.resolve("RequestState${Constants.kotlinExt}"))
    //dialog
    libSrcOut.resolve("dialog").mkdirs()
    //util
    val libSrcUtilOut = libSrcOut.resolve("util")
    save(ExposedHelperKt(bundleName), libSrcUtilOut.resolve("ExposedHelper${Constants.kotlinExt}"))
    save(RequestHelperKt(bundleName), libSrcUtilOut.resolve("RequestHelper${Constants.kotlinExt}"))
    //widget
    libSrcOut.resolve("widget").mkdirs()

    save(DemoApplicationKt(bundleName), libSrcOut.resolve("DemoApplication${Constants.javaExt}"))
    save(DemoClassKt(bundleName), libSrcOut.resolve("DemoClass${Constants.javaExt}"))
    save(DemoMainActivityKt(bundleName), libSrcOut.resolve("DemoMainActivity${Constants.javaExt}"))

    if (buildMainPage) {
        pageRecipe(
                page,
                enableFragment,
                enablePullToRefresh,
                enableRecommendWidget,
                "com.jd.pingou.${bundleName}",
                libSrcOut.resolve(page),
                libResOut,
                libOut
        )

    }




}