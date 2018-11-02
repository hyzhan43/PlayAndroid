package zqx.rj.com.playandroid.article.view

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivtiy
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.article.data.adapter.ArticleAdapter
import zqx.rj.com.playandroid.article.data.bean.Article
import zqx.rj.com.playandroid.article.vm.ArticleViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 19:22
 * desc：    文章列表 基类  (封装了 文章列表)
 */
abstract class ArticleListFragment<T : ArticleViewModel<*>>
    : LifecycleFragment<T>(), CollectListener {

    protected lateinit var mArticleAdapter: ArticleAdapter

    private val mArticleData: List<Article> by lazy { getArticleData() }

    override fun getLayoutId(): Int = R.layout.fragment_article_list

    override fun initView() {
        super.initView()

        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mArticleAdapter = ArticleAdapter(R.layout.article_item, null)
        mRvArticle.adapter = mArticleAdapter

        // item 点击
        mArticleAdapter.setOnItemClickListener { _, _, position ->
            if (mArticleData.isNotEmpty()) {
                val article = mArticleData[position]
                startActivity<WebViewActivtiy>("link" to article.link,
                        "title" to article.title)
            }
        }

        // 收藏 按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }
    }

    // 获取文章数据
    abstract fun getArticleData(): List<Article>

    fun loadDataSuc() {

        // 如果没有更多数据 则 adapter 加载完毕
        if (mArticleData.isEmpty()) {
            mArticleAdapter.loadMoreEnd()
        } else {
            // 否则 添加数据
            mArticleAdapter.addData(mArticleData)
            mArticleAdapter.loadMoreComplete()
        }
    }

    // 点击收藏 回调
    override fun collect(position: Int) {

        // 获取当前文章的 收藏状态
        val state = mArticleData[position].collect

        mArticleData[position].collect = !state
        mArticleAdapter.notifyDataSetChanged()

        // 文章 id
        val id = mArticleData[position].id

        // 发起 收藏/取消收藏  请求
        if (state) mViewModel.unCollect(id) else mViewModel.collect(id)
    }
}