package zqx.rj.com.playandroid.mine.view.activity

import android.arch.lifecycle.Observer
import android.view.View
import kotlinx.android.synthetic.main.common_bar.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.view.ArticleListActivity
import zqx.rj.com.playandroid.mine.vm.MineViewModel


/**
 * author：  HyZhan
 * created： 2018/10/24 19:10
 * desc：    我的收藏界面
 */
class CollectActivity : ArticleListActivity<MineViewModel>() {

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

        // 取消收藏
        mViewModel.mRequestCollectData.observe(this, Observer {

            // 更新 主页面
            // eventBus.post(mArticleData[current].originId)

            // 同步 recyclerView
            mArticleAdapter.remove(current)
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

        addData(articles)
    }

    // 覆盖父类！！因为调用接口不一样
    //  点击 ♥ 取消收藏 前提是登录成功
    override fun collect(position: Int) {

        val article = mArticleAdapter.getItem(position)

        article?.let {
            current = position
            // 文章 id
            mViewModel.unMyCollect(it.id, it.originId)
        }
    }

    override fun onBackPressed() = finish()
}