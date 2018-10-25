package zqx.rj.com.playandroid.mine.view.activity

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.commom_bar.view.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.view.activity.WebViewActivtiy
import zqx.rj.com.playandroid.mine.data.adapter.CollectAdapter
import zqx.rj.com.playandroid.mine.data.bean.Article
import zqx.rj.com.playandroid.mine.vm.MineViewModel

/**
 * author：  HyZhan
 * created： 2018/10/24 19:10
 * desc：    我的收藏界面
 */
class CollectActivity : LifecycleActivity<MineViewModel>() {

    private lateinit var mCollectAdapter: CollectAdapter
    private lateinit var mArticleData: List<Article>

    override fun getLayoutId(): Int = R.layout.activity_collect

    override fun initView() {
        super.initView()

        mIcBar.mTvBarTitle.text = getString(R.string.mine_collect)
        mIcBar.mIvBack.visibility = View.VISIBLE
        mIcBar.mIvBack.setOnClickListener { finish() }
        mIcBar.mIvSearch.visibility = View.GONE

        mRvCollect.layoutManager = LinearLayoutManager(this)
        mCollectAdapter = CollectAdapter(R.layout.article_item, null)
        mRvCollect.adapter = mCollectAdapter

        mCollectAdapter.setOnItemClickListener { _, _, position ->
            startActivity<WebViewActivtiy>("link" to mArticleData[position].link,
                    "title" to mArticleData[position].title)
        }

        mRefresh.setOnRefreshListener { mViewModel.getCollectArticle() }
    }

    override fun initData() {
        super.initData()

        mViewModel.getCollectArticle()
    }

    override fun dataObserver() {

        mViewModel.mCollectData.observe(this, Observer {
            it?.let { setCollectArticle(it.data.datas) }
        })
    }

    private fun setCollectArticle(articles: List<Article>) {
        mArticleData = articles
        mCollectAdapter.setNewData(articles)

        if (mRefresh.isRefreshing) mRefresh.isRefreshing = false
    }

    override fun onBackPressed() = finish()
}