package zqx.rj.com.playandroid.main.system.view.fragment

import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.base.IFragment
import com.zhan.mvvm.mvvm.IMvmFragment
import kotlinx.android.synthetic.main.fragment_system.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.system.adapter.TopTreeAdapter
import zqx.rj.com.playandroid.main.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.main.system.vm.SystemViewModel

/**
 * author：  HyZhan
 * created： 2018/10/22 18:55
 * desc：    体系模块
 */
class SystemFragment : Fragment(), IMvmFragment {

    @BindViewModel
    lateinit var viewModel: SystemViewModel

    override fun getLayoutId(): Int = R.layout.fragment_system

    override fun initView() {
        super.initView()
        // 关联 viewPage 与 TabLayout
        mTlTopTree.setupWithViewPager(mVpContent)
    }

    override fun initData() {
        super.initData()
        viewModel.getTree()
    }

    override fun dataObserver() {
        viewModel.topTreeLiveData.observe(this, Observer {
            mVpContent.adapter = TopTreeAdapter(childFragmentManager, it.titles, it.fragments)
        })
    }
}