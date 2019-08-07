package zqx.rj.com.playandroid.mine.view.activity

import com.zhan.mvvm.ext.startActivity
import kotlinx.android.synthetic.main.activity_about.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.delete.ToolbarActivity

/**
 * author：  HyZhan
 * created： 2018/10/29 18:48
 * desc：    关于我
 */
class AboutActivity: ToolbarActivity() {

    private val github = "https://github.com/hyzhan43"
    private val csdn = "https://blog.csdn.net/weixin_40595516"
    private val nuggets = "https://juejin.im/user/5c7c8bd4518825408d6fe014"

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        super.initView()

        toolbarTitle = getString(R.string.mine_about)

        mTvGithub.setOnClickListener {
            startActivity<WebViewActivity>("link" to github, "title" to "HyZhan")
        }

        mTvCsdn.setOnClickListener {
            startActivity<WebViewActivity>("link" to csdn, "title" to "HyZhan")
        }

        mTvNuggets.setOnClickListener {
            startActivity<WebViewActivity>("link" to nuggets, "title" to "HyZhan")
        }
    }

    override fun onBackPressed() = finish()
}