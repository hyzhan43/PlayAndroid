package zqx.rj.com.playandroid.common.article.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.mvvm.state.callback.CollectState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivtiy
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.common.article.data.adapter.ArticleAdapter
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel

/**
 * author：  HyZhan
 * created： 2018/11/6 14:57
 * desc：    TODO
 */
abstract class ArticleListActivity<T : ArticleViewModel<*>>
    : LifecycleActivity<T>(), CollectListener {

    // 文章是否 收藏 状态
    private var state: Boolean = false
    // 点击后 当前文章的 位置
    private var current: Int = -1

    protected var isLoadMore: Boolean = true

    protected lateinit var mArticleAdapter: ArticleAdapter

    protected var mArticleData = arrayListOf<Article>()

    override fun getLayoutId(): Int = R.layout.fragment_article_list

    override fun initView() {
        super.initView()

        mRvArticle.layoutManager = LinearLayoutManager(this)
        mArticleAdapter = ArticleAdapter(R.layout.article_item, null)
        mRvArticle.adapter = mArticleAdapter

        // item 点击
        mArticleAdapter.setOnItemClickListener { _, _, position ->
            if (mArticleData.isNotEmpty()) {
                // 如果 获取不到 对应 article  就返回 null (不然会报 空指针异常)
                val article = mArticleData.getOrNull(position)
                article?.let {
                    startActivity<WebViewActivtiy>("link" to it.link,
                            "title" to it.title)
                }
            }
        }

        // 收藏 按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(this, position, this)
        }

        mArticleAdapter.setEnableLoadMore(isLoadMore)
        // 上拉加载更多
        mArticleAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)
    }


    // 加载更多数据
    open fun onLoadMoreData() {}

    fun loadDataSuc() {

        // 如果没有更多数据 则 adapter 加载完毕
        if (mArticleData.isEmpty()) {
            mArticleAdapter.loadMoreEnd()
        } else {
            // 否则 添加数据
            mArticleAdapter.setNewData(mArticleData)
            mArticleAdapter.loadMoreComplete()
        }
    }

    override fun dataObserver() {

        // 收藏成功回调
        mViewModel.mCollectData.observe(this, Observer {
            // 更新 RecyclerView  ♥ 型状态
            mArticleData[current].collect = !state
            mArticleAdapter.notifyDataSetChanged()

            // 更新 主页面 心型状态
            CollectState.notifyCollectState(mArticleData[current].id)
        })
    }

    // 点击收藏 回调
    override fun collect(position: Int) {

        // 获取当前文章的 收藏状态
        state = mArticleData[position].collect
        current = position

        // 文章 id
        val id = mArticleData[position].id

        // 发起 收藏/取消收藏  请求
        if (state) mViewModel.unCollect(id) else mViewModel.collect(id)
    }
}