package zqx.rj.com.playandroid.common.article.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhan.ktwing.ext.startActivity
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.layout_article_list.*
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.other.context.UserContext
import zqx.rj.com.playandroid.common.article.adapter.ArticleAdapter
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * created： 2018/11/6 14:57
 * desc：    TODO
 */
abstract class ArticleListActivity<T : ArticleViewModel<*>> : LifecycleActivity<T>(),
        CollectListener {

    // 文章是否 收藏 状态
    private var state: Boolean = false
    // 点击后 当前文章的 位置
    private var current: Int = -1

    protected val mArticleAdapter by lazy { ArticleAdapter(R.layout.article_item, null) }

    override fun getLayoutId(): Int = R.layout.layout_article_list

    override fun initView() {
        super.initView()

        // 初始化 SwipeRefreshLayout
        initRefresh()

        // 初始化 article
        initArticleRecyclerView()
    }

    private fun initRefresh() {
        // 设置 下拉刷新 loading 颜色
        mSrlRefresh.setColorSchemeResources(R.color.colorPrimary)
        mSrlRefresh.setOnRefreshListener { onRefreshData() }
    }

    private fun initArticleRecyclerView() {
        mArticleAdapter.setEnableLoadMore(true)
        mRvArticle.layoutManager = LinearLayoutManager(this)
        mRvArticle.adapter = mArticleAdapter
    }

    override fun initListener() {
        super.initListener()

        // item 点击
        mArticleAdapter.setOnItemClickListener { _, _, position ->
            mArticleAdapter.data[position]?.let {
                startActivity<WebViewActivity>(Key.LINK to it.link, Key.TITLE to it.title)
            }
        }

        // 收藏 按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            UserContext.collect(this, position, this)
        }


        // 上拉加载更多
        mArticleAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)
    }

    /**
     *  上拉加载更多
     */
    open fun onLoadMoreData() {}

    /**
     *  下拉刷新
     */
    open fun onRefreshData() {}

    /**
     *  加载数据, 并更新 SwipeRefreshLayout, RecyclerView 状态
     */
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
        viewModel.collectData.observe(this, Observer {

            mArticleAdapter.data[current]?.let {
                // 更新 RecyclerView  ♥ 型状态
                it.collect = !state
                mArticleAdapter.refreshNotifyItemChanged(current)
            }
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
            if (state) viewModel.unCollect(it.id) else viewModel.collect(it.id)
        }
    }
}