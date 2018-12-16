package zqx.rj.com.playandroid.system.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_system_article.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivity
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.common.article.data.adapter.ArticleAdapter
import zqx.rj.com.playandroid.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.system.vm.SystemViewModel

/**
 * author：  HyZhan
 * created： 2018/10/22 19:58
 * desc：    体系 文章 fragment
 */
class SysArticleFragment : LifecycleFragment<SystemViewModel>(), CollectListener {

    private var page: Int = 0

    private lateinit var mArticleAdapter: ArticleAdapter

    private var current: Int = -1

    // 获取 初始化 传入的 ids、titles
    private val titles: ArrayList<String>? by lazy { arguments?.getStringArrayList("titles") }
    private val ids: ArrayList<Int>? by lazy { arguments?.getIntegerArrayList("ids") }

    override fun getLayoutId(): Int = R.layout.fragment_system_article

    companion object {
        fun getNewInstance(ids: ArrayList<Int>, titles: ArrayList<String>): Fragment {
            val bundle = Bundle()
            bundle.putIntegerArrayList("ids", ids)
            bundle.putStringArrayList("titles", titles)
            val articleFragment = SysArticleFragment()
            articleFragment.arguments = bundle
            return articleFragment
        }
    }

    override fun initView() {
        super.initView()

        mRvArticle.layoutManager = LinearLayoutManager(context)
        mArticleAdapter = ArticleAdapter(R.layout.article_item, null)
        mRvArticle.adapter = mArticleAdapter

        // item
        mArticleAdapter.setOnItemClickListener { _, _, position ->
            val article = mArticleAdapter.getItem(position)
            article?.let {
                startActivity<WebViewActivity>("link" to it.link,
                        "title" to it.title)
            }
        }

        // ♥ 型按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }

        initSecondTreeTab()
    }

    override fun initData() {
        super.initData()

        // 默认获取 一级菜单 二级菜单 第一个
        switchArticle(0)
    }

    override fun dataObserver() {
        mViewModel.mTreeArticleData.observe(this, Observer { response ->
            response?.let { setArticleData(it.data) }
        })

        mViewModel.mCollectData.observe(this, Observer {
            val article = mArticleAdapter.getItem(current)

            article?.let {
                // 更新 RecyclerView  ♥ 型状态
                it.collect = !it.collect
                mArticleAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setArticleData(data: TreeArticleRsp) {
        mArticleAdapter.setNewData(data.datas)
    }

    private fun initSecondTreeTab() {

        titles?.forEach { mTlSecondTree.addTab(mTlSecondTree.newTab().setText(it)) }

        mTlSecondTree.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                switchArticle(tab?.position ?: 0)
            }
        })
    }

    private fun switchArticle(position: Int) {
        ids?.let {
            // 判断 是否有 数据
            val cid = if (it.isNotEmpty()) it[position] else -1
            mViewModel.getArticle(cid, page)
        }
    }


    // 点击收藏 回调
    override fun collect(position: Int) {

        val article = mArticleAdapter.getItem(position)

        article?.let {
            if (it.collect) mViewModel.unCollect(it.id) else mViewModel.collect(it.id)

            current = position
        }
    }
}