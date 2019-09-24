package zqx.rj.com.playandroid.main

import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.zhan.mvvm.base.ToolbarActivity
import com.zhan.mvvm.ext.Toasts.toast
import com.zhan.mvvm.ext.startActivity
import com.zhan.mvvm.ext.str
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import zqx.rj.com.playandroid.other.context.callback.login.LoginSucListener
import zqx.rj.com.playandroid.other.context.callback.login.LoginSucState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp
import zqx.rj.com.playandroid.other.context.UserContext
import zqx.rj.com.playandroid.common.search.view.SearchActivity
import zqx.rj.com.playandroid.main.home.view.fragment.HomeFragment
import zqx.rj.com.playandroid.mine.AboutActivity
import zqx.rj.com.playandroid.main.navigation.view.fragment.NavigationFragment
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.main.project.view.fragment.ProjectFragment
import zqx.rj.com.playandroid.main.system.view.fragment.SystemFragment
import zqx.rj.com.playandroid.main.wechat.view.fragment.WeChatFragment
import zqx.rj.com.playandroid.other.ext.setDoubleClickListener
import zqx.rj.com.playandroid.other.persistence.Account
import zqx.rj.com.playandroid.other.widget.adapter.BottomNavigationAdapter

class MainActivity : ToolbarActivity(), LoginSucListener {

    private lateinit var headView: View

    private val mHomeFragment by lazy { HomeFragment() }
    private val mWeChatFragment by lazy { WeChatFragment() }
    private val mSystemFragment by lazy { SystemFragment() }
    private val mNavigationFragment by lazy { NavigationFragment() }
    private val mProjectFragment by lazy { ProjectFragment() }

    // 当前显示的 fragment
    private lateinit var mCurrentFragment: Fragment

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()

        initToolBar()
        initDrawerLayout()
        initNavigationBar()
        setDefaultFragment()
    }

    private fun initToolBar() {
        // 设置标题
        toolbarTitle = getString(R.string.app_name)

        //设置导航图标、按钮有旋转特效
        val toggle =
            ActionBarDrawerToggle(this, mDrawerMain, toolbar, R.string.app_name, R.string.app_name)
        mDrawerMain.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun initListener() {

        /**
         *  双击 toolbar  返回 顶部
         */
        toolbar.setDoubleClickListener { mHomeFragment.moveToTop() }

        mFabAdd.setOnClickListener { UserContext.goTodoActivity(this) }
    }

    private fun initDrawerLayout() {

        // 设置 登录成功 监听
        LoginSucState.addListener(this)

        initHeadView()

        mNavMain.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_collect -> UserContext.goCollectActivity(this)
                R.id.nav_menu_todo -> UserContext.goTodoActivity(this)
                R.id.nav_menu_about -> startActivity<AboutActivity>()
                R.id.nav_menu_setting -> toast(getString(R.string.setting))
                R.id.nav_menu_logout -> UserContext.logout(this)
            }

            // 关闭侧边栏
            mDrawerMain.closeDrawers()
            true
        }
    }

    private fun initHeadView() {
        // 直接获取报错   error -> mNavMain.mTvName
        headView = mNavMain.getHeaderView(0)

        updateUserInfo()
        // 点击 登录
        headView.mCivIcon.setOnClickListener { UserContext.login(this) }
    }

    private fun initNavigationBar() {
        with(mNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)

            addItem(BottomNavigationItem(R.mipmap.ic_home, R.string.home))
            addItem(BottomNavigationItem(R.mipmap.ic_wechat, R.string.wechat))
            addItem(BottomNavigationItem(R.mipmap.ic_system, R.string.system))
            addItem(BottomNavigationItem(R.mipmap.ic_navigation, R.string.navigation))
            addItem(BottomNavigationItem(R.mipmap.ic_project, R.string.project))

            // 设置底部 BottomBar 默认选中 home
            setFirstSelectedPosition(Const.HOME)
            // 初始化
            initialise()

            setTabSelectedListener(object : BottomNavigationAdapter() {
                override fun onTabSelected(position: Int) = switchFragment(position)
            })
        }
    }

    /**
     *  创建 searchAsync 搜索 icon
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 设置 toolbar   searchAsync 图标
        // searchAsync 图标大小 -> 通过 drawable-hdpi 文件夹  还有 原图片大小来设置这里 32dp
        // 如果直接放入 drawable 会偏大
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    // 设置默认选中 fragment
    private fun setDefaultFragment() {
        mCurrentFragment = mHomeFragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mContent, mHomeFragment).commit()
    }

    private fun switchFragment(position: Int) {
        when (position) {
            Const.HOME -> goTo(R.string.home, mHomeFragment)
            Const.WECHAT -> goTo(R.string.wechat, mWeChatFragment)
            Const.SYSTEM -> goTo(R.string.system, mSystemFragment)
            Const.NAVIGATION -> goTo(R.string.navigation, mNavigationFragment)
            Const.PROJECT -> goTo(R.string.project, mProjectFragment)
        }
    }

    // 复用 fragment
    private fun goTo(titleRes: Int, to: Fragment) {
        toolbarTitle = getString(titleRes)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            //将滑动菜单显示出来
            android.R.id.home -> {
                mDrawerMain.openDrawer(Gravity.START)
                return true
            }
            // 跳转到 搜索
            R.id.action_search -> {
                startActivity<SearchActivity>()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // 登录成功 回调
    override fun loginSuccess(userInfoRsp: UserInfoRsp?) {
        userInfoRsp?.run {
            Account.username = username
            Account.score = coinCount
            Account.rank = rank

            updateUserInfo()
        } ?: updateUserInfo(Const.NOT_LOGIN_MSG, 0, 0)
    }

    private fun updateUserInfo(
        username: String = Account.username,
        score: Int = Account.score,
        rank: Int = Account.rank
    ) {
        with(headView) {
            mTvName.text = username
            mTvScore.text = String.format(getString(R.string.score), score)
            mTvGrade.text = String.format(getString(R.string.grade), (score / 100 + 1))
            mTvRank.text = String.format(getString(R.string.rank), rank)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSucState.removeListener(this)
    }
}
