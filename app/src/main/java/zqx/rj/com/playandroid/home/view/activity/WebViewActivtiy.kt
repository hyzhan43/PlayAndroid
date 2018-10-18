package zqx.rj.com.playandroid.home.view.activity

import android.view.View
import android.webkit.WebSettings
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.commom_bar.*
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.R
import com.just.agentweb.AgentWeb


/**
 * author：  HyZhan
 * created： 2018/10/18 14:29
 * desc：    TODO
 */
class WebViewActivtiy : BaseActivity() {

    private lateinit var mAgentWeb: AgentWeb

    override fun getLayoutId(): Int = R.layout.activity_webview

    override fun initView() {

        mIvBack.visibility = View.VISIBLE
        mIvBack.setOnClickListener { finish() }
        val link = intent.getStringExtra("link")
        mTvBarTitle.text = intent.getStringExtra("title")

        mAgentWeb = AgentWeb.with(this)
                // 传入 AgentWeb 父容器  mLlContent,  第二个参数是对应的 LinearLayout
                .setAgentWebParent(mLlContent, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()  // 默认进度条
                .createAgentWeb()
                .ready()
                .go(link)       // 加载 url
    }

    override fun onBackPressed() = finish()
}