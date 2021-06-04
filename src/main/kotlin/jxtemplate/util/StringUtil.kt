package jxtemplate.util

import java.util.regex.Pattern

/**
 * Created by liuheng on 2021/6/4.
 */
object StringUtil {

    /**
     * 返回page
     * @param applicationPackage 应用package 比如：com.jd.pingou
     * @param packageName 包路径，比如：com.jd.pingou.jxcart.common
     */
    fun getPageStr(applicationPackage: String?, packageName: String): String {
        if (applicationPackage == null || applicationPackage.isEmpty() || !packageName.startsWith(applicationPackage)) {
            val packageNameArray = packageName.split(".")
            return packageNameArray[0]
        }
        val pageSub = packageName.substring(applicationPackage.length + 1)
        val pageSubArray = pageSub.split(".")
        return pageSubArray[0]
    }


    private val linePattern = Pattern.compile("_(\\w)")
    /**
     * 下划线转驼峰
     */
    fun  lineToHump(str: String): String {
        val lowerStr = str.toLowerCase()
        val matcher = linePattern.matcher(lowerStr)
        val sb = StringBuffer()
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase())
        }
        matcher.appendTail(sb)
        return sb.toString().capitalize()
    }

    /**
     * 去掉下划线
     */
    fun removeLine(str: String): String {
        val lowerStr = str.toLowerCase()
        return lowerStr.replace("_", "")
    }

}