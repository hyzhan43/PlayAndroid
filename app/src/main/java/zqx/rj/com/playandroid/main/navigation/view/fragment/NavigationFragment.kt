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
import zqx.rj.com.playandroid.main.navigation.data.bean.ArticleData
import zqx.rj.com.playandroid.main.navigation.data.bean.NavigationData
import zqx.rj.com.playandroid.main.navigation.vm.NavigationViewModel
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    TODO
 */
class NavigationFragment : LifecycleFragment<NavigationViewModel>() {

    private val mCategoryAdapter by lazy { CategoryAdapter() }

    override fun getLayoutId(): Int = R.layout.fragment_navigation

    override fun initView() {
        super.initView()

        mRvCategory.layoutManager = LinearLayoutManager(activity)
        mRvCategory.adapter = mCategoryAdapter
    }

    override fun initData() {
        super.initData()
        viewModel.getCategory()
    }

    override fun dataObserver() {
        viewModel.categoryLiveData.observe(this, Observer {
            initNavigation(it)
        })
    }

    private fun initNavigation(navigationData: NavigationData) {
        // 设置 分类数据
        mCategoryAdapter.addData(navigationData.categories)

        // 默认选中第一个并设置 第一个 tag值
        initTagLayout(navigationData.articleData[0])

        // 默认选中第一个 category
        switchCategory(0)

        mCategoryAdapter.setOnItemChildClickListener { _, _, position ->
            switchCategory(position)
            initTagLayout(navigationData.articleData[position])
        }
    }


    // 切换 分类的 内容(tag)
    private fun initTagLayout(articleData: ArticleData) {

        val titles = articleData.titles
        val links = articleData.links

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

    // 切换 categoryData 选择状态
    private fun switchCategory(position: Int) {
        mCategoryAdapter.run {
            refreshNotifyItemChanged(selectedPosition)
            selectedPosition = position
            refreshNotifyItemChanged(position)
        }
    }
}