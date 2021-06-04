package jxtemplate.project.bundle

/**
 * Created by liuheng on 2021/6/3.
 * setting.gradle
 */
fun setting_gradleKt(
        bundle: String?
) = """
include ':${bundle}', ':application',":dynamicfeature"
""".trimIndent()