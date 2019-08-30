package zqx.rj.com.playandroid.main.system.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.zhan.mvvm.constant.Const
import kotlinx.android.synthetic.main.fragment_system_article.*
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.main.system.vm.SystemViewModel
import zqx.rj.com.playandroid.other.constant.Key

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
    private val titles: ArrayList<String>? by lazy { arguments?.getStringArrayList(Key.TITLES) }
    private val ids: ArrayList<Int>? by lazy { arguments?.getIntegerArrayList(Key.IDS) }

    override fun getLayoutId(): Int = R.layout.fragment_system_article

    companion object {
        fun getNewInstance(ids: ArrayList<Int>, titles: ArrayList<String>): Fragment {
            val bundle = Bundle()
            bundle.putIntegerArrayList(Key.IDS, ids)
            bundle.putStringArrayList(Key.TITLES, titles)
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

        page = 0
        // 默认获取 一级菜单 二级菜单 第一个
        viewModel.getArticle(getCurrentCid(0), page)
    }

    override fun dataObserver() {
        // 调用父类 dataObserver
        super.dataObserver()

        viewModel.treeArticleData.observe(this, Observer {
            if (flag) {
                // 如果是切换了 tab  就重新设置 新的数据
                // 后续的 加载更多就直接 添加数据
                // 例如  开发环境 -> AndroidStudio 相关 切换至 gradle 就重新设置数据
                mArticleAdapter.setNewData(it.datas)
                flag = false
            } else {
                addData(it.datas)
            }
        })
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
        return ids?.let {
            // 判断 是否有 数据
            if (it.isNotEmpty()) it[position] else -1
        } ?: -1
    }
}