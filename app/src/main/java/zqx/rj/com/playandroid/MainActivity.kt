package zqx.rj.com.playandroid

import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.home.view.fragment.HomeFragment
import zqx.rj.com.playandroid.mine.view.fragment.MineFragment
import zqx.rj.com.playandroid.navigation.view.fragment.NavigationFragment
import zqx.rj.com.playandroid.system.view.fragment.SystemFragment

class MainActivity : BaseActivity() {

    private val HOME = 0
    private val SYSTEM = 1
    private val NAVIGATION = 2
    private val MINE = 3

    private val mHomeFragment by lazy { HomeFragment() }
    private val mSystemFragment by lazy { SystemFragment() }
    private val mNavigationFragment by lazy { NavigationFragment() }
    private val mMineFragment by lazy { MineFragment() }

    private lateinit var mCurrent: Fragment
    private val mFragmentManager by lazy { supportFragmentManager }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        initNavigationBar()
        setDefaultFragment()
    }

    private fun initNavigationBar() {
        with(mNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)

            addItem(BottomNavigationItem(R.mipmap.ic_home_active, getString(R.string.home)))
            addItem(BottomNavigationItem(R.mipmap.ic_system, getString(R.string.system)))
            addItem(BottomNavigationItem(R.mipmap.ic_navigation, getString(R.string.navigation)))
            addItem(BottomNavigationItem(R.mipmap.ic_mine, getString(R.string.mine)))

            // 设置底部 BottomBar 默认选中 home
            setFirstSelectedPosition(HOME)
            // 初始化
            initialise()

            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {}
                override fun onTabUnselected(position: Int) {}

                override fun onTabSelected(position: Int) = switchFragment(position)
            })
        }
    }

    // 设置默认选中 fragment
    private fun setDefaultFragment() {
        mCurrent = mHomeFragment
        val transaction = mFragmentManager.beginTransaction()
        transaction.add(R.id.content, mHomeFragment).commit()
    }

    private fun switchFragment(position: Int) {
        when (position) {
            HOME -> switch(mCurrent, mHomeFragment)
            SYSTEM -> switch(mCurrent, mSystemFragment)
            NAVIGATION -> switch(mCurrent, mNavigationFragment)
            MINE -> switch(mCurrent, mMineFragment)
        }
    }

    // 复用 fragment
    private fun switch(from: Fragment, to: Fragment) {
        if (mCurrent != to) {
            mCurrent = to
            val transaction = mFragmentManager.beginTransaction()
            if (to.isAdded)
                transaction.hide(from).show(to)
            else
                transaction.hide(from).add(R.id.content, to)
            transaction.commit()
        }
    }

}
