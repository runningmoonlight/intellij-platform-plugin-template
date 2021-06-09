package jxtemplate.project.bundle

/**
 * Created by liuheng on 2021/6/8.
 */
fun bundle_build_gradle()
= """
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor 1, 'minutes'
            cacheDynamicVersionsFor 1, 'minutes'
        }
    }
    repositories {
        maven { url "http://artifactory.jd.com/libs-releases-local/" }
        maven { url "http://artifactory.jd.com/libs-snapshots-local/" }
        maven { url "https://maven.google.com" }
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:3.3.0'
        classpath "com.jingdong.wireless.aurasdk:auraplugin:${'$'}aura_plugin_version"
        // Kotlin编译插件
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:${'$'}kotlinVersion"
    }
}
allprojects {
    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor 1, 'minutes'
            cacheDynamicVersionsFor 1, 'minutes'
        }
    }
    repositories {
        maven { url 'http://artifactory.jd.com/libs-releases-local/' }
        maven { url "http://artifactory.jd.com/libs-snapshots-local/" }
        maven { url "https://maven.google.com" }
        jcenter()
        mavenCentral()
    }
}

""".trimIndent()