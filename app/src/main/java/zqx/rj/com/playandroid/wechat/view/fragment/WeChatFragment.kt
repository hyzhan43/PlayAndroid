package zqx.rj.com.playandroid.wechat.view.fragment

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_wechat.*
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.wechat.data.adapter.WxNameAdapter
import zqx.rj.com.playandroid.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.wechat.vm.WeChatViewModel

/**
 * author：  HyZhan
 * created： 2018/11/2 16:28
 * desc：    微信公众号
 */
class WeChatFragment : LifecycleFragment<WeChatViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_wechat

    override fun initView() {
        super.initView()

        // 关联 viewPage 和 tabLayout
        mTlWxName.setupWithViewPager(mVpContent)
    }

    override fun initData() {
        super.initData()

        mViewModel.getWeChatName()
    }


    override fun dataObserver() {
        mViewModel.mWeChatNameData.observe(this, Observer {
            it?.let { initWxArticle(it.data) }
        })
    }

    private fun initWxArticle(dataList: List<WeChatNameRsp>) {

        val titles = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()

        for (data in dataList) {
            titles.add(data.name)
            fragments.add(WxArticleFragment.getNewInstance(data.id))
        }

        mVpContent.adapter = WxNameAdapter(childFragmentManager, titles, fragments)
    }
}