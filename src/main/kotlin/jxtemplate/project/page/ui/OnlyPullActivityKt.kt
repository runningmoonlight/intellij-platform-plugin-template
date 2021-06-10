package jxtemplate.project.page.ui

import jxtemplate.util.StringUtil

/**
 * Created by liuheng on 2021/6/10.
 * OnlyPullActivity，只支持下拉刷新
 */
fun OnlyPullActivityKt(
        applicationPackage: String?,
        page: String
) = """
package ${applicationPackage}.${StringUtil.removeLine(page)}.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.deeplinkdispatch.DeepLink
import com.github.zackratos.ultimatebar.UltimateBar
import com.jd.pingou.base.BaseActivity
import com.jd.pingou.base.jxutils.android.JxApplication
import com.jd.pingou.base.jxutils.common.JxReportUtils
import ${applicationPackage}.R
import ${applicationPackage}.common.ErrorViewModel
import ${applicationPackage}.common.OnRetryListener
import ${applicationPackage}.common.PostedEvent
import ${applicationPackage}.common.PullToRefreshView
import ${applicationPackage}.${StringUtil.removeLine(page)}.vm.${StringUtil.lineToHump(page)}ViewModel
import com.jd.pingou.lib.adapter.core.ViewModelType
import com.jd.pingou.lib.adapter.core.into
import com.jd.pingou.widget.loading.PgCommonNetLoadingStyle2
import com.jd.pingou.widget.message.PgMessageView

//TODO 修改deeplink
@DeepLink("jdpingou://${StringUtil.getBundleStr(applicationPackage)}_${StringUtil.removeLine(page)}")
class ${StringUtil.lineToHump(page)}Activity : BaseActivity() {

    private lateinit var mBackIv: ImageView
    private lateinit var mTitleTv: TextView
    private lateinit var mMessageView: PgMessageView
    private lateinit var mPullToRefreshView: PullToRefreshView
    private lateinit var mCenterLoadingView: PgCommonNetLoadingStyle2

    private lateinit var m${StringUtil.lineToHump(page)}ViewModel: ${StringUtil.lineToHump(page)}ViewModel

    private val m${StringUtil.lineToHump(page)}ReportId = JxApplication.getApplication().resources.getString(R.string.${page}_reportPageId)

    companion object {
        private var TAG = ${StringUtil.lineToHump(page)}Activity::class.java.simpleName
    }

    private val m${StringUtil.lineToHump(page)}Adapter  by lazy {
        ${StringUtil.lineToHump(page)}Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        JxReportUtils.initWatcher(m${StringUtil.lineToHump(page)}ReportId, 3)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_${page})

        initView()
        initViewModel()

        immersive(true)
        JxReportUtils.athenaReport(m${StringUtil.lineToHump(page)}ReportId, 0)
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
        m${StringUtil.lineToHump(page)}Adapter.into(mPullToRefreshView.refreshableView)
        mPullToRefreshView.setOnRefreshListener {
            m${StringUtil.lineToHump(page)}ViewModel.sync${StringUtil.lineToHump(page)}Data("", false) //TODO
        }
        
        //loading view
        mCenterLoadingView = findViewById(R.id.view_center_loading)
    }

    private fun initViewModel() {
        m${StringUtil.lineToHump(page)}ViewModel = ViewModelProviders.of(this).get(${StringUtil.lineToHump(page)}ViewModel::class.java)
        m${StringUtil.lineToHump(page)}ViewModel.dataMutableLiveData.observe(this, Observer {
            mPullToRefreshView.onRefreshComplete()
            m${StringUtil.lineToHump(page)}Adapter.setData(it)
        })
        m${StringUtil.lineToHump(page)}ViewModel.loadingEventLiveData.observe(this, Observer {
            onEvent(it)
        })
        m${StringUtil.lineToHump(page)}ViewModel.stateEventLiveData.observe(this, Observer {
            mPullToRefreshView.onRefreshComplete()
            onEvent(it)
        })

        JxReportUtils.athenaReport(m${StringUtil.lineToHump(page)}ReportId, 2)
        //TODO 补充接口参数
        m${StringUtil.lineToHump(page)}ViewModel.sync${StringUtil.lineToHump(page)}Data("", true)
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
                m${StringUtil.lineToHump(page)}ViewModel.sync${StringUtil.lineToHump(page)}Data("", true)
            }
        }))
        m${StringUtil.lineToHump(page)}Adapter.setStateData(list)
    }
}
""".trimIndent()
