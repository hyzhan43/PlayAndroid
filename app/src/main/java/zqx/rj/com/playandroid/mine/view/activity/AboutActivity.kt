package zqx.rj.com.playandroid.mine.view.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.common_bar.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivtiy

/**
 * author：  HyZhan
 * created： 2018/10/29 18:48
 * desc：    关于我
 */
class AboutActivity: BaseActivity() {

    private val github: String = "https://github.com/hyzhan43"
    private val csdn: String = "https://blog.csdn.net/weixin_40595516"

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        super.initView()

        mIvBack.visibility = View.VISIBLE
        mIvBack.setOnClickListener { finish() }

        mTvBarTitle.text = getString(R.string.mine_about)

        mIvSearch.visibility = View.GONE


        mTvGithub.setOnClickListener {
            startActivity<WebViewActivtiy>("link" to github, "title" to "HyZhan")
        }

        mTvCsdn.setOnClickListener {
            startActivity<WebViewActivtiy>("link" to csdn, "title" to "HyZhan")
        }
    }

    override fun onBackPressed() = finish()
}