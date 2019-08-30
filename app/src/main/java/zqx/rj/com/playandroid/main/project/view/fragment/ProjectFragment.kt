package zqx.rj.com.playandroid.main.project.view.fragment

import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import com.zhan.mvvm.mvvm.LifecycleFragment
import kotlinx.android.synthetic.main.fragment_project.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.project.adapter.ViewPagerAdapter
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.main.project.vm.ProjectViewModel

/**
 * author：  HyZhan
 * created： 2018/10/27 16:11
 * desc：    TODO
 */
class ProjectFragment : LifecycleFragment<ProjectViewModel>() {

    private lateinit var mAdapter: ViewPagerAdapter
    private val titles by lazy { ArrayList<String>() }
    private val fragments by lazy { ArrayList<Fragment>() }

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun initView() {
        super.initView()

        mTlCategory.setupWithViewPager(mVpFragments)
    }

    override fun initData() {
        super.initData()

        // 获取项目 分类数据
        viewModel.getProjectTree()
    }

    override fun dataObserver() {
        viewModel.projectTreeData.observe(this, Observer {
            initTitles(it)
            initFragment(it)
        })
    }

    private fun initTitles(dataList: List<ProjectTreeRsp>) {
        dataList.map { titles.add(it.name) }
    }

    private fun initFragment(dataList: List<ProjectTreeRsp>) {
        dataList.map { fragments.add(ProjectTabFragment.getNewInstance(it.id)) }

        mAdapter = ViewPagerAdapter(titles, fragments, childFragmentManager)
        mVpFragments.adapter = mAdapter
    }
}