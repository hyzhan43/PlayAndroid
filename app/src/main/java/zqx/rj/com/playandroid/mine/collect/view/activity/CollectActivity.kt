package zqx.rj.com.playandroid.mine.collect.view.activity

import androidx.lifecycle.Observer
import android.view.View
import kotlinx.android.synthetic.main.common_bar.view.*
import kotlinx.android.synthetic.main.layout_article_list.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.view.ArticleListActivity
import zqx.rj.com.playandroid.mine.collect.vm.CollectViewModel
import java.util.stream.Collectors


/**
 * author：  HyZhan
 * created： 2018/10/24 19:10
 * desc：    我的收藏界面
 */
class CollectActivity : ArticleListActivity<CollectViewModel>() {

    private var current: Int = -1

    private var page = 0

    override fun initView() {
        val headView = View.inflate(this, R.layout.layout_toolbar, null)
        mLlContent.addView(headView, 0)
        super.initView()

        toolbarTitle = getString(R.string.mine_collect)
    }

    override fun initData() {
        super.initData()

        page = 0
        // 获取收藏的文章
        viewModel.getCollectArticle(page)
    }

    override fun dataObserver() {

        // 获取 收藏数据
        viewModel.collectArticleData.observe(this, Observer {
            buildCollectData(it.datas)
        })

        // 取消收藏
        viewModel.requestCollectData.observe(this, Observer {
            // 同步 recyclerView
            mArticleAdapter.remove(current)
        })
    }

    private fun buildCollectData(articles: List<Article>) {
        // 全部设置为 已收藏 状态
        addData(articles.onEach { article -> article.collect = true }
                .toList())
    }

    override fun onRefreshData() {
        page = 0
        viewModel.getCollectArticle(page)
    }

    override fun onLoadMoreData() {
        viewModel.getCollectArticle(++page)
    }

    // 覆盖父类！！因为调用接口不一样
    //  点击 ♥ 取消收藏 前提是登录成功
    override fun collect(position: Int) {

        val article = mArticleAdapter.getItem(position)

        article?.let {
            current = position
            // 文章 id
            viewModel.unMyCollect(it.id, it.originId)
        }
    }

    override fun onBackPressed() = finish()
}