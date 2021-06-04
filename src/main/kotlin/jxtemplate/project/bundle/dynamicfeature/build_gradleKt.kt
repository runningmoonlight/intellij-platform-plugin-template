package jxtemplate.project.bundle.dynamicfeature

/**
 * Created by liuheng on 2021/6/3.
 * build.gradle
 */

fun build_gradleKt(
        bundle: String
) = """
apply plugin: 'com.android.dynamic-feature'
apply plugin: 'AuraDynamicFeature'


aura {
    applyAura()
}
android {
    compileSdkVersion Integer.parseInt(rootProject.compileSdkVersion)
    buildToolsVersion rootProject.buildToolsVersion
    defaultConfig {
        minSdkVersion rootProject.minSdkVersion
        targetSdkVersion rootProject.targetSdkVersion

    }

}

andResGuard {
    //配置需要keep的资源路径
    mappingFile = file("./resource_mapping.txt")
    //配置需要keep的资源
    whiteList = []
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation project(':application')
    implementation project(':${bundle}')
}

""".trimIndent()
