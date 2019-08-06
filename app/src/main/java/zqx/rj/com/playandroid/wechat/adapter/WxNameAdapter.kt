package zqx.rj.com.playandroid.wechat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

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