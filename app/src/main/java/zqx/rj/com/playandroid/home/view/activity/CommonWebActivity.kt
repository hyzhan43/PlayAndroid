package zqx.rj.com.playandroid.home.view.activity

import android.arch.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.android.synthetic.main.common_bar.view.*
import kotlinx.android.synthetic.main.common_tag.view.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.WebViewActivity
import zqx.rj.com.playandroid.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.home.vm.HomeViewModel

/**
 * author：  HyZhan
 * created： 2018/10/21 15:49
 * desc：    常用网站
 */
class CommonWebActivity : LifecycleActivity<HomeViewModel>() {

    private val links = arrayListOf<String>()

    override fun getLayoutId(): Int = R.layout.activity_common_web

    override fun initView() {
        super.initView()

        mIcBar.mIvBack.visibility = View.VISIBLE
        mIcBar.mIvBack.setOnClickListener { finish() }
        mIcBar.mTvBarTitle.text = getString(R.string.common_websites)
        mIcBar.mIvSearch.visibility = View.GONE
    }

    override fun initData() {
        mViewModel.getCommonWeb()
    }

    override fun dataObserver() {
        mViewModel.mCommonWebData.observe(this, Observer { response ->
            response?.let { initCommonWeb(it.data) }
        })
    }

    private fun initCommonWeb(dataList: List<CommonWebRsp>) {

        val tags = arrayListOf<String>()

        for (commonWebRsp in dataList) {
            tags.add(commonWebRsp.name)
            links.add(commonWebRsp.link)
        }

        mTflCommonWeb.adapter = object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout?, position: Int, tag: String?): View {

                val mTagLayout = LayoutInflater.from(this@CommonWebActivity)
                        .inflate(R.layout.common_tag, parent, false)

                mTagLayout.mTvTag.text = tag
                return mTagLayout
            }
        }

        mTflCommonWeb.setOnTagClickListener { _, position, _ ->
            startActivity<WebViewActivity>("link" to links[position], "title" to tags[position])
            true
        }
    }


    override fun onBackPressed() = finish()
}