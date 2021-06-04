package jxtemplate.project.page.ui

/**
 * Created by liuheng on 2021/6/3.
 * MainAdapter
 */

fun MainAdapterKt(
        bundle: String,
        page: String
) = """
package com.jd.pingou.${bundle}.${page}.ui

import com.jd.pingou.${bundle}.${page}.model.AllData
import com.jd.pingou.lib.adapter.core.BaseAdapter
import com.jd.pingou.lib.adapter.core.ViewModelType


/**
 * Created by liuheng on 2021/6/1.
 */
class ${page.capitalize()}Adapter: BaseAdapter<ViewModelType>() {

    var mAllData: AllData? = null
    private var mFloorList = mutableListOf<ViewModelType>()

    override fun getItem(position: Int): ViewModelType? {
        return mFloorList[position]
    }

    override fun getItemCount(): Int {
        return mFloorList.size
    }

    fun setData(allData: AllData) {
        mAllData = allData
        mFloorList.clear()
        mFloorList.addAll(allData.mFloorList)
        notifyDataSetChanged()
    }

    fun setStateData(dataList: List<ViewModelType>) {
        mFloorList.clear()
        mFloorList.addAll(dataList)
        notifyDataSetChanged()
    }
}
""".trimIndent()
