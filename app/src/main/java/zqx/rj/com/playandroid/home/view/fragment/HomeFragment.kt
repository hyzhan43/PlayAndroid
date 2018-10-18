package zqx.rj.com.playandroid.home.view.fragment

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.commom_bar.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_article_item.*
import kotlinx.android.synthetic.main.home_article_item.view.*
import kotlinx.android.synthetic.main.home_banner_item.view.*
import kotlinx.android.synthetic.main.home_special_item.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.common.GlideImageLoader
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.R.id.mBanner
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.home.data.adapter.HomeArticleAdapter
import zqx.rj.com.playandroid.home.data.bean.Article
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.view.activity.SearchActivity
import zqx.rj.com.playandroid.home.view.activity.WebViewActivtiy
import zqx.rj.com.playandroid.home.vm.HomeViewModel
import java.util.*


/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    首页
 */
class HomeFragment : LifecycleFragment<HomeViewModel>() {

    private val mAdapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter(R.layout.home_article_item, null)
    }

    private lateinit var mBanner: Banner
    private val urls = arrayListOf<String>()
    private val articleList by lazy { arrayListOf<Article>() }
    private var page = 0

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        // 一定要 ！！ (注入 mViewModel)
        super.initView()

        // 设置 back 和 search
        mTvBarTitle.text = getString(R.string.app_name)
        mIvSearch.setOnClickListener { startActivity<SearchActivity>() }

        mRvArticle.layoutManager = LinearLayoutManager(context)
        mRvArticle.adapter = mAdapter

        val headView = View.inflate(activity, R.layout.home_banner_item, null)
        // 设置标题
        initTitle(headView)

        mBanner = headView.mBanner
        mBanner.setOnBannerListener { startActivity<WebViewActivtiy>("link" to urls[it]) }

        // 添加 Banner
        mAdapter.addHeaderView(headView)

        mBanner.setImageLoader(GlideImageLoader())
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        mBanner.setDelayTime(3000)
        mBanner.setBannerAnimation(Transformer.DepthPage)

        // ♥ 型按钮
        mAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, mIvLove)
        }

        // item
        mAdapter.setOnItemClickListener { _, _, position ->
            startActivity<WebViewActivtiy>("link" to articleList[position].link,
                    "title" to articleList[position].title)
        }

        mAdapter.setEnableLoadMore(true)
        // 上拉加载更多
        mAdapter.setOnLoadMoreListener({
            mViewModel.getArticle(page++)
        }, mRvArticle)

    }

    private fun initTitle(headView: View?) {
        headView?.let {
            it.mIcCommonTitle.mTvTitle.text = getString(R.string.common_title)
            it.mBtnTools.setOnClickListener { startActivity<SearchActivity>() }

            it.mIcNewsArticle.mTvTitle.text = getString(R.string.news_article)
            it.mBtnWebsites.setOnClickListener { startActivity<SearchActivity>() }
        }
    }

    override fun initData() {
        // 获取 首页 article
        mViewModel.getArticle(page)

        // 获取 Banner
        mViewModel.getBanner()
    }

    // 监听数据变化
    override fun dataObserver() {

        // 处理 repository 回调的数据
        mViewModel.mBannerData.observe(this, Observer {
            it?.data?.let {
                setBannerData(it)
            }
        })


        mViewModel.mHomeArticleData.observe(this, Observer {
            if (it?.data?.datas?.size != 0)
                setArticleData(it?.data?.datas)
            else
                mAdapter.loadMoreEnd()
        })
    }

    private fun setArticleData(article: List<Article>?) {
        article?.let {
            // 本地保存，方便以后跳转
            articleList.addAll(it)
            // 更新 RecyclerView
            mAdapter.addData(it)
            mAdapter.loadMoreComplete()
        }
    }

    private fun setBannerData(bannerList: List<BannerRsp>) {

        val images = ArrayList<String>()
        val titles = ArrayList<String>()

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