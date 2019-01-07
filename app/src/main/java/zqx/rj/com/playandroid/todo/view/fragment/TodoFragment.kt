package zqx.rj.com.playandroid.todo.view.fragment

import android.arch.lifecycle.Observer
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.LinearLayout
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.gavin.com.library.StickyDecoration
import com.gavin.com.library.listener.GroupListener
import kotlinx.android.synthetic.main.fragment_todo.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.common.OnItemSwipeListenerAdapter
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.state.callback.todo.TodoContext
import zqx.rj.com.mvvm.state.callback.todo.TypeChangeListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.todo.data.adapter.TodoAdapter
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp
import zqx.rj.com.playandroid.todo.view.activity.AddTodoActivity
import zqx.rj.com.playandroid.todo.vm.TodoViewModel

/**
 * author：  HyZhan
 * create：  2019/1/3 16:13
 * desc：    TODO
 */
class TodoFragment : LifecycleFragment<TodoViewModel>(), TypeChangeListener {

    private val mTodoAdapter by lazy { TodoAdapter(R.layout.todo_item, null) }

    private var page: Int = 1
    // 当前状态  未完成 0  完成 1
    private val status by lazy { arguments?.getInt(Constant.STATUS) ?: 0 }
    // 当前 类型 工作1  学习2 生活3  0 默认全部
    private var type by Preference(Constant.TODO_TYPE, Constant.ALL)
    // delete index
    private var deleteIndex = 0
    // update index
    private var updateIndex = 0
    private var priority: Int = 0

    private val swipeTitle by lazy { arguments?.getString(Constant.SWIPE_TITLE) ?: "" }
    private val swipeColor by lazy {
        arguments?.getInt(Constant.SWIPE_COLOR) ?: R.color.colorPrimaryDark
    }

