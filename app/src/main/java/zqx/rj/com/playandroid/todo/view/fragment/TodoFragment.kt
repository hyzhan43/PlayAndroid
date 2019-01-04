package zqx.rj.com.playandroid.todo.view.fragment

import android.arch.lifecycle.Observer
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
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
import org.jetbrains.anko.support.v4.toast
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.mvvm.common.OnItemSwipeListenerAdapter
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.todo.data.adapter.TodoAdapter
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp
import zqx.rj.com.mvvm.state.callback.todo.TypeChangeListener
import zqx.rj.com.mvvm.state.callback.todo.TodoContext
import zqx.rj.com.playandroid.todo.vm.TodoViewModel

/**
 * author：  HyZhan
 * create：  2019/1/3 16:13
 * desc：    TODO
 */
class TodoFragment : LifecycleFragment<TodoViewModel>(), TypeChangeListener {

    private lateinit var mTodoAdapter: TodoAdapter

    private var page: Int = 1
    // 当前状态  未完成 0  完成 1
    private val status by lazy { arguments?.getInt(Constant.STATUS) ?: 0 }
    // 当前 类型 工作1  学习2 生活3  0 默认全部
    private var type: Int = 0
    // delete index
    private var deleteIndex = -1

    companion object {
        fun getInstance(status: Int): Fragment {

            val bundle = Bundle()
            bundle.putInt(Constant.STATUS, status)

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
        mTodoAdapter = TodoAdapter(R.layout.todo_item, null)
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

        // 设置 默认的滑动删除
        val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(mTodoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
        itemTouchHelper.attachToRecyclerView(mRvTodo)

        // 开启滑动删除
        mTodoAdapter.enableSwipeItem()
        val paint = initPaint()

        // 设置滑动监听
        mTodoAdapter.setOnItemSwipeListener(object : OnItemSwipeListenerAdapter() {
            override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, isCurrentlyActive: Boolean) {
                canvas?.let {
                    // 设置的 文字
                    val text = getString(R.string.finish)

                    // 设置侧滑删除的 颜色
                    it.drawColor(ContextCompat.getColor(activity!!, R.color.colorPrimaryDark))

                    // 获取 item view 的高度
                    val height = viewHolder?.itemView?.height ?: 1

                    // 文字的范围
                    val bounds = Rect()
                    // 获取文字的范围
                    paint.getTextBounds(text, 0, text.length, bounds)
                    // 计算出 文字 y 轴的位置。
                    // y = 总item高度的一半 + 文字的高度的一半(注意的 文字是根据左下角来绘制的.所以是加上文字高度的一半)
                    val y = height / 2.0f + bounds.height() / 2.0f
                    // 绘制文字  x,y为  文字位置
                    it.drawText(text, 50f, y, paint)
                }
            }

            override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                // 滑动完成 回调  status = 1 未完成
                mViewModel.finishTodo(mTodoAdapter.getItem(pos)?.id ?: 0, 1)
            }
        })

        mTodoAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.right -> {
                    deleteIndex = position
                    mViewModel.deleteTodo(mTodoAdapter.getItem(position)?.id ?: 0)
                }
            }
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
            toast("删除成功")
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