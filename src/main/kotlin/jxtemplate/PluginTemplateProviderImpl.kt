package jxtemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import jxtemplate.project.floorTemplate

/**
 * Created by liuheng on 2021/6/3.
 */
class PluginTemplateProviderImpl: WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        floorTemplate
    )
}