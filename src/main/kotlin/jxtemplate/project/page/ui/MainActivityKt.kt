package jxtemplate.project.page.ui

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/3.
 * MainActivity
 */
fun MainActivityKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.deeplinkdispatch.DeepLink
import com.facebook.drawee.view.SimpleDraweeView
import com.github.zackratos.ultimatebar.UltimateBar
import com.jd.pingou.base.BaseActivity
import com.jd.pingou.base.jxutils.android.JxApplication
import com.jd.pingou.base.jxutils.android.JxScreenUtils
import com.jd.pingou.base.jxutils.common.JxReportUtils
import com.jd.pingou.crash.ExceptionController
import ${applicationPackage}.R
import ${applicationPackage}.common.ErrorViewModel
import ${applicationPackage}.common.OnRetryListener
import ${applicationPackage}.common.PostedEvent
import ${applicationPackage}.common.PullToRefreshView
import ${applicationPackage}.${StringUtil.removeLine(page)}.vm.${StringUtil.lineToHump(page).capitalize()}ViewModel
import com.jd.pingou.lib.adapter.WrapperAdapter
import com.jd.pingou.lib.adapter.core.ViewModelType
import com.jd.pingou.lib.adapter.core.into
import com.jd.pingou.recommend.IRecommend
import com.jd.pingou.recommend.RecommendBuilder
import com.jd.pingou.recommend.entity.RecommendTab
import com.jd.pingou.recommend.ui.RecommendFooterView
import com.jd.pingou.recommend.ui.RecommendWidget
import com.jd.pingou.utils.PLog
import com.jd.pingou.widget.loading.PgCommonNetLoadingStyle2
import com.jd.pingou.widget.message.PgMessageView
import com.jingdong.sdk.platform.lib.utils.InflateUtil
import org.json.JSONObject
import java.util.ArrayList

//TODO 修改deeplink
@DeepLink("jdpingou://${StringUtil.getBundleStr(applicationPackage)}_${StringUtil.removeLine(page)}")
class ${StringUtil.lineToHump(page).capitalize()}Activity : BaseActivity(), IRecommend {

    private lateinit var mBackIv: ImageView
    private lateinit var mTitleTv: TextView
    private lateinit var mMessageView: PgMessageView
    private lateinit var mPullToRefreshView: PullToRefreshView
    private lateinit var mCenterLoadingView: PgCommonNetLoadingStyle2
    private lateinit var mToTopSdv: SimpleDraweeView

    private lateinit var mRecommendHeaderView: View
    private lateinit var mRecommendFooterView: RecommendFooterView
    private lateinit var mRecommendWidget: RecommendWidget

    private lateinit var m${StringUtil.lineToHump(page).capitalize()}ViewModel: ${StringUtil.lineToHump(page).capitalize()}ViewModel

    private var mRecommendParams: String = ""
    private var mRecyclerViewDy: Int = 0 //滚动dy
    private val mTopDistance = JxScreenUtils.getScreenHeightWithVirtKeyboard(JxApplication.getApplicationContext()) //滚动两个屏幕的距离，返回顶部按钮可见
    private val m${StringUtil.lineToHump(page).capitalize()}ReportId = JxApplication.getApplication().resources.getString(R.string.${page}_reportPageId)

    companion object {
        const val VIEW_TYPE_RECOMMEND_HEADER = 310
        const val VIEW_TYPE_RECOMMEND_FOOTER = 320
        const val VIEW_TYPE_RECOMMEND_WIDGET = 330

        private var TAG = ${StringUtil.lineToHump(page).capitalize()}Activity::class.java.simpleName
    }

    private val m${StringUtil.lineToHump(page).capitalize()}Adapter  by lazy {
        ${StringUtil.lineToHump(page).capitalize()}Adapter()
    }

    private val mWrapperAdapter by lazy {
        WrapperAdapter(m${StringUtil.lineToHump(page).capitalize()}Adapter)
    }

