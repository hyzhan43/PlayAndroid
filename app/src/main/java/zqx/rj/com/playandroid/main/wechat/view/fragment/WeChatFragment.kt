package zqx.rj.com.playandroid.main.wechat.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import kotlinx.android.synthetic.main.fragment_wechat.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.home.view.fragment.HomeFragment
import zqx.rj.com.playandroid.main.wechat.adapter.WxNameAdapter
import zqx.rj.com.playandroid.main.wechat.vm.WeChatViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 16:28
 * desc：    微信公众号
 */
class WeChatFragment : Fragment(), IMvmFragment {

    @BindViewModel
    lateinit var viewModel: WeChatViewModel

    override fun getLayoutId(): Int = R.layout.fragment_wechat

    override fun initView() {
        super.initView()

        // 关联 viewPage 和 tabLayout
        mTlWxName.setupWithViewPager(mVpContent)
    }

    override fun initData() {
        super.initData()

        viewModel.getWeChatName()
    }


    override fun dataObserver() {
        viewModel.weChatLiveData.observe(this, Observer {
            mVpContent.adapter = WxNameAdapter(childFragmentManager, it.titles, it.fragments)
        })
    }
}