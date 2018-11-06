package zqx.rj.com.playandroid.account.view

import android.arch.lifecycle.Observer
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.common_bar.*
import kotlinx.android.synthetic.main.common_bar.view.*
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.common.str
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.account.data.context.LoginState
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class RegisterActivity : LifecycleActivity<AccountViewModel>() {

    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        super.initView()

        mIvSearch.visibility = View.GONE
        mIvBack.visibility = View.VISIBLE
        mIvBack.setOnClickListener { finish() }

        mIcBar.mTvBarTitle.text = getString(R.string.register)

        mBtnRegister.setOnClickListener {
            mViewModel.getRegister(mTieAccount.str(), mTiePassword.str(), mTiePasswordAg.str())
        }

        showSuccess()
    }


    override fun dataObserver() {
        mViewModel.mRegisterData.observe(this, Observer {
            toast(getString(R.string.register_suc))
            // 改变 sharedPreferences   isLogin值
            isLogin = true
            LoginContext.instance.mState = LoginState()

            it?.let { LoginActivity.listener?.success(it.data.username) }

            finish()
        })
    }

    override fun onBackPressed() = finish()
}