    val mRecycleViewScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            PLog.i(TAG, " onScrollStateChanged ---> newState : ${'$'}newState")
            //推荐组件的滚动不监听
            if (recyclerView == mRecommendWidget) {
                return
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val firstView = (mPullToRefreshView.refreshableView.layoutManager as LinearLayoutManager).findViewByPosition(0)
                if (null != firstView && firstView.top == 0) {
                    mRecyclerViewDy = 0
                    mToTopSdv.visibility = View.INVISIBLE
                }
            }
        }

        override fun onScrolled(mRecyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(mRecyclerView, dx, dy)
            PLog.i(TAG, " onScrolled --->${'$'}dy")
            mRecyclerViewDy += dy
            if (mRecyclerViewDy > mTopDistance) {
                mToTopSdv.visibility = View.VISIBLE
            } else {
                mToTopSdv.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        JxReportUtils.initWatcher(m${StringUtil.lineToHump(page).capitalize()}ReportId, 3)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_${page})

        initView()
        initViewModel()

        immersive(true)
        JxReportUtils.athenaReport(m$${StringUtil.lineToHump(page).capitalize()}ReportId, 0)
    }

    private fun initView() {
        mBackIv = findViewById(R.id.iv_back)
        mBackIv.setOnClickListener {
            finish()
        }

        mTitleTv = findViewById(R.id.tv_title)
        mMessageView = findViewById(R.id.message_view)

        //下拉刷新RecyclerView
        mPullToRefreshView = findViewById(R.id.view_pull_to_refresh)
        mWrapperAdapter.into(mPullToRefreshView.refreshableView)
        mPullToRefreshView.setOnRefreshListener {
            m${StringUtil.lineToHump(page).capitalize()}ViewModel.sync${StringUtil.lineToHump(page).capitalize()}Data("", false) //TODO
        }
        mPullToRefreshView.refreshableView.addOnScrollListener(mRecycleViewScrollListener)
        //推荐组件footer
        mRecommendFooterView = RecommendFooterView(this)
        mRecommendFooterView.setFooterState(RecommendFooterView.FOOTER_NORMAL)
        mRecommendFooterView.setOnClickListener {
            mRecommendFooterView.setFooterState(RecommendFooterView.FOOTER_NORMAL)
            mRecommendWidget.loadRecommendData()
        }
        //推荐组件header
        mRecommendHeaderView = InflateUtil.inflate(this, R.layout.view_recommend_header, mPullToRefreshView.refreshableView, false)
        //推荐组件
        mRecommendWidget = RecommendWidget(mPullToRefreshView.refreshableView, this,
                RecommendBuilder()
                        .setRecommendID("5714") //TODO
                        .setFullFooter(false)
                        .setEnableWaterFall(true)
                        .setEnablePageing(true)
                        .setMaxPageNum(10))
        mRecommendWidget.layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mRecommendWidget.setExpoDataPtag("") //TODO
        mRecommendWidget.setRDClickReportUrl("") //TODO
        mRecommendWidget.setOnRequestResultListener(object : RecommendWidget.OnNewRequestResultListener {
            override fun onSuccess(p0: ArrayList<RecommendTab>?) {
                mWrapperAdapter.notifyRemoveView(VIEW_TYPE_RECOMMEND_FOOTER)
                mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_HEADER, mRecommendHeaderView)
                mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_WIDGET, mRecommendWidget)
            }

            override fun onFailed() {
                if (!mRecommendWidget.hasRecommendData()) {
                    mRecommendFooterView.setFooterState(RecommendFooterView.FOOTER_ERROR)
                    mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_HEADER, mRecommendHeaderView)
                    mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_FOOTER, mRecommendFooterView)
                    mWrapperAdapter.notifyRemoveView(VIEW_TYPE_RECOMMEND_WIDGET)
                }
            }
        })
        mRecommendWidget.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                mRecycleViewScrollListener.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mRecycleViewScrollListener.onScrolled(recyclerView, dx, dy)
            }
        })
        mPullToRefreshView.setRecommendWidget(mRecommendWidget)

        //loading view
        mCenterLoadingView = findViewById(R.id.view_center_loading)
        //返回顶部view
        mToTopSdv = findViewById(R.id.sdv_to_top)
        mToTopSdv.setOnClickListener {
            scrollToTop()
        }
    }

    private fun initViewModel() {
        m${StringUtil.lineToHump(page).capitalize()}ViewModel = ViewModelProviders.of(this).get(${StringUtil.lineToHump(page).capitalize()}ViewModel::class.java)
        m${StringUtil.lineToHump(page).capitalize()}ViewModel.dataMutableLiveData.observe(this, Observer {
            mPullToRefreshView.onRefreshComplete()
            m${StringUtil.lineToHump(page).capitalize()}Adapter.setData(it)
            loadRecommend()
        })
        m${StringUtil.lineToHump(page).capitalize()}ViewModel.loadingEventLiveData.observe(this, Observer {
            onEvent(it)
        })
        m${StringUtil.lineToHump(page).capitalize()}ViewModel.stateEventLiveData.observe(this, Observer {
            mPullToRefreshView.onRefreshComplete()
            onEvent(it)
        })

        JxReportUtils.athenaReport(m${StringUtil.lineToHump(page).capitalize()}ReportId, 2)
        //TODO 补充接口参数
        m${StringUtil.lineToHump(page).capitalize()}ViewModel.sync${StringUtil.lineToHump(page).capitalize()}Data("", true)
    }

    override fun immersive(immersiveWhite: Boolean) {
        try {
            UltimateBar.Companion.with(this).statusDark(immersiveWhite)
                    .statusDrawable(ColorDrawable(Color.WHITE))
                    .create()
                    .drawableBar()
        } catch (t: Throwable) {
            //ignore
        }
    }

    private fun scrollToTop() {
        mRecommendWidget.scrollToTop()
        mPullToRefreshView.refreshableView.smoothScrollToPosition(0)
    }

    private fun loadRecommend() {
        val params: String = m${StringUtil.lineToHump(page).capitalize()}Adapter.mAllData?.mRecommendParams ?: ""

        //参数不变，显示原有推荐数据
        if (TextUtils.equals(mRecommendParams, params) && mRecommendWidget.hasRecommendData()) {
            mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_HEADER, mRecommendHeaderView)
            mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_WIDGET, mRecommendWidget)
            return
        }
        // 推荐位需要清空数据才会重新请求
        clearRecommend()
        mWrapperAdapter.notifyRemoveView(VIEW_TYPE_RECOMMEND_WIDGET)
        mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_HEADER, mRecommendHeaderView)
        mWrapperAdapter.notifyAddFollowView(VIEW_TYPE_RECOMMEND_FOOTER, mRecommendFooterView)
        if (!mRecommendWidget.hasRecommendData()) {
            mRecommendParams = params
            val jsonObject = JSONObject()
            try {
                jsonObject.put("sku", params)
            } catch (e: Exception) {
                ExceptionController.handleCaughtException("liuheng58", "RecommendWidget", "recommend params", e)
            }
            mRecommendWidget.setExtentParam(jsonObject)
            mRecommendWidget.loadRecommendData()
        }
    }

    ///清除推荐组件
    private fun clearRecommend() {
        mRecommendWidget.clearRecommend()
        m${StringUtil.lineToHump(page).capitalize()}Adapter.notifyDataSetChanged()
    }

    private fun onEvent(event: PostedEvent) {
        when (event.eventType) {
            PostedEvent.EVENT_SHOW_LOADING -> showLoading()
            PostedEvent.EVENT_HIDE_LOADING -> hideLoading()
            PostedEvent.EVENT_ERROR_STATE -> handleError()
        }
    }

    private fun showLoading() {
        mCenterLoadingView.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        mCenterLoadingView.visibility = View.GONE
    }

    private fun handleError() {
        val list = mutableListOf<ViewModelType>()
        list.add(ErrorViewModel(object : OnRetryListener {
            override fun onRetryClick() {
                //TODO
                m${StringUtil.lineToHump(page).capitalize()}ViewModel.sync${StringUtil.lineToHump(page).capitalize()}Data("", true)
            }
        }))
        m${StringUtil.lineToHump(page).capitalize()}Adapter.setStateData(list)
    }
}
""".trimIndent()
