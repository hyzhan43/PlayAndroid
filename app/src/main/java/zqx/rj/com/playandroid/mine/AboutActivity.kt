package zqx.rj.com.playandroid.mine

import com.zhan.ktwing.ext.startActivity
import com.zhan.mvvm.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_about.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * created： 2018/10/29 18:48
 * desc：    关于我
 */
class AboutActivity: ToolbarActivity() {

    private val github = "https://github.com/hyzhan43"
    private val csdn = "https://blog.csdn.net/weixin_40595516"
    private val nuggets = "https://juejin.im/user/5c7c8bd4518825408d6fe014"

    private val author = "HyZhan"

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        super.initView()

        toolbarTitle = getString(R.string.mine_about)

        mTvGithub.setOnClickListener {
            startActivity<WebViewActivity>(Key.LINK to github, Key.TITLE to author)
        }

        mTvCsdn.setOnClickListener {
            startActivity<WebViewActivity>(Key.LINK to csdn, Key.TITLE to author)
        }

        mTvNuggets.setOnClickListener {
            startActivity<WebViewActivity>(Key.LINK to nuggets, Key.TITLE to author)
        }
    }

    override fun onBackPressed() = finish()
}