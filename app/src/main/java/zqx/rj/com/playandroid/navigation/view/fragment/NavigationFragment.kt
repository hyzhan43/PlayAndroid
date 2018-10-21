package zqx.rj.com.playandroid.navigation.view.fragment

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.commom_bar.view.*
import kotlinx.android.synthetic.main.common_tag.view.*
import kotlinx.android.synthetic.main.fragment_navigation.*
import org.jetbrains.anko.support.v4.startActivity
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.view.activity.WebViewActivtiy
import zqx.rj.com.playandroid.navigation.data.adapter.CategoryAdapter
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp
import zqx.rj.com.playandroid.navigation.vm.NavigationViewModel

/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    TODO
 */
class NavigationFragment : LifecycleFragment<NavigationViewModel>() {

    private val categories = arrayListOf<String>()
    private lateinit var mNaviCategoryRspList: List<NaviCategoryRsp>

    private val mCategoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter(R.layout.navigation_category_item, null)
    }

    override fun getLayoutId(): Int = R.layout.fragment_navigation

    override fun initView() {
        super.initView()

        mIcBar.mTvBarTitle.text = getString(R.string.navigation)

        mRvCategory.layoutManager = LinearLayoutManager(activity)
        mRvCategory.adapter = mCategoryAdapter

        mCategoryAdapter.setOnItemChildClickListener { _, _, position ->
            switchCategory(position)
            switchRepresent(position)
        }
    }

    override fun initData() {
        mViewModel.getCategory()
    }

    override fun dataObserver() {
        mViewModel.mCategory.observe(this, Observer {
            it?.let {
                initNavigation(it.data)
            }
        })
    }

    private fun initNavigation(naviCategoryRspList: List<NaviCategoryRsp>) {

        mNaviCategoryRspList = naviCategoryRspList

        for (naviCategoryRsp in naviCategoryRspList) {
            categories.add(naviCategoryRsp.name)
        }

        // 设置 分类数据
        mCategoryAdapter.addData(categories)
        // 默认选中第一个  并设置 第一个 tag值
        switchRepresent(0)
        // 默认选中第一个 category
        switchCategory(0)
    }

    private fun initTagLayout(titles: List<String>, links: List<String>) {
        mTflRepresent.adapter = object : TagAdapter<String>(titles) {
            override fun getView(parent: FlowLayout?, position: Int, title: String?): View {

                val mTagLayout = LayoutInflater.from(this@NavigationFragment.context)
                        .inflate(R.layout.common_tag, parent, false)
                mTagLayout.mTvTag.text = title
                return mTagLayout
            }
        }

        mTflRepresent.setOnTagClickListener { _, position, _ ->
            startActivity<WebViewActivtiy>("link" to links[position],
                    "title" to titles[position])
            true
        }
    }

    // 切换 分类的 内容(tag)
    private fun switchRepresent(position: Int) {

        val titles = arrayListOf<String>()
        val links = arrayListOf<String>()

        for (article in mNaviCategoryRspList[position].articles) {
            titles.add(article.title)
            links.add(article.link)
        }

        initTagLayout(titles, links)
    }

    // 切换 category 选择状态
    private fun switchCategory(position: Int) {
        mCategoryAdapter.selectedPosition = position
        mCategoryAdapter.notifyDataSetChanged()
    }
}