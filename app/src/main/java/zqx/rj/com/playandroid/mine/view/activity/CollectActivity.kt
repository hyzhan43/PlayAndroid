package zqx.rj.com.playandroid.mine.view.activity

import android.arch.lifecycle.Observer
import android.view.View
import kotlinx.android.synthetic.main.common_bar.view.*
import zqx.rj.com.mvvm.common.callback.EmptyCallback
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.mvvm.state.callback.collect.CollectState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.view.ArticleListActivity
import zqx.rj.com.playandroid.mine.vm.MineViewModel


/**
 * author：  HyZhan
 * created： 2018/10/24 19:10
 * desc：    我的收藏界面
 */
class CollectActivity : ArticleListActivity<MineViewModel>(), CollectListener {

    private var current: Int = -1

    override fun initView() {
        super.initView()

        initHeadView()
    }

    private fun initHeadView() {

        val headView = View.inflate(this, R.layout.common_bar, null)

        headView.mTvBarTitle.text = getString(R.string.mine_collect)
        headView.mIvBack.visibility = View.VISIBLE
        headView.mIvBack.setOnClickListener { finish() }
        headView.mIvSearch.visibility = View.GONE

        mArticleAdapter.addHeaderView(headView)
    }

    override fun initData() {
        super.initData()

        // 获取收藏的文章
        mViewModel.getCollectArticle()
    }

    override fun dataObserver() {

        // 获取 收藏数据
        mViewModel.mCollectArticleData.observe(this, Observer { response ->
            response?.let { setCollectToData(it.data.datas) }
        })

        // 发起收藏 或者 取消收藏
        mViewModel.mRequestCollectData.observe(this, Observer {

            // 更新 主页面
            CollectState.notifyCollectState(mArticleData[current].originId)

            // 同步 recyclerView
            mArticleAdapter.remove(current)
            mArticleData.removeAt(current)

            if (mArticleData.isEmpty())
                showEmpty()
        })
    }

    override fun onLoadMoreData() {
        super.onLoadMoreData()
        mArticleAdapter.loadMoreEnd()
    }


    private fun setCollectToData(articles: List<Article>) {

        // 全部设置为 已收藏
        for (article in articles) {
            article.collect = true
        }

        setCollectArticle(articles)
    }

    private fun setCollectArticle(articles: List<Article>) {
        if (articles.isNotEmpty()) {
            mArticleData.addAll(articles)
            mArticleAdapter.setNewData(articles)
            mArticleAdapter.loadMoreComplete()
        } else {
            mArticleAdapter.loadMoreEnd()
            loadService.showCallback(EmptyCallback::class.java)
        }
    }


    // 点击 ♥ 取消收藏 前提是登录成功
    override fun collect(position: Int) {

        if (mArticleData.isNotEmpty()) {

            current = position

            // 收藏 id
            val id = mArticleData[position].id
            // 文章 id
            val originId = mArticleData[position].originId
            mViewModel.unMyCollect(id, originId)
        }
    }

    override fun onBackPressed() = finish()
}