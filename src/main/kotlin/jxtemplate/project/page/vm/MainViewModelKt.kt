package jxtemplate.project.page.vm

/**
 * Created by liuheng on 2021/6/3.
 * MainViewModel
 */

fun MainViewModelKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.vm

import androidx.lifecycle.MutableLiveData
import com.jd.pingou.${bundle}.common.BaseViewModel
import com.jd.pingou.${bundle}.common.PostedEvent
import com.jd.pingou.${bundle}.${page}.model.AllData
import com.jd.pingou.${bundle}.${page}.model.${page.capitalize()}RepositoryImpl
import com.jd.pingou.${bundle}.${page}.model.RequestParam
import com.jd.pingou.${bundle}.${page}.model.entity.${page.capitalize()}DataEntity
import com.jd.pingou.${bundle}.${page}.ui.floor.EmptyViewModel
import com.jd.pingou.report.net.ReportOnCommonListener
import com.jd.pingou.report.net.ReportOption
import com.jd.pingou.report.net.RequestError
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by liuheng on 2021/6/1.
 * 页面ViewModel
 */
class ${page.capitalize()}ViewModel: BaseViewModel() {

    companion object {
        private val requestCount = AtomicInteger(0)
    }

    private val ${page}Repository = ${page.capitalize()}RepositoryImpl()
    val dataMutableLiveData = MutableLiveData<AllData>()
    val stateEventLiveData = MutableLiveData<PostedEvent>() //发送状态和其他事件

    fun sync${page.capitalize()}Data(param: String, showLoading: Boolean = true) {
        val requestParam = RequestParam(param)
        //TODO 根据页面修改
        val reportOption = ReportOption("", "", "code", "msg")//"code", "msg"存在接口差异，取接口相关key
        val listener = object : ReportOnCommonListener<${page.capitalize()}DataEntity>(${page.capitalize()}DataEntity::class.java, reportOption) {
            override fun onEnd(entity: ${page.capitalize()}DataEntity?) {
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
        ${page}Repository.sync${page.capitalize()}Data(listener, requestParam)
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

