package zqx.rj.com.playandroid.system.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import com.kingja.loadsir.callback.SuccessCallback
import kotlinx.android.synthetic.main.fragment_system_article.*
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
import zqx.rj.com.playandroid.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.system.vm.SystemViewModel

/**
 * author：  HyZhan
 * created： 2018/10/22 19:58
 * desc：    体系 文章 fragment
 */
class SysArticleFragment : LifecycleFragment<SystemViewModel>(), CollectListener,
        CollectUpdateListener {

    private var page: Int = 0

    private lateinit var mArticleAdapter: ArticleAdapter

    // 获取 初始化 传入的 ids、titles
    private val titles: ArrayList<String>? by lazy { arguments?.getStringArrayList("titles") }
    private val ids: ArrayList<Int>? by lazy { arguments?.getIntegerArrayList("ids") }

    private lateinit var mArticleData: List<Article>

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
            startActivity<WebViewActivity>("link" to mArticleData[position].link,
                    "title" to mArticleData[position].title)
        }

        // ♥ 型按钮
        mArticleAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }

        initSecondTreeTab()

        // 监听 其他地方 点击收藏后 回调
        CollectState.addListener(this)
    }

    override fun initData() {
        super.initData()

        // 默认获取 一级菜单 二级菜单 第一个
        switchArticle(0)
    }

    override fun dataObserver() {
        mViewModel.mTreeArticleData.observe(this, Observer {
            it?.let { setArticleData(it.data) }

            loadService.showCallback(SuccessCallback::class.java)
        })
    }

    private fun setArticleData(data: TreeArticleRsp) {
        // 跳转 WebActivity 需要用到
        mArticleData = data.datas
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

        // 更新 recyclerView  状态
        val collect = notifyRecyclerViewCollectState(position)

        // 文章 id
        val id = mArticleData[position].id

        if (collect) mViewModel.unCollect(id) else mViewModel.collect(id)
    }

    override fun updateState(id: Int) {
        var position = 0

        for ((index, value) in mArticleAdapter.data.withIndex()) {
            if (value.id == id) {
                position = index
                break
            }
        }

        notifyRecyclerViewCollectState(position)
    }

    // 更新 RecyclerView  ♥ 状态
    private fun notifyRecyclerViewCollectState(position: Int): Boolean {
        val collect = mArticleAdapter.data[position].collect

        // 同步 recyclerView
        mArticleAdapter.data[position].collect = !collect
        mArticleAdapter.notifyDataSetChanged()

        return collect
    }
}