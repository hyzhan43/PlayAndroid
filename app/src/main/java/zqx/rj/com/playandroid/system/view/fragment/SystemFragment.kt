package zqx.rj.com.playandroid.system.view.fragment

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_system.*
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.system.data.adapter.TopTreeAdapter
import zqx.rj.com.playandroid.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.system.vm.SystemViewModel

/**
 * author：  HyZhan
 * created： 2018/10/22 18:55
 * desc：    TODO
 */
class SystemFragment : LifecycleFragment<SystemViewModel>() {

    private val titles by lazy { arrayListOf<String>() }
    private val fragments by lazy { arrayListOf<Fragment>() }

    override fun getLayoutId(): Int = R.layout.fragment_system

    override fun initView() {
        super.initView()
        // 关联 viewPage 与 TabLayout
        mTlTopTree.setupWithViewPager(mVpContent)
    }

    override fun initData() {
        super.initData()

        mViewModel.getTree()
    }

    override fun dataObserver() {
        mViewModel.mTreeData.observe(this, Observer {
            it?.let { initSystemData(it.data) }
        })

    }

    private fun initSystemData(data: List<TopTreeRsp>) {

        initTitle(data)
        initFragment(data)

        mVpContent.adapter = TopTreeAdapter(childFragmentManager, titles, fragments)
    }

    private fun initTitle(topTreeList: List<TopTreeRsp>) {
        for (topTree in topTreeList) {
            titles.add(topTree.name)
        }
    }

    private fun initFragment(topTreeList: List<TopTreeRsp>): List<Fragment> {

        // 一级菜单
        for (topTreeRsp in topTreeList) {

            val ids = arrayListOf<Int>()
            val secondTreeTitles = arrayListOf<String>()

            // 二级菜单
            for (child in topTreeRsp.children) {
                ids.add(child.id)
                secondTreeTitles.add(child.name)
            }

            fragments.add(SysArticleFragment.getNewInstance(ids, secondTreeTitles))
        }
        return fragments
    }
}