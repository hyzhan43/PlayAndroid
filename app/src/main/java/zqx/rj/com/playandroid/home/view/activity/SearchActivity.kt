package zqx.rj.com.playandroid.home.view.activity

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.common_icon_title.view.*
import kotlinx.android.synthetic.main.common_search.view.*
import kotlinx.android.synthetic.main.common_tag.view.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.hideKeyboard
import zqx.rj.com.mvvm.state.callback.CollectListener
import zqx.rj.com.mvvm.state.callback.CollectState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivtiy
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.home.data.adapter.HistoryAdapter
import zqx.rj.com.playandroid.home.data.adapter.HomeSearchAdapter
import zqx.rj.com.playandroid.home.data.bean.HomeHotKeyRsp
import zqx.rj.com.playandroid.home.data.bean.SearchResult
import zqx.rj.com.playandroid.home.vm.HomeViewModel
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import kotlinx.android.synthetic.main.history_foot.view.*
import org.jetbrains.anko.toast
import org.litepal.LitePal
import org.litepal.extension.delete
import org.litepal.extension.deleteAll
import org.litepal.extension.find
import org.litepal.extension.findAll
import zqx.rj.com.playandroid.home.data.db.bean.Record


/**
 * author：  HyZhan
 * created： 2018/10/18 13:54
 * desc：    搜索页面
 */
class SearchActivity : LifecycleActivity<HomeViewModel>(), TextWatcher, CollectListener {

    private val tags = arrayListOf<String>()
    private lateinit var searchList: List<SearchResult>
    // 搜索结果 页码
    private val page = 0
    private lateinit var mResultAdapter: HomeSearchAdapter
    // 标识符，判断是否显示 -> 热门搜索-tag-最近搜索
    private var isShow = true

    private lateinit var mFootView: View
    private val mHistoryData by lazy { ArrayList<String>() }
    private val mHistoryAdapter by lazy { HistoryAdapter(R.layout.history_item, mHistoryData) }

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()

        initSearch()

        mIcHotKey.mIvIcon.setImageResource(R.drawable.ic_hotkey)
        mIcHotKey.mTvTitle.text = getString(R.string.hot_key)

        mIcHistory.mIvIcon.setImageResource(R.drawable.ic_history)
        mIcHistory.mTvTitle.text = getString(R.string.history)

        initSearchResult()

