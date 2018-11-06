package zqx.rj.com.playandroid.home.view.fragment

import android.arch.lifecycle.Observer
import android.view.View
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.article_item.view.*
import kotlinx.android.synthetic.main.home_headview.view.*
import kotlinx.android.synthetic.main.home_special_item.view.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.common.GlideImageLoader
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.search.view.SearchActivity
import zqx.rj.com.playandroid.WebViewActivtiy
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.view.activity.CommonWebActivity
import zqx.rj.com.playandroid.home.vm.HomeViewModel
import java.util.*


/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    首页
 */
class HomeFragment : ArticleListFragment<HomeViewModel>(){

    private lateinit var mBanner: Banner
    private val urls by lazy { arrayListOf<String>() }
    private val titles by lazy { arrayListOf<String>() }
    private var page = 0

    override fun initView() {
        // 一定要 ！！ (注入 mViewModel)
        super.initView()

        initHeadView()
    }

    private fun initHeadView() {
        val headView = View.inflate(activity, R.layout.home_headview, null)
        // 设置标题
        initTitle(headView)

        mBanner = headView.mBanner
        mBanner.setOnBannerListener { position ->
            startActivity<WebViewActivtiy>("link" to urls[position],
                    "title" to titles[position])
        }

        // 添加 Banner
        mArticleAdapter.addHeaderView(headView)

        mBanner.setImageLoader(GlideImageLoader())
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        mBanner.setDelayTime(3000)
        mBanner.setBannerAnimation(Transformer.DepthPage)
    }

    private fun initTitle(headView: View?) {
        headView?.let {
            it.mIcCommonTitle.mTvTitle.text = getString(R.string.common_title)
            it.mIcNewsArticle.mTvTitle.text = getString(R.string.news_article)
            it.mBtnTools.setOnClickListener { startActivity<SearchActivity>() }
            it.mBtnWebsites.setOnClickListener { startActivity<CommonWebActivity>() }
        }
    }

    override fun initData() {
        // 获取 首页 article
        mViewModel.getArticle(page)

        // 获取 Banner
        mViewModel.getBanner()
    }

    // 处理 repository 回调的数据
    override fun dataObserver() {
        // 调用父类 dataObserver
        super.dataObserver()

        mViewModel.mBannerData.observe(this, Observer {
            it?.let { setBannerData(it.data) }
        })

        mViewModel.mHomeArticleData.observe(this, Observer {
            it?.let {
                mArticleData.addAll(it.data.datas)
                loadDataSuc()
            }
        })
    }

    override fun onLoadMoreData() {
        mViewModel.getArticle(++page)
    }

    private fun setBannerData(bannerList: List<BannerRsp>) {

        val images = ArrayList<String>()

        urls.clear()
        titles.clear()

        for (bannerItem in bannerList) {
            images.add(bannerItem.imagePath)
            titles.add(bannerItem.title)
            urls.add(bannerItem.url)
        }

        mBanner.setImages(images)
        mBanner.setBannerTitles(titles)
        mBanner.start()
    }

}