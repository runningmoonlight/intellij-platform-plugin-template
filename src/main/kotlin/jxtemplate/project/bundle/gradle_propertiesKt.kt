package jxtemplate.project.bundle

/**
 * Created by liuheng on 2021/6/3.
 * gradle.properties
 */
fun gradle_propertiesKt(
        pingouLibVersion: String
) = """
# Project-wide Gradle settings.

# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.

# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html

# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
# Default value: -Xmx10248m -XX:MaxPermSize=256m
# org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8

# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. More details, visit
# http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects
# org.gradle.parallel=true


# Note: Please modify the path if needed. An absolute path and a relative path are both supported.
# Note: if you not set this property, it will be set "../../jd-android-phone" by default.
# jdmall_dir = ../../jd-android-phone

buildToolsVersion=29.0.3
compileSdkVersion=29
minSdkVersion=18
targetSdkVersion=29

#关闭自动依赖
auraplus_config_auto_depent_aar=false
auraplus_config_auto_depent_amon=true

#公共库
public_lib_version=com.jingdong.wireless.jd_pingou.sdk:pingou-lib:${pingouLibVersion}-SNAPSHOT
#Aura构建插件版本
aura_plugin_version=5.0.+
#kotlin版本
kotlinVersion=1.3.72
#androidx
android.useAndroidX=true
android.enableJetifier=true
""".trimIndent()
