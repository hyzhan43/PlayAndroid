package zqx.rj.com.playandroid.main.project.view.fragment

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.zhan.mvvm.ext.startActivity
import com.zhan.mvvm.mvvm.LifecycleFragment
import kotlinx.android.synthetic.main.fragment_project_tab.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.main.project.adapter.ProjectTabAdapter
import zqx.rj.com.playandroid.main.project.data.bean.Project
import zqx.rj.com.playandroid.main.project.vm.ProjectViewModel
import zqx.rj.com.playandroid.other.constant.Key

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
            bundle.putInt(Key.ID, id)
            return ProjectTabFragment().apply {
                arguments = bundle
            }
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
            mAdapter.data[position]?.let {
                startActivity<WebViewActivity>(Key.LINK to it.link, Key.TITLE to it.title)
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
        arguments?.getInt(Key.ID)?.let {
            viewModel.getProjects(page, it)
        }
    }


    override fun dataObserver() {
        viewModel.projectsData.observe(this, Observer {
            addData(it.datas)
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