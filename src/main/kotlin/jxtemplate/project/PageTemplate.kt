package jxtemplate.project

import com.android.tools.idea.wizard.template.*
import jxtemplate.util.Constants
import org.jetbrains.kotlin.idea.refactoring.pullUp.EmptyPullUpHelper

/**
 * Created by liuheng on 2021/6/9.
 * MVVM页面模板配置
 */
val pageTemplate
    get() = template {
        revision = 1
        name = "页面模板"
        description = "创建MVVM架构的页面"
        minBuildApi = Constants.MIN_API
        minApi = Constants.MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val page = stringParameter {
            name = "页面名称"
            default = ""
            help = "输入页面单词小写，多个单词用_隔开"
            constraints = listOf(Constraint.NONEMPTY)
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
                TextFieldWidget(page),
                CheckBoxWidget(enableFragment),
                CheckBoxWidget(enablePullToRefresh),
                CheckBoxWidget(enableRecommendWidget)
        )

        recipe = {data: TemplateData ->
            pageRecipe(
                    data as ModuleTemplateData,
                    page.value,
                    enableFragment.value,
                    enablePullToRefresh.value,
                    enableRecommendWidget.value
            )

        }


    }