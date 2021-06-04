package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 * PullToRefreshView
 */

fun PullToRefreshViewKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView
import com.jd.pingou.recommend.ui.RecommendWidget


/**
 * Created by liuheng on 2021/5/27.
 * 下拉刷新RecyclerView
 */
class PullToRefreshView : PullToRefreshRecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, mode: Mode?) : super(context, mode)
    constructor(context: Context, mode: Mode?, style: AnimationStyle?) : super(context, mode, style)

    override fun createRefreshableView(context: Context, attrs: AttributeSet?): RecyclerView {
        mRefreshableView = RecommendParentRecycler(context)
        return mRefreshableView
    }

    /**
     * 设置推荐组件
     */
    fun setRecommendWidget(recommendWidget: RecommendWidget) {
        if (mRefreshableView is RecommendParentRecycler) {
            (mRefreshableView as RecommendParentRecycler).setRecommendWidget(recommendWidget)
        }
    }
}
""".trimIndent()
