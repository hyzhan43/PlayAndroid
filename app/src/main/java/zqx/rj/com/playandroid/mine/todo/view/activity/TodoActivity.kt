package zqx.rj.com.playandroid.mine.todo.view.activity

import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.zhan.mvvm.base.ToolbarActivity
import com.zhan.mvvm.common.Preference
import com.zhan.mvvm.ext.startActivity
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import zqx.rj.com.playandroid.other.context.callback.todo.TodoContext
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.mine.todo.view.fragment.TodoFragment
import zqx.rj.com.playandroid.other.widget.adapter.BottomNavigationAdapter


class TodoActivity : ToolbarActivity() {

    // 当前显示的 fragment
    private lateinit var mCurrentFragment: Fragment

    // 当前 类型 工作1  学习2 生活3  0 默认全部
    private var type by Preference(Const.TODO_TYPE, 0)

    private val mTodoFragment by lazy {
        TodoFragment.newInstance(
            Const.TODO_STATUS,
            getString(R.string.finish),
            R.color.colorPrimaryDark
        )
    }

    private val mFinishFragment by lazy {
        TodoFragment.newInstance(
            Const.FINISH_STATUS,
            getString(R.string.reduction),
            R.color.green_500
        )
    }

    override fun getLayoutId(): Int = R.layout.activity_todo

    override fun initView() {
        super.initView()

        toolbarTitle = getStringType(type)
        setDefaultFragment()
        initNavigationBar()
    }

    private fun getStringType(type: Int): String {
        return when (type) {
            Const.ALL -> getString(R.string.all)
            Const.WORK -> getString(R.string.work)
            Const.STUDY -> getString(R.string.study)
            Const.LIFE -> getString(R.string.life)
            else -> getString(R.string.all)
        }
    }

    /**
     *  设置默认 fragment为 待办todo fragment
     */
    private fun setDefaultFragment() {
        mCurrentFragment = mTodoFragment
        supportFragmentManager.beginTransaction()
            .add(R.id.mContent, mTodoFragment).commit()
    }

    override fun initListener() {
        super.initListener()
        mFabAdd.setOnClickListener { startActivity<AddTodoActivity>() }
    }

    private fun initNavigationBar() {
        with(mNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)

            addItem(BottomNavigationItem(R.mipmap.ic_todo, R.string.todo))
            addItem(BottomNavigationItem(R.mipmap.ic_finish, R.string.finish))
            // 设置底部 BottomBar 默认选中 plan
            setFirstSelectedPosition(0)
            // 初始化
            initialise()
            // 设置 button 点击事件
            setTabSelectedListener(object : BottomNavigationAdapter() {
                override fun onTabSelected(position: Int) = switchFragment(position)
            })
        }
    }

    private fun switchFragment(position: Int) {
        when (position) {
            Const.TODO -> goTo(mTodoFragment)
            Const.FINISH -> goTo(mFinishFragment)
        }
    }

    // 复用 fragment
    private fun goTo(to: Fragment) {
        if (mCurrentFragment == to) {
            return
        }

        with(supportFragmentManager.beginTransaction()) {
            hide(mCurrentFragment)
            if (to.isAdded) {
                show(to)
            } else {
                add(R.id.mContent, to)
            }
            commit()
            mCurrentFragment = to
        }
    }

    /**
     *  右上角 类别菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.todo_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //  全部, 工作1  学习2 生活3
        return when (item?.itemId) {
            R.id.todo_all -> changeFragment(R.string.all, Const.ALL)
            R.id.todo_work -> changeFragment(R.string.work, Const.WORK)
            R.id.todo_study -> changeFragment(R.string.study, Const.STUDY)
            R.id.todo_life -> changeFragment(R.string.life, Const.LIFE)
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeFragment(titleRes: Int, type: Int): Boolean {
        toolbar.title = getString(titleRes)
        //通知 子 fragment 更新数据
        TodoContext.notifyTodoTypeChange(type)
        return true
    }

    override fun onBackPressed() = finish()
}
