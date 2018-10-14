package zqx.rj.com.playandroid.home.view.fragment

import android.arch.lifecycle.Observer
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.common.GlideImageLoader
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.vm.HomeViewModel
import java.util.ArrayList

/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    首页
 */
class HomeFragment : LifecycleFragment<HomeViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        // 获取 banner 数据
        mViewModel.getBanner()
    }

    // 监听数据变化
    override fun dataObserver() {

        // 处理 repository 回调的数据
        mViewModel.getBannerData().observe(this, Observer {
            it?.data?.let {
                initBanner(it)
            }
        })
    }

    private fun initBanner(bannerList: List<BannerRsp>) {

        val images = ArrayList<String>()
        val titles = ArrayList<String>()

        for (bannerItem in bannerList) {
            images.add(bannerItem.imagePath)
            titles.add(bannerItem.title)
        }

        mBanner.setImageLoader(GlideImageLoader())
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        mBanner.setImages(images)
        mBanner.setBannerTitles(titles)
        mBanner.setDelayTime(2000)
        mBanner.setBannerAnimation(Transformer.DepthPage)
        mBanner.start()
    }
}