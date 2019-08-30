package zqx.rj.com.playandroid.other.widget.list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.layout_refresh_recyclcer.view.*
import zqx.rj.com.playandroid.R

/**
 *  @author: HyJame
 *  @date:   2019-08-25
 *  @desc:   TODO
 */
class RecyclerViewPlus
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyle: Int = 0)
    : LinearLayout(context, attrs, defStyle) {


    var refreshColor: Int = 0
        set(value) {
            field = value
            mSrlRefresh.setColorSchemeResources(value)
        }

    var page = 0

//    var <T> mAdapter: BaseQuickAdapter<T, BaseViewHolder>? = null


//    fun <T> setAdapter(adapter: BaseQuickAdapter<T, BaseViewHolder>) {
//        this.mAdapter = adapter
//
//    }


    var layoutManager: RecyclerView.LayoutManager? = null

    init {
        View.inflate(context, R.layout.layout_refresh_recyclcer, this)

        //val typeArray = context.obtainStyledAttributes(attrs, R.styleable.PlaceHolderView, defStyle, 0)

//        with(typeArray) {
//
//            recycle()
//        }

        initRefresh()
        //initRecyclerView()
    }

    private fun initRefresh() {
        // 设置 下拉刷新 loading 颜色
        mSrlRefresh.setColorSchemeResources(refreshColor)
        mSrlRefresh.setOnRefreshListener {
            page = 0
            onRefreshData()
        }
    }

//    private fun initRecyclerView() {
//
//        mRvContent.layoutManager = layoutManager
//        mRvContent.adapter = mAdapter
//
//        // 上拉加载更多
//        mAdapter?.setOnLoadMoreListener({
//            ++page
//            onLoadMoreData()
//        }, mRvContent)
//    }
//
//    private fun <T> addData(newData: List<T>) {
//        // 如果为空的话，就直接 显示加载完毕
//        if (newData.isEmpty()) {
//            mAdapter?.loadMoreEnd()
//            return
//        }
//
//        // 如果是 下拉刷新 直接设置数据
//        if (mSrlRefresh.isRefreshing) {
//            mSrlRefresh.isRefreshing = false
//            mAdapter?.setNewData(newData)
//            mAdapter?.loadMoreComplete()
//            return
//        }
//
//        // 否则 添加新数据
//        mAdapter?.addData(newData)
//        mAdapter?.loadMoreComplete()
//    }

    fun onRefreshData() {}

    fun onLoadMoreData() {}
}