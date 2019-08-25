package zqx.rj.com.playandroid.common

import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.zhan.mvvm.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_webview.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.other.constant.Key
import zqx.rj.com.playandroid.other.ext.toHtml


/**
 * author：  HyZhan
 * created： 2018/10/18 14:29
 * desc：    TODO
 */
class WebViewActivity : ToolbarActivity() {

    private lateinit var mAgentWeb: AgentWeb

    override fun getLayoutId(): Int = R.layout.activity_webview

    override fun initView() {
        super.initView()

        toolbarTitle = intent.getStringExtra(Key.TITLE).toHtml()
        val link = intent.getStringExtra(Key.LINK)


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