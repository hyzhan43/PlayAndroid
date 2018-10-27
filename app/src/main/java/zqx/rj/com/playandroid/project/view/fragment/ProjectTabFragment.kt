package zqx.rj.com.playandroid.project.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_project_tab.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.view.activity.WebViewActivtiy
import zqx.rj.com.playandroid.project.data.adapter.ProjectTabAdapter
import zqx.rj.com.playandroid.project.data.bean.Project
import zqx.rj.com.playandroid.project.vm.ProjectViewModel

/**
 * author：  HyZhan
 * created： 2018/10/27 17:42
 * desc：    TODO
 */
class ProjectTabFragment : LifecycleFragment<ProjectViewModel>() {

    private var mPage: Int = 1
    private val mProjectsData by lazy { ArrayList<Project>() }

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

        mSlRefresh.setOnRefreshListener { refreshRvProject() }

        mAdapter.setOnItemClickListener { _, _, position ->
            val project = mProjectsData[position]
            startActivity<WebViewActivtiy>("link" to project.link,
                    "title" to project.title)
        }

        mAdapter.setEnableLoadMore(true)
        // 加载更多
        mAdapter.setOnLoadMoreListener({
            getProjects(++mPage)
        }, mRvProject)
    }

    private fun refreshRvProject() {
        mPage = 1
        mProjectsData.clear()
        mAdapter.data.clear()
        getProjects(mPage)
    }

    override fun initData() {
        super.initData()

        getProjects(mPage)
    }

    private fun getProjects(page: Int) {
        arguments?.getInt("id")?.let {
            mViewModel.getProjects(page, it)
        }
    }


    override fun dataObserver() {
        mViewModel.mProjectsData.observe(this, Observer {
            it?.data?.let {
                if (it.datas.isEmpty()) {
                    mAdapter.loadMoreEnd()
                } else {
                    initRecyclerView(it.datas)
                }
            }
        })
    }

    private fun initRecyclerView(projects: List<Project>) {
        mProjectsData.addAll(projects)
        mAdapter.addData(projects)
        mAdapter.loadMoreComplete()

        if (mSlRefresh.isRefreshing) mSlRefresh.isRefreshing = false
    }
}