package jxtemplate.project

import com.android.tools.idea.wizard.template.*
import jxtemplate.util.Constants

/**
 * Created by liuheng on 2021/6/4.
 * 楼层模板配置
 */

val floorTemplate
    get() = template {
        revision = 1
        name = "楼层模板"
        description = "创建楼层对应的ViewModel和Entity类"
        minApi = Constants.MIN_API
        minBuildApi = Constants.MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val floorName = stringParameter {
            name = "楼层名称"
            default = ""
            help = "只输入楼层名称小写，多个单词用_分隔，不要带ViewModel"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val buildEntity = booleanParameter {
            name = "是否创建Entity类"
            default = true
            help = "true-创建楼层Entity类，和楼层ViewModel在同一文件夹，false-不创建楼层Entity类"
        }

        widgets(
                TextFieldWidget(floorName),
                CheckBoxWidget(buildEntity)
        )

        recipe = {data: TemplateData ->
            floorRecipe(
                    data as ModuleTemplateData,
                    floorName.value,
                    buildEntity.value
            )
        }

    }

