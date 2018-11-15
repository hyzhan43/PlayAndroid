package zqx.rj.com.playandroid.common.article.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.mvvm.state.callback.login.LoginSucListener
import zqx.rj.com.mvvm.state.callback.login.LoginSucState
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
    : LifecycleFragment<T>(),
        LoginSucListener,
        CollectListener {

    // 文章是否 收藏 状态
    private var state: Boolean = false
    // 点击后 当前文章的 位置
    private var current: Int = -1

    protected lateinit var mArticleAdapter: ArticleAdapter

    override fun getLayoutId(): Int = R.layout.fragment_article_list

    override fun initView() {
        super.initView()

        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mArticleAdapter = ArticleAdapter(R.layout.article_item, null)
        mRvArticle.adapter = mArticleAdapter

        // item 点击
        mArticleAdapter.setOnItemClickListener { _, _, position ->

            val article = mArticleAdapter.getItem(position)

            article?.let {
                startActivity<WebViewActivity>("link" to it.link,
                        "title" to it.title)
            }
        }

        // 收藏 按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }

        mArticleAdapter.setEnableLoadMore(true)
        // 上拉加载更多
        mArticleAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)

        // 监听登录成功
        LoginSucState.addListener(this)
    }


    // 加载更多数据
    open fun onLoadMoreData() {}

    fun addData(articleList: List<Article>) {
        if (articleList.isEmpty()) {
            mArticleAdapter.loadMoreEnd()
        } else {
            mArticleAdapter.addData(articleList)
            mArticleAdapter.loadMoreComplete()
        }
    }

    override fun dataObserver() {
        // 收藏成功回调
        mViewModel.mCollectData.observe(this, Observer {

            val article = mArticleAdapter.getItem(current)

            article?.let {
                // 更新 RecyclerView  ♥ 型状态
                it.collect = !state
                mArticleAdapter.notifyDataSetChanged()
            }

//            通知所有界面更新 ♥型状态
//            eventBus.post(CollectUpdateEvent(article.id))
        })
    }

    // 发起收藏
    override fun collect(position: Int) {

        val article = mArticleAdapter.getItem(position)

        article?.let {
            current = position

            // 获取当前文章的 收藏状态
            state = it.collect

            // 发起 收藏/取消收藏  请求
            if (state) mViewModel.unCollect(it.id) else mViewModel.collect(it.id)
        }
    }

    // 更新 主页面的  article 心型状态
    // 例如 在 我的收藏点击了  取消收藏   这时候 就通知主页面 更新
    fun onCollectUpdateEvent() {
//        Log.d("LST", "ArticleListFragment 收到消息啦")
//        var position = 0
//
//        for ((index, value) in mArticleAdapter.data.withIndex()) {
//            if (value.id == event.id) {
//                position = index
//                break
//            }
//        }
//
//        val article = mArticleAdapter.data.getOrNull(position)
//
//        article?.let {
//            Log.d("LST", "onClick")
//            val isCollect = it.collect
//            it.collect = !isCollect
//            mArticleAdapter.notifyDataSetChanged()
//        }
    }


    override fun success(username: String, collectIds: List<Int>) {
        // 表示没有任何收藏文章
        if (collectIds.isNotEmpty()) {
            collectIds.forEach { id ->
                mArticleAdapter.data.forEach { article ->
                    // 更新文章收藏状态
                    if (article.id == id) {
                        article.collect = true
                    }
                }
            }
        }

        mArticleAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSucState.removeListener(this)
    }
}