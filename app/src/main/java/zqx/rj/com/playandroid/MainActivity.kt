package zqx.rj.com.playandroid

import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kingja.loadsir.callback.SuccessCallback
import kotlinx.android.synthetic.main.activity_main.*
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.home.view.fragment.HomeFragment
import zqx.rj.com.playandroid.mine.view.fragment.MineFragment
import zqx.rj.com.playandroid.navigation.view.fragment.NavigationFragment

class MainActivity : BaseActivity() {

    private val HOME = 0
    private val NAVIGATION = 1
    private val MINE = 2

    private val mHomeFragment by lazy { HomeFragment() }
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
            addItem(BottomNavigationItem(R.mipmap.ic_navigation, getString(R.string.navigation)))
            addItem(BottomNavigationItem(R.mipmap.ic_mine, getString(R.string.mine)))

            // 设置底部 BottomBar 默认选中 home
            setFirstSelectedPosition(HOME)
            // 初始化
            initialise()

            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabSelected(position: Int) {
                    switchFragment(position)
                }

                override fun onTabReselected(position: Int) {}
                override fun onTabUnselected(position: Int) {}
            })
        }
    }

    // 设置默认选中 fragment
    private fun setDefaultFragment() {
        mCurrent = mHomeFragment
        val transaction = mFragmentManager.beginTransaction()
        transaction.add(R.id.content, mHomeFragment).commit()

        loadService.showCallback(SuccessCallback::class.java)
    }

    private fun switchFragment(position: Int) {
        when (position) {
            HOME -> {
                switch(mCurrent, mHomeFragment)
            }
            NAVIGATION -> {
                switch(mCurrent, mNavigationFragment)
            }
            MINE -> {
                switch(mCurrent, mMineFragment)
            }
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
