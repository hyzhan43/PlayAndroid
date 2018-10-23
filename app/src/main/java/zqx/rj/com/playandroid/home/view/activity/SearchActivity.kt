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
import kotlinx.android.synthetic.main.article_item.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.Util
import zqx.rj.com.mvvm.common.hideKeyboard
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.home.data.adapter.HomeSearchAdapter
import zqx.rj.com.playandroid.home.data.bean.HomeHotKeyRsp
import zqx.rj.com.playandroid.home.data.bean.SearchResult
import zqx.rj.com.playandroid.home.vm.HomeViewModel

/**
 * author：  HyZhan
 * created： 2018/10/18 13:54
 * desc：    搜索页面
 */
class SearchActivity : LifecycleActivity<HomeViewModel>(), TextWatcher {

    private val tags = arrayListOf<String>()
    private lateinit var searchList: List<SearchResult>
    // 搜索结果 页码
    private val page = 0
    private lateinit var mResultAdapter: HomeSearchAdapter
    // 标识符，判断是否显示 -> 热门搜索-tag-最近搜索
    private var isShow = true

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()

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

        mIcHotKey.mIvIcon.setImageResource(R.drawable.ic_hotkey)
        mIcHotKey.mTvTitle.text = getString(R.string.hot_key)

        mIcHistory.mIvIcon.setImageResource(R.drawable.ic_history)
        mIcHistory.mTvTitle.text = getString(R.string.history)

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
            LoginContext.instance.collect(this, mIvLove)
        }
    }

    override fun initData() {
        mViewModel.getHotKey()
    }


    override fun dataObserver() {
        mViewModel.mHotKeyData.observe(this, Observer {
            it?.let { initFlowLayout(it.data) }
        })

        mViewModel.mSearchResultData.observe(this, Observer {
            it?.let { setSearchResult(it.data.datas) }
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

    override fun afterTextChanged(s: Editable?) {}

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

    override fun onBackPressed() = finish()
}