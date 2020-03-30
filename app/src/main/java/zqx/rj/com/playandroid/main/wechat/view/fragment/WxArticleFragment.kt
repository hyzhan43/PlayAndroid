package zqx.rj.com.playandroid.main.wechat.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.logd
import com.zhan.ktwing.ext.startActivity
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.base.IFragment
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.mvvm.MvmFragment
import kotlinx.android.synthetic.main.layout_article_list.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.common.article.adapter.ArticleAdapter
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.main.home.vm.HomeViewModel
import zqx.rj.com.playandroid.main.wechat.vm.WeChatViewModel
import zqx.rj.com.playandroid.other.constant.Key
import zqx.rj.com.playandroid.other.context.UserContext
import zqx.rj.com.playandroid.other.context.callback.login.LoginSucState
import zqx.rj.com.playandroid.other.widget.SpeedLayoutManager

/**
 * author：  HyZhan
 * created： 2018/11/2 16:54
 * desc：    微信公众号 文章
 */
class WxArticleFragment : ArticleListFragment<WeChatViewModel>() {

    @BindViewModel
    lateinit var viewModel: WeChatViewModel

    private var page: Int = 1
    private val uid by lazy { arguments?.getInt(KEY_ID) ?: -1 }

    companion object {
        const val KEY_ID = "id"

        fun newInstance(id: Int): Fragment = WxArticleFragment().apply {
            arguments = Bundle().apply { putInt(KEY_ID, id) }
        }
    }

    override fun initData() {
        super.initData()
        page = 1
        viewModel.getWxArticle(uid, page)
    }

    override fun onRefreshData() {
        page = 1
        viewModel.getWxArticle(uid, page)
    }

    override fun onLoadMoreData() {
        viewModel.getWxArticle(uid, ++page)
    }

    override fun dataObserver() {
        super.dataObserver()

        viewModel.wxArticleData.observe(this, Observer {
            addData(it.datas)
        })
    }
}