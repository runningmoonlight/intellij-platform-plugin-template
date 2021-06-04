package jxtemplate.project.bundle.lib.src.util

/**
 * Created by liuheng on 2021/6/3.
 * ExposedHelper
 */

fun ExposedHelperKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.util

import android.text.TextUtils
import com.jd.pingou.base.jxutils.android.JxApplication
import com.jd.pingou.report.PGReportInterface

/**
 * Created by liuheng on 2021/5/10.
 */
object ExposedHelper {


    private var mtaURL = ""  //TODO 初始化url

    /**
     * 点击实时上报 wg_wx.000001
     * @param target
     */
    fun clickRealTimeMta(target: String) {
        PGReportInterface.sendRealTimeClickEvent(JxApplication.getApplicationContext(), target)
    }

    /**
     * 点击实时上报 wg_wx.000001
     * @param target
     * @param extraData 附加参数
     */
    fun clickRealTimeMta(target: String, extraData: HashMap<String, String>) {
        PGReportInterface.sendRealTimeClickEvent(JxApplication.getApplicationContext(), target, mtaURL, "", extraData)
    }

    /**
     * 点击批量上报 wg_wx.000001
     * @param target
     */
    fun clickMta(target: String) {
        PGReportInterface.sendClickData(JxApplication.getApplicationContext(), mtaURL, target)
    }

    /**
     * pv实时上报 wg_wx.000000
     * @param obj 通常为页面activity/fragment对象
     * @param fromHomeActivity 是否HomeActivity
     */
    fun pvMta(obj: Any, fromHomeActivity: Boolean) {
        // HomeActivity pv上报需要增加参数showFootNav=0
        var url = mtaURL
        if (fromHomeActivity && !TextUtils.isEmpty(url)) {
            //这样添加参数有点粗糙
            url = if (url.contains("?")) {
                "${'$'}url&showFootNav=0"
            } else {
                "${'$'}url?showFootNav=0"
            }
        }
        pvMta(url, obj)
    }

    /**
     * pv上报
     * @param url pv url
     * @param obj 通常为页面activity/fragment对象
     */
    fun pvMta(url: String, obj: Any) {
        PGReportInterface.sendPvEvent(JxApplication.getApplicationContext(), obj, url)
    }

    /**
     * 曝光批量上报 wg_wx.000003
     */
    fun exposureMta(target: String) {
        PGReportInterface.sendExposureData(JxApplication.getApplicationContext(), target)
    }

}
""".trimIndent()