    companion object {
        fun getInstance(status: Int, swipeTitle: String, @ColorRes swipeColor: Int): Fragment {

            val bundle = Bundle()
            bundle.putInt(Constant.STATUS, status)
            bundle.putString(Constant.SWIPE_TITLE, swipeTitle)
            bundle.putInt(Constant.SWIPE_COLOR, swipeColor)

            val fragment = TodoFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_todo

    override fun initView() {
        super.initView()

        initTodoList()

        // 设置 刷新
        mSrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        mSrlRefresh.setOnRefreshListener { refresh() }

        // 设置 type listener
        TodoContext.addListener(this)
    }

    /**
     *  重新加载
     */
    override fun reLoad() {
        page = 1
        mViewModel.getTodoList(page, status, type)
    }


    private fun initTodoList() {
        mRvTodo.layoutManager = LinearLayoutManager(activity)
        // 设置默认分割线
        mRvTodo.addItemDecoration(DividerItemDecoration(activity, LinearLayout.VERTICAL))
        mRvTodo.adapter = mTodoAdapter
        // 设置空数据布局
        mTodoAdapter.setEmptyView(R.layout.layout_empty, mRvTodo)
        // 开启上拉加载更多
        mTodoAdapter.setEnableLoadMore(true)
        mTodoAdapter.setOnLoadMoreListener({
            mViewModel.getTodoList(++page, status, type)
        }, mRvTodo)

        // 设置 item 的组名
        val groupListener = GroupListener { position ->
            if (mTodoAdapter.data.isNotEmpty()) {
                //获取分组名
                mTodoAdapter.getItem(position)?.dateStr
            } else ""
        }

        // 利用RecyclerView.ItemDecoration实现顶部悬浮效果
        val decoration = StickyDecoration.Builder
                .init(groupListener)
                .setTextSideMargin(20)
                .setGroupBackground(ContextCompat.getColor(activity!!, R.color.lightBlue))
                .build()
        mRvTodo.addItemDecoration(decoration)

        // 设置 默认的滑动
        val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(mTodoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
        itemTouchHelper.attachToRecyclerView(mRvTodo)

        // 开启滑动
        mTodoAdapter.enableSwipeItem()

        // 设置滑动监听
        mTodoAdapter.setOnItemSwipeListener(object : OnItemSwipeListenerAdapter() {
            override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, isCurrentlyActive: Boolean) {
                // 绘制出文字和背景色
                drawText(canvas, viewHolder)
            }

            override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                // 滑动完成或还原 回调  (status = 1 完成, status = 0 未完成)
                // 如果当前页面是 未完成fragment 则是 完成 status = 1。
                val isFinish = if (status == 1) 0 else 1
                mViewModel.finishTodo(mTodoAdapter.getItem(pos)?.id ?: 0, isFinish)
            }
        })

        // 设置滑动删除监听
        mTodoAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                // item 删除
                R.id.mTvDelete -> {
                    deleteIndex = position
                    mViewModel.deleteTodo(mTodoAdapter.getItem(position)?.id ?: 0)
                }
                // item 重要
                R.id.mTvImportant -> {
                    updateIndex = position
                    val todoRsp = mTodoAdapter.getItem(position)

                    todoRsp?.let {
                        priority = if (it.priority == 1) {
                            Constant.TODO_COMMON
                        } else {
                            Constant.TODO_IMPORTANT
                        }

                        mViewModel.updateTodo(
                                it.id,
                                it.title,
                                it.dateStr,
                                it.status,
                                it.type,
                                it.content,
                                priority)
                    }
                }
                // item click
                R.id.content -> {
                    // 滑动有冲突 (BaseRecyclerViewAdapter 与 EasySwipeMenuLayout)
                    // adapter.setOnItemClickListener 无效
                    // 采用 R.id.content 来实现点击效果

                    val todoRsp = mTodoAdapter.getItem(position)
                    todoRsp?.let {
                        startActivity<AddTodoActivity>(
                                "id" to it.id,
                                "title" to it.title,
                                "time" to it.dateStr,
                                "status" to it.status,
                                "type" to it.type,
                                "content" to it.content,
                                "priority" to it.priority)
                    }
                }
            }
        }
    }

    private fun drawText(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?) {
        val paint = initPaint()
        canvas?.let {
            // 设置侧滑删除的 颜色
            it.drawColor(ContextCompat.getColor(activity!!, swipeColor))

            // 获取 item view 的高度
            val height = viewHolder?.itemView?.height ?: 1

            // 文字的范围
            val bounds = Rect()
            // 获取文字的范围
            paint.getTextBounds(swipeTitle, 0, swipeTitle.length, bounds)
            // 计算出 文字 y 轴的位置。
            // y = 总item高度的一半 + 文字的高度的一半(注意的 文字是根据左下角来绘制的.所以是加上文字高度的一半)
            val y = height / 2.0f + bounds.height() / 2.0f
            // 绘制文字  x,y为  文字位置
            it.drawText(swipeTitle, 50f, y, paint)
        }
    }

    override fun initData() {
        super.initData()

        showLoading()
        page = 1
        mViewModel.getTodoList(page, status, type)
    }

    override fun dataObserver() {
        mViewModel.mTodoData.observe(this, Observer { response ->
            response?.data?.datas?.let { setTodoData(it) }
        })

        mViewModel.mDeleteTodoData.observe(this, Observer {
            mTodoAdapter.remove(deleteIndex)
            toast(getString(R.string.delete_suc))
        })

        mViewModel.mUpdateTodoData.observe(this, Observer {
            // 改变数据源
            mTodoAdapter.data[updateIndex].priority = priority
            // 局部刷新 recyclerView
            mTodoAdapter.notifyItemChanged(updateIndex)
        })
    }

    private fun setTodoData(data: List<TodoRsp>) {

        showSuccess()

        if (mSrlRefresh.isRefreshing || page == 1) {
            mSrlRefresh.isRefreshing = false
            mTodoAdapter.setNewData(data)
            mTodoAdapter.loadMoreComplete()
            return
        }

        if (data.isEmpty()) {
            mTodoAdapter.loadMoreEnd()
            return
        }

        // 否则 就是添加数据
        mTodoAdapter.addData(data)
        mTodoAdapter.loadMoreComplete()
    }

    private fun initPaint(): Paint {
        val paint = Paint()
        paint.color = Color.WHITE
        // 设置文字大小
        paint.textSize = 50.0f
        // 抗锯齿
        paint.isAntiAlias = true
        return paint
    }

    override fun typeChange(type: Int) {
        // 改变 sharedPreferences   type值
        this.type = type
        // 刷新数据
        refresh()
    }

    override fun refreshTodoList() = refresh()

    private fun refresh() {
        page = 1
        mViewModel.getTodoList(page, status, type)
    }

    override fun onDestroy() {
        super.onDestroy()
        TodoContext.removeListener(this)
    }
}