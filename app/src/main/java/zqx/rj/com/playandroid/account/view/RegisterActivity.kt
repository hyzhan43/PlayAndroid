package zqx.rj.com.playandroid.account.view

import androidx.lifecycle.Observer
import android.view.View
import com.zhan.mvvm.ext.Toasts.toast
import com.zhan.mvvm.ext.str
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_collect.mIcBar
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.common_bar.*
import kotlinx.android.synthetic.main.common_bar.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.UserContext
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class RegisterActivity : LifecycleActivity<AccountViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        super.initView()

        mIvSearch.visibility = View.GONE
        mIvBack.visibility = View.VISIBLE
        mIvBack.setOnClickListener { finish() }

        mIcBar.mTvBarTitle.text = getString(R.string.register)

        mBtnRegister.setOnClickListener {
            viewModel.register(mTieAccount.str(), mTiePassword.str(), mTiePasswordAg.str())
        }

        showSuccess()
    }


    override fun dataObserver() {
        viewModel.registerData.observe(this, Observer {
            toast(getString(R.string.register_suc))
            UserContext.instance.loginSuccess(it.username, it.collectIds)
            finish()
        })
    }

    override fun onBackPressed() = finish()
}
