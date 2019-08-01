package zqx.rj.com.playandroid.common.search.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.common_icon_title.view.*
import kotlinx.android.synthetic.main.common_search.*
import kotlinx.android.synthetic.main.common_search.view.*
import kotlinx.android.synthetic.main.common_tag.view.*
import kotlinx.android.synthetic.main.history_foot.view.*
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.ext.gone
import zqx.rj.com.mvvm.ext.hideKeyboard
import zqx.rj.com.mvvm.ext.str
import zqx.rj.com.mvvm.ext.visible
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.adapter.HistoryAdapter
import zqx.rj.com.playandroid.common.article.data.bean.Article
import zqx.rj.com.playandroid.common.article.view.ArticleListActivity
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.vm.SearchViewModel


/**
 * author：  HyZhan
 * created： 2018/10/18 13:54
 * desc：    搜索页面
 */
class SearchActivity : ArticleListActivity<SearchViewModel>() {

    // 搜索结果 页码
    private var page = 0

    // 标识符，判断是否显示
    private var isShow = true

    // 最多纪录数
    private val maxRecord = 5

    private var recordIndex = 0

    private lateinit var mFootView: View
    private val mHistoryAdapter by lazy { HistoryAdapter() }

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()

        initSearch()

        mIcHotKey.mIvIcon.setImageResource(R.drawable.ic_hotkey)
        mIcHotKey.mTvTitle.text = getString(R.string.hot_key)

        mIcHistory.mIvIcon.setImageResource(R.drawable.ic_history)
        mIcHistory.mTvTitle.text = getString(R.string.history)

        initHistory()
    }

    private fun initHistory() {
        mRvHistory.layoutManager = LinearLayoutManager(this)
        mRvHistory.adapter = mHistoryAdapter
        mRvHistory.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        mFootView = LayoutInflater.from(this).inflate(R.layout.history_foot, mLlContent, false)
        mHistoryAdapter.setFooterView(mFootView)

        // 清除全部历史记录
        mFootView.mTvClear.setOnClickListener {
            mViewModel.clearRecords()
        }

        mHistoryAdapter.setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.mIvDelete) {
                recordIndex = position
                mViewModel.deleteOneRecord(mHistoryAdapter.data[position])
            }
        }

        mHistoryAdapter.setOnItemClickListener { _, _, position ->
            searchKeyword(mHistoryAdapter.data[position])
        }
    }

    private fun initSearch() {
        mIvBack.setOnClickListener { finish() }
        mIvClose.setOnClickListener {
            mIcSearch.mEtInput.setText("")
            showSearchView()
            hideKeyboard()
        }

        mBtnSearch.setOnClickListener { view ->
            //搜索
            searchKeyword(mEtInput.str())
        }

        // 点击 键盘search按钮 隐藏软键盘
        mEtInput.setOnEditorActionListener(TextView.OnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchKeyword(mEtInput.str())
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun searchKeyword(keyword: String) {

        // 判断输入是否为空
        if (keyword.isEmpty()) {
            showSearchView()
            toast(getString(R.string.keyword_empty))
            return
        }

        hideKeyboard()

        mEtInput.setText(keyword)
        // 设置光标位置
        mEtInput.setSelection(keyword.length)

        mViewModel.search(page, keyword)
    }

    override fun initData() {
        mViewModel.getRecords()
        mViewModel.getHotKey()
    }


    override fun dataObserver() {

        super.dataObserver()

        // 热门搜索 回调
        mViewModel.mHotKeyData.observe(this, Observer { response ->
            response?.let { showHotTags(it.data) }
        })

        // 搜索成功回调
        mViewModel.mSearchResultData.observe(this, Observer { response ->
            response?.let {
                showSearchResult(it.data.datas)
                // 添加历史搜索记录
                mViewModel.addRecord(mEtInput.str())
            }
        })

        mViewModel.deleteRecord.observe(this, Observer {
            mHistoryAdapter.remove(recordIndex)
            if (mHistoryAdapter.data.isEmpty()) mFootView.gone()
        })

        mViewModel.newRecord.observe(this, Observer {
            updateRecordPosition(mEtInput.str())
        })

        mViewModel.records.observe(this, Observer { records ->
            val recordNames = records?.map { it.name }?.toList()
            recordNames?.let {
                if (it.isEmpty()) mFootView.gone() else mHistoryAdapter.addData(it)
            }
        })

        mViewModel.clearRecord.observe(this, Observer {
            mHistoryAdapter.setNewData(null)
            mFootView.gone()
        })
    }

    private fun showSearchResult(resultList: List<Article>) {
        // 如果数据为空直接不 显示搜索结果
        if (resultList.isEmpty()) {
            showSearchView()
            toast(getString(R.string.empty_data))
            return
        }

        addData(resultList)
        hideSearchView()
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.search(page, mEtInput.str())
    }

    // 搜索结果  加载更多
    override fun onLoadMoreData() {
        mViewModel.search(++page, mEtInput.str())
    }

    private fun showSearchView() {
        if (isShow) return

        mIcHotKey.visible()
        mIcHistory.visible()
        mTagFlowLayout.visible()
        mRvHistory.visible()
        // 清空数据
        mArticleAdapter.setNewData(null)

        isShow = true
    }

    private fun hideSearchView() {
        if (!isShow) return

        mIcHotKey.gone()
        mIcHistory.gone()
        mTagFlowLayout.gone()
        mRvHistory.gone()

        isShow = false
    }

    // 热门搜索tag 标签
    private fun showHotTags(homeHotKeyRsp: List<HotKeyRsp>) {

        val tags = homeHotKeyRsp.map { it.name }.toList()

        mTagFlowLayout.adapter = object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout, position: Int, tag: String): View {

                return LayoutInflater.from(this@SearchActivity)
                        .inflate(R.layout.common_tag, parent, false)
                        .apply { mTvTag.text = tag }
            }
        }


        mTagFlowLayout.setOnTagClickListener { view, position, _ ->
            searchKeyword(tags[position])
            true
        }
    }


    private fun updateRecordPosition(name: String) {

        val records = mHistoryAdapter.data

        // 判断是否存在一个同样的搜索记录
        val index = records.indexOf(name)
        if (index == -1) {

            if (records.size >= maxRecord) {
                // 删除最后一条
                mHistoryAdapter.remove(4)
            }

            // 不存在就添加
            mHistoryAdapter.addData(0, name)
            return
        }

        if (index != 0) {
            // 存在就调整该记录到第一条。
            mHistoryAdapter.remove(index)
            mHistoryAdapter.addData(0, name)
        }
    }

    override fun onBackPressed() = finish()
}