package zqx.rj.com.playandroid.main.home.view.activity

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.zhan.mvvm.ext.startActivity
import com.zhan.mvvm.mvvm.LifecycleActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.android.synthetic.main.common_tag.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.main.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.main.home.vm.HomeViewModel
import zqx.rj.com.playandroid.other.constant.Key

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

        toolbarTitle = getString(R.string.common_websites)
    }

    override fun initData() {
        super.initData()
        viewModel.getCommonWeb()
    }

    override fun dataObserver() {
        viewModel.commonWebData.observe(this, Observer {
            initCommonWeb(it)
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

                return LayoutInflater.from(this@CommonWebActivity)
                        .inflate(R.layout.common_tag, parent, false)
                        .apply { mTvTag.text = tag }
            }
        }

        mTflCommonWeb.setOnTagClickListener { _, position, _ ->
            startActivity<WebViewActivity>(Key.LINK to links[position], Key.TITLE to tags[position])
            true
        }
    }


    override fun onBackPressed() = finish()
}