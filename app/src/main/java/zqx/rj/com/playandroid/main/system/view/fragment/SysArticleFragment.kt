package zqx.rj.com.playandroid.main.system.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_system_article.*
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.main.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.main.system.vm.SystemViewModel
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * created： 2018/10/22 19:58
 * desc：    体系 文章 fragment
 */
class SysArticleFragment : ArticleListFragment<SystemViewModel>(), CollectListener {

    private var page: Int = 0

    // 当前选中的 tab  索引  默认是 0 选中第一个
    private var tabIndex: Int = 0

    /**
     *  标志位, 用于判断是否切换了 tab
     *  如果是切换了 tab  就重新设置 新的数据
     *  否则  就 添加数据
     */
    private var flag: Boolean = false

    // 获取 初始化 传入的 ids、titles
    private val titles: ArrayList<String>? by lazy { arguments?.getStringArrayList(Key.TITLES) }
    private val ids: ArrayList<Int>? by lazy { arguments?.getIntegerArrayList(Key.IDS) }

    override fun getLayoutId(): Int = R.layout.fragment_system_article

    companion object {
        fun newInstance(ids: ArrayList<Int>, titles: ArrayList<String>) =
            SysArticleFragment().also {
                it.arguments = Bundle().apply {
                    putIntegerArrayList(Key.IDS, ids)
                    putStringArrayList(Key.TITLES, titles)
                }
            }
    }

    override fun initView() {
        super.initView()

        initSecondTreeTab()
    }

    override fun initData() {
        super.initData()

        page = 0
        // 默认获取 一级菜单 二级菜单 第一个
        viewModel.getArticle(getCurrentCid(0), page)
    }

    override fun dataObserver() {
        // 调用父类 dataObserver
        super.dataObserver()

        viewModel.treeArticleData.observe(this, Observer {
            changeTree(it)
        })
    }

    private fun changeTree(it: TreeArticleRsp) {
        if (flag) {
            mArticleAdapter.setNewData(it.datas)
            flag = false
            return
        }

        addData(it.datas)
    }

    override fun onRefreshData() {
        page = 0
        viewModel.getArticle(getCurrentCid(tabIndex), page)
    }

    override fun onLoadMoreData() {
        viewModel.getArticle(getCurrentCid(tabIndex), ++page)
    }

    private fun initSecondTreeTab() {

        titles?.forEach { mTlSecondTree.addTab(mTlSecondTree.newTab().setText(it)) }

        mTlSecondTree.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                flag = true
                tabIndex = tab?.position ?: 0
                viewModel.getArticle(getCurrentCid(tabIndex), 0)
            }
        })
    }

    private fun getCurrentCid(position: Int): Int {
        return ids?.getOrNull(position) ?: -1
    }
}