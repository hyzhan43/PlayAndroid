package zqx.rj.com.playandroid.system.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_system_article.*
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.system.vm.SystemViewModel

/**
 * author：  HyZhan
 * created： 2018/10/22 19:58
 * desc：    体系 文章 fragment
 */
class SysArticleFragment : ArticleListFragment<SystemViewModel>(), CollectListener {

    private var page: Int = 0

    // 当前 收藏 article 索引
    private var current: Int = -1

    // 当前选中的 tab  索引  默认是 0 选中第一个
    private var tabIndex: Int = 0

    /**
     *  标志位, 用于判断是否切换了 tab
     *  如果是切换了 tab  就重新设置 新的数据
     *  否则  就 添加数据
     */
    private var flag: Boolean = false

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

        initSecondTreeTab()
    }

    override fun initData() {
        super.initData()

        // 默认获取 一级菜单 二级菜单 第一个
        mViewModel.getArticle(getCurrentCid(0), 0)
    }

    override fun dataObserver() {
        // 调用父类 dataObserver
        super.dataObserver()

        mViewModel.mTreeArticleData.observe(this, Observer { response ->
            response?.let {
                if (flag) {
                    // 如果是切换了 tab  就重新设置 新的数据
                    // 后续的 加载更多就直接 添加数据
                    // 例如  开发环境 -> AndroidStudio 相关 切换至 gradle 就重新设置数据
                    mArticleAdapter.setNewData(it.data.datas)
                    flag = false
                } else {
                    addData(it.data.datas)
                }
            }
        })
    }

    override fun onRefreshData() {
        mViewModel.getArticle(getCurrentCid(tabIndex), 0)
    }

    override fun onLoadMoreData() {
        mViewModel.getArticle(getCurrentCid(tabIndex), ++page)
    }

    private fun initSecondTreeTab() {

        titles?.forEach { mTlSecondTree.addTab(mTlSecondTree.newTab().setText(it)) }

        mTlSecondTree.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                flag = true
                tabIndex = tab?.position ?: 0
                mViewModel.getArticle(getCurrentCid(tabIndex), 0)
            }
        })
    }

    private fun getCurrentCid(position: Int): Int {
        return ids?.let {
            // 判断 是否有 数据
            if (it.isNotEmpty()) it[position] else -1
        } ?: -1
    }
}