package jxtemplate.project.page.vm

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * MainViewModel
 */

fun MainViewModelKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${page}.vm

import androidx.lifecycle.MutableLiveData
import ${applicationPackage}.common.BaseViewModel
import ${applicationPackage}.common.PostedEvent
import ${applicationPackage}.${StringUtil.removeLine(page)}.model.AllData
import ${applicationPackage}.${StringUtil.removeLine(page)}.model.${StringUtil.lineToHump(page)}RepositoryImpl
import ${applicationPackage}.${StringUtil.removeLine(page)}.model.RequestParam
import ${applicationPackage}.${StringUtil.removeLine(page)}.model.entity.${StringUtil.lineToHump(page)}DataEntity
import ${applicationPackage}.${StringUtil.removeLine(page)}.ui.floor.EmptyViewModel
import com.jd.pingou.report.net.ReportOnCommonListener
import com.jd.pingou.report.net.ReportOption
import com.jd.pingou.report.net.RequestError
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by liuheng on 2021/6/1.
 * 页面ViewModel
 */
class ${StringUtil.lineToHump(page)}ViewModel: BaseViewModel() {

    companion object {
        private val requestCount = AtomicInteger(0)
    }

    private val m${StringUtil.lineToHump(page)}Repository = ${StringUtil.lineToHump(page)}RepositoryImpl()
    val dataMutableLiveData = MutableLiveData<AllData>()
    val stateEventLiveData = MutableLiveData<PostedEvent>() //发送状态和其他事件

    fun sync${StringUtil.lineToHump(page)}Data(param: String, showLoading: Boolean = true) {
        val requestParam = RequestParam(param)
        //TODO 根据页面修改
        val reportOption = ReportOption("", "", "code", "msg")//"code", "msg"存在接口差异，取接口相关key
        val listener = object : ReportOnCommonListener<${StringUtil.lineToHump(page)}DataEntity>(${StringUtil.lineToHump(page)}DataEntity::class.java, reportOption) {
            override fun onEnd(entity: ${StringUtil.lineToHump(page)}DataEntity?) {
                if (showLoading) {
                    checkHideLoading()
                }
                //TODO 根据接口定义修改
                if (entity?.code == null || entity.code != 0) {
                    stateEventLiveData.postValue(PostedEvent(PostedEvent.EVENT_ERROR_STATE, entity!!.msg))
                    return
                }

                val allData = AllData()
                allData.parseData(entity)
                if (allData.mFloorList.isEmpty()) {
                    allData.mFloorList.add(EmptyViewModel())
                    allData.isEmpty = true
                }
                dataMutableLiveData.postValue(allData)
            }

            override fun onError(requestError: RequestError?) {
                if (showLoading) {
                    checkHideLoading()
                }
                stateEventLiveData.postValue(PostedEvent(PostedEvent.EVENT_ERROR_STATE))
            }
        }
        if (showLoading) {
            checkShowLoading()
        }
        m${StringUtil.lineToHump(page)}Repository.sync${StringUtil.lineToHump(page)}Data(listener, requestParam)
    }

    private fun checkShowLoading() {
        if (requestCount.get() == 0) {
            loadingEventLiveData.postValue(PostedEvent(PostedEvent.EVENT_SHOW_LOADING))
        }
        requestCount.incrementAndGet()
    }

    private fun checkHideLoading() {
        requestCount.decrementAndGet()
        if (requestCount.get() == 0) {
            loadingEventLiveData.postValue(PostedEvent(PostedEvent.EVENT_HIDE_LOADING))
        }
    }
}
""".trimIndent()

