package zqx.rj.com.playandroid.todo.view.activity

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.playandroid.R
import zqx.rj.com.mvvm.state.callback.todo.TodoTypeContext
import zqx.rj.com.mvvm.state.callback.todo.TypeChangeListener
import zqx.rj.com.playandroid.todo.view.fragment.SettingFragment
import zqx.rj.com.playandroid.todo.view.fragment.TodoFragment


class TodoActivity : BaseActivity() {

    // 当前显示的 fragment
    private lateinit var mCurrentFragment: Fragment

    private val mTodoFragment by lazy { TodoFragment.getInstance(Constant.TODO_STATUS) }
    private val mFinishFragment by lazy { TodoFragment.getInstance(Constant.FINISH_STATUS) }
    private val mSettingFragment by lazy { SettingFragment() }

    var listener: TypeChangeListener? = null

    override fun getLayoutId(): Int = R.layout.activity_todo

    override fun initView() {
        super.initView()

        setToolBar(toolbar, getString(R.string.all))
        setDefaultFragment()
        initFloatButton()
        initNavigationBar()
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

            addItem(BottomNavigationItem(R.mipmap.ic_finish, R.string.finish))
            addItem(BottomNavigationItem(R.mipmap.ic_not_finish, R.string.todo))
            addItem(BottomNavigationItem(R.drawable.ic_setting, R.string.setting))
            // 设置底部 BottomBar 默认选中 plan
            setFirstSelectedPosition(1)
            // 初始化
            initialise()
            // 设置 button 点击事件
            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {}

                override fun onTabUnselected(position: Int) {}

                override fun onTabSelected(position: Int) {
                    when (position) {
                        Constant.FINISH -> goTo(mFinishFragment)
                        Constant.TODO -> goTo(mTodoFragment)
                        Constant.SETTING -> goTo(mSettingFragment)
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
                // 通知 子 fragment 更新数据
                TodoTypeContext.notifyTodoTypeChange(0)
            }
            R.id.todo_work -> {
                toolbar.title = getString(R.string.work)
                TodoTypeContext.notifyTodoTypeChange(1)
            }
            R.id.todo_study -> {
                toolbar.title = getString(R.string.study)
                TodoTypeContext.notifyTodoTypeChange(2)
            }
            R.id.todo_life -> {
                toolbar.title = getString(R.string.life)
                TodoTypeContext.notifyTodoTypeChange(3)
            }
            R.id.todo_setting -> {
                toast("设置")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() = finish()
}
