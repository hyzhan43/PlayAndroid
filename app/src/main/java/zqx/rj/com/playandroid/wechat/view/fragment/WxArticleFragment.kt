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

    private val page: Int = 1

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

        val id = arguments?.getInt("id") ?: -1
        mViewModel.getWxArticle(id, page)
    }

    override fun dataObserver() {
        super.dataObserver()

        mViewModel.mWxArticleData.observe(this, Observer {
            it?.let {
                mArticleData.addAll(it.data.datas)
                loadDataSuc()
            }
        })
    }
}