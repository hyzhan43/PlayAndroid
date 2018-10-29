package zqx.rj.com.playandroid.mine.view.activity

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.commom_bar.view.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.callback.EmptyCallback
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.mvvm.state.callback.CollectState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.WebViewActivtiy
import zqx.rj.com.playandroid.mine.data.adapter.CollectAdapter
import zqx.rj.com.playandroid.mine.data.bean.Article
import zqx.rj.com.playandroid.mine.vm.MineViewModel

/**
 * author：  HyZhan
 * created： 2018/10/24 19:10
 * desc：    我的收藏界面
 */
class CollectActivity : LifecycleActivity<MineViewModel>(), CollectListener {

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

        // ♥ 型按钮
        mCollectAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(this, position, this)
        }

        mRefresh.setOnRefreshListener { mViewModel.getCollectArticle() }
    }

    override fun initData() {
        super.initData()

        mViewModel.getCollectArticle()
    }

    override fun dataObserver() {

        // 获取 收藏数据
        mViewModel.mCollectData.observe(this, Observer {
            it?.let { setCollectArticle(it.data.datas) }
        })

        // 发起收藏 或者 取消收藏
        mViewModel.mRequestCollectData.observe(this, Observer {
            // TODO
        })
    }

    private fun setCollectArticle(articles: List<Article>) {

        if (articles.isNotEmpty()) {
            mArticleData = articles
            mCollectAdapter.setNewData(articles)
        } else {
            Log.d("LST", "111111")
            loadService.showCallback(EmptyCallback::class.java)
        }

        if (mRefresh.isRefreshing) mRefresh.isRefreshing = false
    }

    // 点击 ♥ 取消收藏 前提是登录成功
    override fun collect(position: Int) {

        if (mArticleData.isNotEmpty()) {

            // 收藏 id
            val id = mArticleData[position].id
            // 文章 id
            val originId = mArticleData[position].originId
            mViewModel.unCollect(id, originId)

            // 同步 recyclerView
            mCollectAdapter.remove(position)

            // 更新 主页面
            CollectState.notifyCollectState(originId)
        }
    }

    override fun onBackPressed() = finish()
}