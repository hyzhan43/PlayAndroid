package zqx.rj.com.playandroid.common.article.view

import android.arch.lifecycle.Observer
import android.util.Log
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.common.SpeedLayoutManager
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
    : LifecycleFragment<T>(), LoginSucListener, CollectListener {

    // 文章是否 收藏 状态
    private var state: Boolean = false
    // 点击后 当前文章的 位置
    private var current: Int = 0

    protected lateinit var mArticleAdapter: ArticleAdapter

    override fun getLayoutId(): Int = R.layout.fragment_article_list

    override fun initView() {
        super.initView()

        // 初始化 SwipeRefreshLayout
        initRefresh()

        // 第二个参数 设置滑动速度, 默认是 25.0 太慢所以加快
        mRvArticle.layoutManager = SpeedLayoutManager(context, 10f)
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

    private fun initRefresh() {
        // 设置 下拉刷新 loading 颜色
        mSrlRefresh.setColorSchemeResources(R.color.colorPrimary)
        mSrlRefresh.setOnRefreshListener { onRefreshData() }
    }

    /**
     *  下拉刷新
     */
    abstract fun onRefreshData()

    /**
     *  加载更多数据
     */
    abstract fun onLoadMoreData()

    fun addData(articleList: List<Article>) {

        // 如果为空的话，就直接 显示加载完毕
        if (articleList.isEmpty()) {
            mArticleAdapter.loadMoreEnd()
            return
        }

        // 如果是 下拉刷新 直接设置数据
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            mArticleAdapter.setNewData(articleList)
            mArticleAdapter.loadMoreComplete()
            return
        }

        // 否则 添加新数据
        mArticleAdapter.addData(articleList)
        mArticleAdapter.loadMoreComplete()
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
        })
    }

    // 发起收藏
    override fun collect(position: Int) {
        Log.d("LST", "position=$position")
        val article = mArticleAdapter.getItem(position)

        article?.let {
            current = position

            // 获取当前文章的 收藏状态
            state = it.collect

            // 发起 收藏/取消收藏  请求
            if (state) mViewModel.unCollect(it.id) else mViewModel.collect(it.id)
        }
    }

    override fun success(username: String, collectIds: List<Int>?) {

        collectIds?.let {
            it.forEach { id ->
                mArticleAdapter.data.forEach { article ->
                    // 更新文章收藏状态
                    if (article.id == id) {
                        article.collect = true
                    }
                }
            }
        } ?: let {
            mArticleAdapter.data.forEach { article ->
                // 退出登录后  更新文章收藏状态
                if (article.collect) {
                    article.collect = false
                }
            }
        }

        mArticleAdapter.notifyDataSetChanged()
    }

    /**
     *  双击 toolbar  返回 顶部
     */
    fun moveToTop() {
        mRvArticle.smoothScrollToPosition(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSucState.removeListener(this)
    }
}