        initHistory()
    }

    private fun initHistory() {
        mRvHistory.layoutManager = LinearLayoutManager(this)
        mRvHistory.adapter = mHistoryAdapter
        mRvHistory.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        mFootView = LayoutInflater.from(this).inflate(R.layout.history_foot, null)
        mHistoryAdapter.setFooterView(mFootView)

        // 清除全部历史记录
        mFootView.mTvClear.setOnClickListener {
            LitePal.deleteAll<Record>()
            mHistoryAdapter.setNewData(null)
            mFootView.visibility = View.GONE
        }

        mHistoryAdapter.setOnItemChildClickListener { _, _, position ->
            if (mHistoryData.isNotEmpty()){
                // 删除本地数据库
                LitePal.deleteAll("Record", "name = ?", mHistoryData[position])

                mHistoryAdapter.remove(position)
            }
        }
    }

    private fun initSearchResult() {
        // 搜索结果
        mRvResult.layoutManager = LinearLayoutManager(this)
        mResultAdapter = HomeSearchAdapter(R.layout.article_item, null)
        mRvResult.adapter = mResultAdapter

        // item
        mResultAdapter.setOnItemClickListener { _, _, position ->
            startActivity<WebViewActivtiy>("link" to searchList.get(position).link,
                    "title" to searchList[position].title)
        }

        // ♥ 型按钮
        mResultAdapter.setOnItemChildClickListener { _, _, position ->
            LoginContext.instance.collect(this, position, this)
        }
    }

    private fun initSearch() {
        mIcSearch.mIvBack.setOnClickListener { finish() }
        mIcSearch.mIvClose.setOnClickListener { mIcSearch.mEtInput.setText("") }
        mIcSearch.mBtnSearch.setOnClickListener { view -> view.hideKeyboard() }

        mIcSearch.mEtInput.addTextChangedListener(this)

        // 点击 键盘search按钮 隐藏软键盘
        mIcSearch.mEtInput.setOnEditorActionListener(TextView.OnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                view.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun initData() {
        getHistory()

        mViewModel.getHotKey()
    }

    // 获取 历史搜索记录
    private fun getHistory() {
        val recordList = LitePal.select("name").order("id desc").find<Record>()

        val records = arrayListOf<String>()

        for (record in recordList) {
            records.add(record.name)
        }

        // 如果有历史记录，显示 清楚记录 view
        if (recordList.isNotEmpty())
            mFootView.visibility = View.VISIBLE
        else
            mFootView.visibility = View.GONE

        mHistoryAdapter.addData(records)
    }


    override fun dataObserver() {
        mViewModel.mHotKeyData.observe(this, Observer {
            it?.let { initFlowLayout(it.data) }
        })

        mViewModel.mSearchResultData.observe(this, Observer {
            it?.let { setSearchResult(it.data.datas) }
        })

        mViewModel.mCollectData.observe(this, Observer {
            // TODO
        })
    }

    private fun setSearchResult(resultList: List<SearchResult>) {
        // 如果数据为空直接不 显示搜索结果
        if (resultList.isEmpty()) {
            hideOtherView()
        } else if (!isShow) {     // 因为是异步回调，当快速按键盘删除键时候，多次回调 需要加这个判断是否显示数据
            searchList = resultList
            hideOtherView()
            mResultAdapter.setNewData(resultList)
            mResultAdapter.loadMoreComplete()
        }
    }

    private fun hideOtherView() {
        if (isShow) {
            mIcHotKey.visibility = View.VISIBLE
            mIcHistory.visibility = View.VISIBLE
            mTagFlowLayout.visibility = View.VISIBLE
            mRvHistory.visibility = View.VISIBLE
            // 清空数据
            mResultAdapter.setNewData(null)
        } else {
            mIcHotKey.visibility = View.GONE
            mIcHistory.visibility = View.GONE
            mTagFlowLayout.visibility = View.GONE
            mRvHistory.visibility = View.GONE
        }
    }

    // tag 标签
    private fun initFlowLayout(homeHotKeyRsp: List<HomeHotKeyRsp>) {

        for (tag in homeHotKeyRsp) {
            tags.add(tag.name)
        }

        mTagFlowLayout.adapter = object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout, position: Int, tag: String): View {

                val tagLayout = LayoutInflater.from(this@SearchActivity)
                        .inflate(R.layout.common_tag, mTagFlowLayout, false)
                tagLayout.mTvTag.text = tag
                return tagLayout
            }
        }

        mTagFlowLayout.setOnTagClickListener { view, position, _ ->
            mIcSearch.mEtInput.setText(tags[position])
            // 设置光标位置
            mIcSearch.mEtInput.setSelection(tags[position].length)
            // 关闭软键盘
            view.hideKeyboard()
            true
        }
    }

    override fun afterTextChanged(s: Editable?) {
        // 添加历史搜索记录
        s?.let {
            if (it.toString().isNotEmpty()) {
                saveDataToDb(it.toString())
                updateList(it.toString())

                // 如果有历史记录，显示 清除记录 view
                if (mHistoryData.isNotEmpty()) mFootView.visibility = View.VISIBLE
            }
        }
    }

    private fun saveDataToDb(name: String) {

        // 查询数据库
        val result = LitePal.where("name = ?", name).find<Record>()

        // 如果存在则 删除原来数据 添加新数据,不存在 则添加 到数据库，
        if (result.isNotEmpty()){
            // 更新 数据
            LitePal.delete<Record>(result[0].id)
        }

        val record = Record()
        record.name = name
        record.save()
    }

    private fun updateList(name: String) {
        for ((index,record) in mHistoryAdapter.data.withIndex()) {
            if (name == record){
                mHistoryAdapter.remove(index)
                mHistoryAdapter.addData(0, name)
                return
            }
        }
        mHistoryAdapter.addData(0, name)
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    // 监听输入变化
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // 判断输入是否为空
        if (s.isNullOrEmpty()) {
            isShow = true
            hideOtherView()
        } else {
            isShow = false
            mViewModel.search(page, s.toString())
        }
    }

    override fun collect(position: Int) {
        if (mResultAdapter.data.isNotEmpty()) {

            val result = mResultAdapter.data[position]

            val isCollect = result.collect

            // 同步 recyclerView
            result.collect = !isCollect
            mResultAdapter.notifyDataSetChanged()

            // 更新 主页面
            CollectState.notifyCollectState(result.id)

            // 文章 id
            val id = searchList[position].id

            if (isCollect) mViewModel.unCollect(id) else mViewModel.collect(id)
        }
    }

    override fun onBackPressed() = finish()
}