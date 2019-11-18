package zqx.rj.com.playandroid.main.navigation.view.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhan.ktwing.ext.startActivity
import com.zhan.mvvm.mvvm.LifecycleFragment
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.common_tag.view.*
import kotlinx.android.synthetic.main.fragment_navigation.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.main.navigation.adapter.CategoryAdapter
import zqx.rj.com.playandroid.main.navigation.data.bean.NavigationCategoryRsp
import zqx.rj.com.playandroid.main.navigation.vm.NavigationViewModel
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    TODO
 */
class NavigationFragment : LifecycleFragment<NavigationViewModel>() {

    private val categories = arrayListOf<String>()
    private lateinit var mNavigationCategoryRspList: List<NavigationCategoryRsp>

    private val mCategoryAdapter by lazy { CategoryAdapter() }

    override fun getLayoutId(): Int = R.layout.fragment_navigation

    override fun initView() {
        super.initView()

        mRvCategory.layoutManager = LinearLayoutManager(activity)
        mRvCategory.adapter = mCategoryAdapter
    }

    override fun initListener() {
        super.initListener()
        mCategoryAdapter.setOnItemChildClickListener { _, _, position ->
            switchCategory(position)
            switchRepresent(position)
        }
    }

    override fun initData() {
        super.initData()
        viewModel.getCategory()
    }

    override fun dataObserver() {
        viewModel.categoryData.observe(this, Observer {
            initNavigation(it)
        })
    }

    private fun initNavigation(navigationCategoryRspList: List<NavigationCategoryRsp>) {

        mNavigationCategoryRspList = navigationCategoryRspList

        for (navigationCategoryRsp in navigationCategoryRspList) {
            categories.add(navigationCategoryRsp.name)
        }

        // 设置 分类数据
        mCategoryAdapter.addData(categories)
        // 默认选中第一个  并设置 第一个 tag值
        switchRepresent(0)
        // 默认选中第一个 categoryData
        switchCategory(0)
    }

    private fun initTagLayout(titles: List<String>, links: List<String>) {
        mTflRepresent.adapter = object : TagAdapter<String>(titles) {
            override fun getView(parent: FlowLayout?, position: Int, title: String?): View {

                return LayoutInflater.from(context)
                    .inflate(R.layout.common_tag, parent, false)
                    .apply { mTvTag.text = title }
            }
        }

        mTflRepresent.setOnTagClickListener { _, position, _ ->
            startActivity<WebViewActivity>(
                Key.LINK to links[position],
                Key.TITLE to titles[position]
            )
            true
        }
    }

    // 切换 分类的 内容(tag)
    private fun switchRepresent(position: Int) {

        val titles = arrayListOf<String>()
        val links = arrayListOf<String>()

        for (article in mNavigationCategoryRspList[position].articles) {
            titles.add(article.title)
            links.add(article.link)
        }

        initTagLayout(titles, links)
    }

    // 切换 categoryData 选择状态
    private fun switchCategory(position: Int) {
        mCategoryAdapter.run {
            refreshNotifyItemChanged(selectedPosition)
            selectedPosition = position
            refreshNotifyItemChanged(position)
        }
    }
}