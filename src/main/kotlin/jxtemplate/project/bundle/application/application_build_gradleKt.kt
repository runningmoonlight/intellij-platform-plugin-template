package jxtemplate.project.bundle.application

/**
 * Created by liuheng on 2021/6/3.
 * build.gradle
 */

fun application_build_gradleKt(
        bundle: String
) = """
apply plugin: 'com.android.application'
apply plugin: 'AuraApplication'


aura {
    //TODO 替换resId，范围应该在0x21~0x7E，不能和已有模块冲突，https://cf.jd.com/pages/viewpage.action?pageId=221709545
    resId = '0x6a'
    bundleName = 'com.jd.pingou.${bundle}'
    // 值为宿主存放插件so的路径
    hostBundleDir = 'hostdir/libs/armeabi'
    //isUseAura=true表示编译aura插件;isUseAura=false表示编译独立apk
    //isUseAura参数默认为true,若作为独立apk进行调试，请将该参数修改为false
    //对该参数的的修改请不要提交至远程仓库,否则CI打包会失败!!!
    isUseAura = true
    //是否使用AndResGuard混淆插件资源，默认为true
    isUseResGuard = true
    applyAura()
}

shared_aar {
    provided("${'$'}rootProject.public_lib_version", "res/raw/shared_res_public.xml")
}

android {
    compileSdkVersion Integer.parseInt(rootProject.compileSdkVersion)
    buildToolsVersion rootProject.buildToolsVersion
    defaultConfig {
        minSdkVersion rootProject.minSdkVersion
        targetSdkVersion rootProject.targetSdkVersion
    }
    packagingOptions {
        pickFirst 'lib/armeabi/libjdJmaEncryptUtil.so'
        pickFirst 'lib/armeabi/libDecryptorJni.so'
        pickFirst 'lib/armeabi/libtencentloc.so'
        pickFirst 'lib/armeabi/libSecurity.so'
        exclude 'lib/armeabi-v7a/libtencentloc.so'

        exclude 'META-INF/proguard/androidx-annotations.pro'
        exclude 'aapt2'
    }
    dynamicFeatures = [":dynamicfeature"]
}

""".trimIndent()
