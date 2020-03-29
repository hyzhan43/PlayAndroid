package zqx.rj.com.playandroid.main.project.view.fragment

import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import kotlinx.android.synthetic.main.fragment_project.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.project.adapter.ViewPagerAdapter
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeData
import zqx.rj.com.playandroid.main.project.vm.ProjectViewModel

/**
 * author：  HyZhan
 * created： 2018/10/27 16:11
 * desc：    TODO
 */
class ProjectFragment : Fragment(), IMvmFragment {

    @BindViewModel
    lateinit var viewModel: ProjectViewModel

    private lateinit var mAdapter: ViewPagerAdapter

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
        viewModel.projectTreeLiveData.observe(this, Observer {
            initFragments(it)
        })
    }

    private fun initFragments(projectTreeData: ProjectTreeData) {

        mAdapter = ViewPagerAdapter(
            projectTreeData.titles,
            projectTreeData.fragments,
            childFragmentManager
        )
        mVpFragments.adapter = mAdapter
    }

}