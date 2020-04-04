package zqx.rj.com.playandroid.main.home.view.activity

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.startActivity
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.android.synthetic.main.common_tag.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.WebViewActivity
import zqx.rj.com.playandroid.main.home.data.bean.CommonWebData
import zqx.rj.com.playandroid.main.home.vm.HomeViewModel
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * created： 2018/10/21 15:49
 * desc：    常用网站
 */
class CommonWebActivity : AppCompatActivity(), IMvmActivity {

    @BindViewModel
    lateinit var viewModel: HomeViewModel

    override fun getLayoutId(): Int = R.layout.activity_common_web

    override fun initView() {
        super.initView()

        // TODO Toolbar
        //toolbarTitle = getString(R.string.common_websites)
    }

    override fun initData() {
        super.initData()
        viewModel.getCommonWeb()
    }

    override fun dataObserver() {
        viewModel.commonWebLiveData.observe(this, Observer {
            initCommonWeb(it)
        })
    }

    private fun initCommonWeb(commonWebData: CommonWebData) {

        val tags = commonWebData.tags
        val links = commonWebData.links

        mTflCommonWeb.adapter = object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout?, position: Int, tag: String?): View {

                return LayoutInflater.from(this@CommonWebActivity)
                    .inflate(R.layout.common_tag, parent, false)
                    .apply { mTvTag.text = tag }
            }
        }

        mTflCommonWeb.setOnTagClickListener { _, position, _ ->
            startActivity<WebViewActivity>(
                Key.LINK to links[position],
                Key.TITLE to tags[position]
            )
            true
        }
    }

    override fun onBackPressed() = finish()
}