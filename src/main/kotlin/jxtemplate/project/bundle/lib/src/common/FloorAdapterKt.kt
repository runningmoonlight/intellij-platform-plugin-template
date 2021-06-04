package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 * FloorAdapter
 */

fun FloorAdapterKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

import com.jd.pingou.lib.adapter.core.BaseAdapter
import com.jd.pingou.lib.adapter.core.ViewModelType

/**
 * Created by liuheng on 2021/6/1.
 * 楼层adapter，简单通用
 */
class FloorAdapter: BaseAdapter<ViewModelType>() {

    private var mDataList = mutableListOf<ViewModelType>()

    override fun getItem(position: Int): ViewModelType {
        return mDataList[position]
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setData(dataList: ArrayList<ViewModelType>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }
}
""".trimIndent()
