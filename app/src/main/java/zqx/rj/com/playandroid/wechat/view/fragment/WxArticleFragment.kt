package zqx.rj.com.playandroid.wechat.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.wechat.vm.WeChatViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 16:54
 * desc：    微信公众号
 */
class WxArticleFragment : ArticleListFragment<WeChatViewModel>() {

    private var page: Int = 1
    private val uid: Int by lazy { arguments?.getInt("id") ?: -1 }

    companion object {
        fun getNewInstance(id: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("id", id)

            val fragment = WxArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.getWxArticle(uid, page)
    }

    override fun onLoadMoreData() {
        mViewModel.getWxArticle(uid, ++page)
    }

    override fun dataObserver() {
        super.dataObserver()

        mViewModel.mWxArticleData.observe(this, Observer { response ->
            response?.let {
                mArticleData.addAll(it.data.datas)
                loadDataSuc()
            }
        })
    }
}