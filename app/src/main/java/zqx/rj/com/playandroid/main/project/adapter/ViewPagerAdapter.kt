package zqx.rj.com.playandroid.main.project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * author：  HyZhan
 * created： 2018/10/27 16:45
 * desc：    TODO
 */
class ViewPagerAdapter(private val titles: List<String>,
                       private val fragments: List<Fragment>,
                       manager: FragmentManager) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}