package zqx.rj.com.playandroid.project.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_project_tab.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivity
import zqx.rj.com.playandroid.project.data.adapter.ProjectTabAdapter
import zqx.rj.com.playandroid.project.data.bean.Project
import zqx.rj.com.playandroid.project.vm.ProjectViewModel

/**
 * author：  HyZhan
 * created： 2018/10/27 17:42
 * desc：    项目模块
 */
class ProjectTabFragment : LifecycleFragment<ProjectViewModel>() {

    private var page: Int = 1

    private val mAdapter: ProjectTabAdapter by lazy {
        ProjectTabAdapter(R.layout.project_item, null)
    }

    companion object {
        fun getNewInstance(id: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("id", id)
            val projectTabFragment = ProjectTabFragment()
            projectTabFragment.arguments = bundle
            return projectTabFragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_project_tab

    override fun initView() {
        super.initView()

        mRvProject.layoutManager = GridLayoutManager(activity, 2)
        mRvProject.adapter = mAdapter

        // 设置 下拉刷新 loading 颜色
        mSrlRefresh.setColorSchemeResources(R.color.colorPrimary)
        mSrlRefresh.setOnRefreshListener { refreshRvProject() }

        mAdapter.setOnItemClickListener { _, _, position ->

            val project = mAdapter.getItem(position)

            project?.let {
                startActivity<WebViewActivity>("link" to project.link,
                        "title" to project.title)
            }
        }

        mAdapter.setEnableLoadMore(true)
        // 加载更多
        mAdapter.setOnLoadMoreListener({ getProjects(++page) }, mRvProject)
    }

    private fun refreshRvProject() {
        page = 1
        getProjects(page)
    }

    override fun initData() {
        super.initData()
        page = 1
        getProjects(page)
    }

    private fun getProjects(page: Int) {
        arguments?.getInt("id")?.let {
            mViewModel.getProjects(page, it)
        }
    }


    override fun dataObserver() {
        mViewModel.mProjectsData.observe(this, Observer { response ->
            response?.data?.let { addData(it.datas) }
        })
    }

    private fun addData(projects: List<Project>) {
        // 如果为空的话，就直接 显示加载完毕
        if (projects.isEmpty()) {
            mAdapter.loadMoreEnd()
            return
        }

        // 如果是 下拉刷新 直接设置数据
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            mAdapter.setNewData(projects)
            mAdapter.loadMoreComplete()
            return
        }

        // 否则 添加新数据
        mAdapter.addData(projects)
        mAdapter.loadMoreComplete()
    }
}