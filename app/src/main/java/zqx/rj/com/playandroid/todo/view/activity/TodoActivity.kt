package zqx.rj.com.playandroid.todo.view.activity

import android.arch.lifecycle.Observer
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.gavin.com.library.StickyDecoration
import com.gavin.com.library.listener.GroupListener
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.OnItemSwipeListenerAdapter
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.todo.data.adapter.TodoAdapter
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp
import zqx.rj.com.playandroid.todo.vm.TodoViewModel


class TodoActivity : LifecycleActivity<TodoViewModel>() {

    private lateinit var mTodoAdapter: TodoAdapter

    private var page: Int = 1

    override fun getLayoutId(): Int = R.layout.activity_todo

    override fun initView() {
        super.initView()

        setToolBar(toolbar, getString(R.string.all))
        initFloatButton()
        initTodoList()
        initNavigationBar()

        // 设置 刷新
        mSrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        mSrlRefresh.setOnRefreshListener {
            page = 1
            mViewModel.getTodoList(page)
        }
    }

    private fun initFloatButton() {
        mFabAdd.setOnClickListener { startActivity<AddTodoActivity>() }
    }

    private fun initTodoList() {
        mRvTodo.layoutManager = LinearLayoutManager(this)
        // 设置默认分割线
        mRvTodo.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        mTodoAdapter = TodoAdapter(R.layout.todo_item, null)
        mRvTodo.adapter = mTodoAdapter
        // 设置空数据布局
        mTodoAdapter.setEmptyView(R.layout.layout_empty, mRvTodo)

        // 设置 item 的组名
        val groupListener = GroupListener { position ->
            if (mTodoAdapter.data.isNotEmpty()) {
                //获取分组名
                mTodoAdapter.data[position].dateStr
            } else ""
        }

        // 利用RecyclerView.ItemDecoration实现顶部悬浮效果
        val decoration = StickyDecoration.Builder
                .init(groupListener)
                .setTextSideMargin(20)
                .setGroupBackground(ContextCompat.getColor(this, R.color.lightBlue))
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
                    it.drawColor(ContextCompat.getColor(this@TodoActivity,
                            R.color.colorPrimaryDark))

                    // 获取 itemview 的高度
                    val height = viewHolder?.itemView?.height ?: 1

                    // 文字的范围
                    val bounds = Rect()
                    // 获取文字的范围
                    paint.getTextBounds(text, 0, text.length, bounds)
                    // 计算出 文字 y 轴的位置。
                    // y = 总item高度的一半 + 文字的高度的一半(注意的 文字是根据左下角来绘制的.所以是加上文字高度的一半)
                    val y = height / 2.0f + bounds.height() / 2.0f
                    // 绘制文字
                    it.drawText(text, 50f, y, paint)
                }
            }

            override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                super.onItemSwiped(viewHolder, pos)
                toast("完成")
                // TODO 设置完成 todo
            }
        })

        mTodoAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.right -> {
                    toast("删除 $position")
                    adapter.remove(position)
                }
            }
        }
    }

    private fun initNavigationBar() {
        with(mNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)

            addItem(BottomNavigationItem(R.mipmap.ic_plan, R.string.plan))
            addItem(BottomNavigationItem(R.mipmap.ic_finish, R.string.finish))
            // 设置底部 BottomBar 默认选中 plan
            setFirstSelectedPosition(0)
            // 初始化
            initialise()
        }
    }

    override fun initData() {
        super.initData()

        page = 1
        mViewModel.getTodoList(page)
    }

    override fun dataObserver() {
        mViewModel.mTodoData.observe(this, Observer { response ->
            response?.data?.datas?.let { setTodoData(it) }
        })
    }

    private fun setTodoData(data: List<TodoRsp>) {

        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
        }

        mTodoAdapter.addData(data)
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

    /**
     *  右上角 类别菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.todo_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        //  全部, 工作1 生活2  娱乐3
        when (item?.itemId) {
            R.id.todo_all -> {
                toolbar.title = getString(R.string.all)
            }
            R.id.todo_work -> {
                toolbar.title = getString(R.string.work)
            }
            R.id.todo_life -> {
                toolbar.title = getString(R.string.life)
            }
            R.id.todo_play -> {
                toolbar.title = getString(R.string.play)
            }
            R.id.todo_setting -> {
                toast("设置")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() = finish()
}
