package zqx.rj.com.playandroid.wechat.data.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

/**
 * author：  HyZhan
 * created： 2018/11/2 16:58
 * desc：    TODO
 */
class WxNameAdapter(fm: FragmentManager,
                    val titles: List<String>,
                    val fragments: List<Fragment>)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}