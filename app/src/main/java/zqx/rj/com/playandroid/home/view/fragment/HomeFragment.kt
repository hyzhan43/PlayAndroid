package zqx.rj.com.playandroid.home.view.fragment

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kingja.loadsir.callback.SuccessCallback
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.article_item.view.*
import kotlinx.android.synthetic.main.commom_bar.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_banner_item.view.*
import kotlinx.android.synthetic.main.home_special_item.view.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.common.GlideImageLoader
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.mvvm.state.callback.CollectState
import zqx.rj.com.mvvm.state.callback.CollectUpdateListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.home.data.adapter.HomeArticleAdapter
import zqx.rj.com.playandroid.home.data.bean.Article
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.view.activity.CommonWebActivity
import zqx.rj.com.playandroid.home.view.activity.SearchActivity
import zqx.rj.com.playandroid.home.view.activity.WebViewActivtiy
import zqx.rj.com.playandroid.home.vm.HomeViewModel
import java.util.*


/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    首页
 */
class HomeFragment : LifecycleFragment<HomeViewModel>(), CollectListener, CollectUpdateListener {

    private val mAdapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter(R.layout.article_item, null)
    }

    private lateinit var mBanner: Banner
    private val urls by lazy { arrayListOf<String>() }
    private val titles by lazy { arrayListOf<String>() }
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

        initHeadView()

        // ♥ 型按钮
        mAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(activity, position, this)
        }

        // item
        mAdapter.setOnItemClickListener { _, _, position ->
            startActivity<WebViewActivtiy>("link" to articleList[position].link,
                    "title" to articleList[position].title)
        }

        mAdapter.setEnableLoadMore(true)
        // 上拉加载更多
        mAdapter.setOnLoadMoreListener({ mViewModel.getArticle(page++) }, mRvArticle)

        CollectState.addListener(this)
    }

    private fun initHeadView() {
        val headView = View.inflate(activity, R.layout.home_banner_item, null)
        // 设置标题
        initTitle(headView)

        mBanner = headView.mBanner
        mBanner.setOnBannerListener { position ->
            startActivity<WebViewActivtiy>("link" to urls[position],
                    "title" to titles[position])
        }

        // 添加 Banner
        mAdapter.addHeaderView(headView)

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

        mViewModel.mBannerData.observe(this, Observer {
            it?.let { setBannerData(it.data) }
        })

        mViewModel.mHomeArticleData.observe(this, Observer {
            it?.let {
                // 如果没有更多数据 则 adapter 加载完毕
                if (it.data.datas.isEmpty())
                    mAdapter.loadMoreEnd()
                else
                    setArticleData(it.data.datas) // 否则 添加数据
            }

            // 关闭 loading
            loadService.showCallback(SuccessCallback::class.java)
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

    // 点击收藏 回调
    override fun collect(position: Int) {

        val isCollect = mAdapter.data[position].collect

        // 同步 recyclerView
        mAdapter.data[position].collect = !isCollect
        mAdapter.notifyDataSetChanged()

        // 文章 id
        val id = articleList[position].id

        if (isCollect) mViewModel.unCollect(id) else mViewModel.collect(id)
    }

    // 更新 主页面的  article 心型状态
    // 例如 在 我的收藏点击了  取消收藏   这时候 就通知主页面 更新
    override fun updateState(id: Int) {

        var position = 0

        for ((index, value) in mAdapter.data.withIndex()) {
            if (value.id == id) {
                position = index
                break
            }
        }

        val isCollect = mAdapter.data[position].collect
        mAdapter.data[position].collect = !isCollect
        mAdapter.notifyDataSetChanged()
    }
}