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


class TodoActivity : ToolbarActivity() {

    // 当前显示的 fragment
    private lateinit var mCurrentFragment: Fragment

    // 当前 类型 工作1  学习2 生活3  0 默认全部
    // SharedPreference 保存type类型
    private var type by Preference(Const.TODO_TYPE, 0)

    private val mTodoFragment by lazy {
        TodoFragment.getInstance(Const.TODO_STATUS, getString(R.string.finish), R.color.colorPrimaryDark)
    }

    private val mFinishFragment by lazy {
        TodoFragment.getInstance(Const.FINISH_STATUS, getString(R.string.reduction), R.color.green_500)
    }

    override fun getLayoutId(): Int = R.layout.activity_todo

    override fun initView() {
        super.initView()

        toolbarTitle =  getStringType(type)
        setDefaultFragment()
        initFloatButton()
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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content, mTodoFragment).commit()
    }

    private fun initFloatButton() {
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
            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {}

                override fun onTabUnselected(position: Int) {}

                override fun onTabSelected(position: Int) {
                    when (position) {
                        Const.TODO -> goTo(mTodoFragment)
                        Const.FINISH -> goTo(mFinishFragment)
                    }
                }
            })
        }
    }

    // 复用 fragment
    private fun goTo(to: Fragment) {
        if (mCurrentFragment != to) {
            val transaction = supportFragmentManager.beginTransaction()
            if (to.isAdded)
                transaction.hide(mCurrentFragment).show(to)
            else
                transaction.hide(mCurrentFragment).add(R.id.content, to)
            transaction.commit()
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
        when (item?.itemId) {
            R.id.todo_all -> {
                toolbar.title = getString(R.string.all)
                //通知 子 fragment 更新数据
                TodoContext.notifyTodoTypeChange(Const.ALL)
            }
            R.id.todo_work -> {
                toolbar.title = getString(R.string.work)
                TodoContext.notifyTodoTypeChange(Const.WORK)
            }
            R.id.todo_study -> {
                toolbar.title = getString(R.string.study)
                TodoContext.notifyTodoTypeChange(Const.STUDY)
            }
            R.id.todo_life -> {
                toolbar.title = getString(R.string.life)
                TodoContext.notifyTodoTypeChange(Const.LIFE)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() = finish()
}
