package zqx.rj.com.playandroid.wechat.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_wechat_article.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivtiy
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.wechat.data.adapter.ArticleAdapter
import zqx.rj.com.playandroid.wechat.data.bean.WxArticle
import zqx.rj.com.playandroid.wechat.data.bean.WxArticleRsp
import zqx.rj.com.playandroid.wechat.vm.WeChatViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 16:54
 * desc：    TODO
 */
class WxArticleFragment : LifecycleFragment<WeChatViewModel>(), CollectListener {

    private val page: Int = 1
    private lateinit var mArticleData: List<WxArticle>

    private val mAdapter: ArticleAdapter by lazy {
        ArticleAdapter(R.layout.article_item, null)
    }

    override fun getLayoutId(): Int = R.layout.fragment_wechat_article

    companion object {
        fun getNewInstance(id: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("id", id)

            val fragment = WxArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView() {
        super.initView()

        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mRvArticle.adapter = mAdapter

        // ♥ 型 收藏
        mAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }

        // article
        mAdapter.setOnItemClickListener { _, _, position ->
            if (mArticleData.isNotEmpty()) {

                val article = mArticleData[position]
                startActivity<WebViewActivtiy>("link" to article.link,
                        "title" to article.title)
            }
        }
    }

    override fun initData() {
        super.initData()

        val id = arguments?.getInt("id") ?: -1
        mViewModel.getWxArticle(id, page)
    }

    override fun dataObserver() {

        mViewModel.mWxArticleData.observe(this, Observer {
            it?.let { initArticle(it.data) }
        })
    }

    private fun initArticle(data: WxArticleRsp) {
        mArticleData = data.datas
        mAdapter.addData(data.datas)
    }

    override fun collect(position: Int) {

        val collect = mAdapter.data[position].collect

        // 同步 recyclerView
        mAdapter.data[position].collect = !collect
        mAdapter.notifyDataSetChanged()

        // 文章 id
        val id = mArticleData[position].id

        // if (collect) mViewModel.unCollect(id) else mViewModel.collect(id)
    }
}