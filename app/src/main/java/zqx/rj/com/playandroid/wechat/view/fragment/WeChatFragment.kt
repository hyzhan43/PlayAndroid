package zqx.rj.com.playandroid.wechat.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zhan.mvvm.mvvm.LifecycleFragment
import kotlinx.android.synthetic.main.fragment_wechat.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.wechat.adapter.WxNameAdapter
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

        viewModel.getWeChatName()
    }


    override fun dataObserver() {
        viewModel.weChatNameData.observe(this, Observer {
            initWxArticle(it)
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