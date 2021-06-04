package jxtemplate.project.bundle.lib.src.common

/**
 * Created by liuheng on 2021/6/3.
 * RecommendParentRecycler
 */

fun RecommendParentRecyclerKt(
        bundle: String
) = """
package com.jd.pingou.${bundle}.common

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.NestedScrollingParent2
import androidx.recyclerview.widget.RecyclerView
import com.jd.pingou.recommend.ScrollDispatchHelper
import com.jd.pingou.recommend.ScrollDispatchHelper.ScrollDispatchChild
import com.jd.pingou.recommend.ScrollDispatchHelper.ScrollDispatchParent
import com.jd.pingou.recommend.ui.RecommendWidget


/**
 * Created by liuheng on 2021/5/27.
 * 推荐组件RecyclerView
 */
class RecommendParentRecycler(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs), ScrollDispatchParent, NestedScrollingParent2 {
    private var scrollHelper: ScrollDispatchHelper = ScrollDispatchHelper(this, getContext())
    private var recommendWidget: RecommendWidget? = null

    constructor(context: Context) : this(context, null)

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return if (scrollHelper.isChildConsumeTouch(e)) {
            false
        } else super.onInterceptTouchEvent(e)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?,
                                         type: Int): Boolean {
        return if (scrollHelper.dispatchNestedPreScroll(this, dx, dy, consumed, offsetInWindow,
                            type)) {
                true
            } else super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
    }

    override fun getChildView(): ScrollDispatchChild? {
        return recommendWidget
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        recommendWidget?.clearRecommend()
        recommendWidget = null
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return scrollHelper.onStartNestedScroll(target) ?: false
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {}
    override fun onStopNestedScroll(target: View, type: Int) {}
    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {}
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        scrollHelper.onNestedPreScroll(target, dx, dy, consumed)
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {}
    override fun onStopNestedScroll(child: View) {}
    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {}
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {}
    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return false
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun getNestedScrollAxes(): Int {
        return 0
    }

    fun setRecommendWidget(recommendWidget: RecommendWidget) {
        this.recommendWidget = recommendWidget
    }

    companion object {
        private const val TAG = "RecommendParentRecycler"
    }

    init {
        addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (bottom - top != oldBottom - oldTop) { //view 高度发生变化
                recommendWidget?.let {
                    val layoutParams = it.layoutParams
                    if (layoutParams == null) {
                        it.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bottom - top)
                    } else {
                        layoutParams.height = bottom - top
                        it.layoutParams = layoutParams
                    }
                }
            }
        }
    }
}
""".trimIndent()
