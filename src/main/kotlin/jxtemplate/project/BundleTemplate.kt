package jxtemplate.project

import com.android.tools.idea.wizard.template.*
import jxtemplate.util.Constants

/**
 * Created by liuheng on 2021/6/4.
 * 插件工程模板配置
 */

val bundleTemplate
    get() = template {
        revision = 1
        name = "插件工程模板"
        description = "创建京喜androidx模板的插件工程"
        minApi = Constants.MIN_API
        minBuildApi = Constants.MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val bundlePath = stringParameter {
            name = "插件工程所在路径"
            default = ""
            help = "输入完整路径，如D:\\template\\project\\"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val bundleName = stringParameter {
            name = "插件工程名称name（最终格式：bundle-{name}"
            default = ""
            help = "输入插件工程名称小写，建议以jx开头"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val pingouLibVersion = stringParameter {
            name = "pingou-lib版本"
            default = "4.10.0"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val buildMainPage = booleanParameter {
            name = "是否创建默认主页面，为true后三个属性才有效"
            default = true
            help = "创建默认的页面模板（main）"
        }
        val enableFragment = booleanParameter {
            name = "是否使用activity嵌套fragment"
            default = true
        }
        val enablePullToRefresh = booleanParameter {
            name = "是否支持下拉刷新"
            default = true
            help = "true-支持， false-不支持"
        }
        val enableRecommendWidget = booleanParameter {
            name = "是否支持推荐组件"
            default = true
            help = "true-支持， false-不支持"
        }

        widgets(
                TextFieldWidget(bundlePath),
                TextFieldWidget(bundleName),
                TextFieldWidget(pingouLibVersion),
                CheckBoxWidget(buildMainPage),
                CheckBoxWidget(enableFragment),
                CheckBoxWidget(enablePullToRefresh),
                CheckBoxWidget(enableRecommendWidget)
        )

        recipe = {data: TemplateData ->
            bundleRecipe(
                    data as ModuleTemplateData,
                    bundlePath.value,
                    bundleName.value,
                    buildMainPage.value,
                    pingouLibVersion.value,
                    enableFragment.value,
                    enablePullToRefresh.value,
                    enableRecommendWidget.value
            )
        }





    }