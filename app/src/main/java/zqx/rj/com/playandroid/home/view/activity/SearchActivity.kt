package zqx.rj.com.playandroid.home.view.activity

import android.view.View
import kotlinx.android.synthetic.main.commom_bar.*
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * created： 2018/10/18 13:54
 * desc：    TODO
 */
class SearchActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()

        mIvBack.visibility = View.VISIBLE
        mIvBack.setOnClickListener { finish() }

        mTvBarTitle.text = getString(R.string.search)
    }

    override fun onBackPressed() = finish()
}