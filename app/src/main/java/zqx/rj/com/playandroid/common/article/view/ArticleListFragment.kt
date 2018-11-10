package zqx.rj.com.playandroid.common.article.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.mvvm.state.callback.CollectState
import zqx.rj.com.mvvm.state.callback.CollectUpdateListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivity
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.common.article.data.adapter.ArticleAdapter
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 19:22
 * desc：    文章列表 基类  (封装了 文章列表)
 */
abstract class ArticleListFragment<T : ArticleViewModel<*>>
    : LifecycleFragment<T>(), CollectListener, CollectUpdateListener {

    // 文章是否 收藏 状态
    private var state: Boolean = false
    // 点击后 当前文章的 位置
    private var current: Int = -1

    protected lateinit var mArticleAdapter: ArticleAdapter

    protected var mArticleData = arrayListOf<Article>()

    override fun getLayoutId(): Int = R.layout.fragment_article_list

    override fun initView() {
        super.initView()

        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mArticleAdapter = ArticleAdapter(R.layout.article_item, null)
        mRvArticle.adapter = mArticleAdapter

        // item 点击
        mArticleAdapter.setOnItemClickListener { _, _, position ->
            if (mArticleData.isNotEmpty()) {
                // 如果 获取不到 对应 article  就返回 null (不然会报 空指针异常)
                val article = mArticleData.getOrNull(position)
                article?.let {
                    startActivity<WebViewActivity>("link" to it.link, "title" to it.title)
                }
            }
        }

        // 收藏 按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }

        mArticleAdapter.setEnableLoadMore(true)
        // 上拉加载更多
        mArticleAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)

        // 监听 其他地方 点击收藏后 回调
        CollectState.addListener(this)
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


    // 更新 主页面的  article 心型状态
    // 例如 在 我的收藏点击了  取消收藏   这时候 就通知主页面 更新
    override fun updateState(id: Int) {

        var position = 0

        for ((index, value) in mArticleAdapter.data.withIndex()) {
            if (value.id == id) {
                position = index
                break
            }
        }

        val isCollect = mArticleAdapter.data[position].collect
        mArticleAdapter.data[position].collect = !isCollect
        mArticleAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        CollectState.removeListener(this)
    }
}