package zqx.rj.com.playandroid

import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_webview.*
import zqx.rj.com.mvvm.base.BaseActivity
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.common_bar.*
import zqx.rj.com.mvvm.common.toHtml


/**
 * author：  HyZhan
 * created： 2018/10/18 14:29
 * desc：    TODO
 */
class WebViewActivity : BaseActivity() {

    private lateinit var mAgentWeb: AgentWeb

    override fun getLayoutId(): Int = R.layout.activity_webview

    override fun initView() {

        mIvBack.visibility = View.VISIBLE
        mIvBack.setOnClickListener { finish() }

        mIvSearch.visibility = View.GONE

        val link = intent.getStringExtra("link")
        mTvBarTitle.text = intent.getStringExtra("title").toHtml()

        mAgentWeb = AgentWeb.with(this)
                // 传入 AgentWeb 父容器  mLlContent,  第二个参数是对应的 LinearLayout
                .setAgentWebParent(mLlContent, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()  // 默认进度条
                .createAgentWeb()
                .ready()
                .go(link)       // 加载 url

        val setting = mAgentWeb.agentWebSettings.webSettings
        // 设置 支持缩放
        setting.builtInZoomControls = true
    }

    override fun onBackPressed() = finish()
}