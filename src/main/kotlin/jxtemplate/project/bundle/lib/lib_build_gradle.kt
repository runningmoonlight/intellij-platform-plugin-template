package jxtemplate.project.bundle.lib

/**
 * Created by liuheng on 2021/6/8.
 */
fun lib_build_gradle()
= """
apply plugin: 'com.android.library'
apply plugin: 'AuraLibrary'
// 使用Kotlin插件
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'

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

dependencies {
    implementation fileTree(dir: 'libs', include: '*.jar')
    api "${'$'}rootProject.public_lib_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${'$'}rootProject.kotlinVersion"
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
}


""".trimIndent()