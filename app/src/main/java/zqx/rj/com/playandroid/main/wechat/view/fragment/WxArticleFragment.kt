package zqx.rj.com.playandroid.main.wechat.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zhan.mvvm.annotation.BindViewModel
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.main.wechat.vm.WeChatViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 16:54
 * desc：    微信公众号 文章
 */
class WxArticleFragment : ArticleListFragment() {

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

    override fun getArticleViewModel(): ArticleViewModel<*> = viewModel

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