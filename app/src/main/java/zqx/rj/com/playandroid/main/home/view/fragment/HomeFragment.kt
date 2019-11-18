package zqx.rj.com.playandroid.main.home.view.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.zhan.ktwing.ext.startActivity
import kotlinx.android.synthetic.main.article_item.view.*
import kotlinx.android.synthetic.main.home_headview.*
import kotlinx.android.synthetic.main.home_headview.view.*
import kotlinx.android.synthetic.main.home_special_item.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.common.article.view.ArticleListFragment
import zqx.rj.com.playandroid.main.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.main.home.view.activity.CommonWebActivity
import zqx.rj.com.playandroid.main.home.vm.HomeViewModel
import zqx.rj.com.playandroid.other.constant.Key
import zqx.rj.com.playandroid.other.widget.GlideImageLoader
import java.util.*


/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    首页
 */
class HomeFragment : ArticleListFragment<HomeViewModel>() {

    private lateinit var headView: View

    private val urls by lazy { arrayListOf<String>() }
    private val titles by lazy { arrayListOf<String>() }
    private var page = 0

    override fun initView() {
        super.initView()

        initHeadView()
        initTitle()
        initBanner()
    }

    private fun initHeadView() {
        headView = View.inflate(activity, R.layout.home_headview, null)
        // 添加 Banner
        mArticleAdapter.addHeaderView(headView)
    }

    private fun initBanner() {
        headView.mBanner.run {
            setOnBannerListener { position ->
                startActivity<WebViewActivity>(
                    Key.LINK to urls[position],
                    Key.TITLE to titles[position]
                )
            }
            setImageLoader(GlideImageLoader())
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            setDelayTime(3000)
            setBannerAnimation(Transformer.DepthPage)
        }
    }

    private fun initTitle() {
        headView.run {
            mIcCommonTitle.mTvTitle.text = getString(R.string.common_title)
            mIcNewsArticle.mTvTitle.text = getString(R.string.news_article)
            mBtnTools.setOnClickListener {
                // TODO 常用专区
                showToast(R.string.developing)
            }
            mBtnWebsites.setOnClickListener { startActivity<CommonWebActivity>() }
        }
    }

    override fun initData() {
        super.initData()
        page = 0
        // 获取 首页 article
        viewModel.getArticle(page)

        // 获取 Banner
        viewModel.getBanner()
    }

    // 处理 repository 回调的数据
    override fun dataObserver() {
        // 调用父类 dataObserver
        super.dataObserver()

        viewModel.bannerData.observe(this, Observer {
            setBannerData(it)
        })

        viewModel.homeArticleData.observe(this, Observer {
            addData(it.datas)
        })
    }

    override fun onRefreshData() {
        page = 0
        // 获取 首页 article
        viewModel.getArticle(page)

        // 获取 Banner
        viewModel.getBanner()
    }

    override fun onLoadMoreData() {
        viewModel.getArticle(++page)
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