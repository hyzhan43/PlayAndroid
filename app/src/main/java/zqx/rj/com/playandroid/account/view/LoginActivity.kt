package zqx.rj.com.playandroid.account.view

import android.arch.lifecycle.Observer
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.callback.LoadingCallback
import zqx.rj.com.mvvm.common.str
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.callback.LoginSucListener
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.account.data.context.LoginState
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class LoginActivity : LifecycleActivity<AccountViewModel>(), View.OnClickListener {

    companion object {
        var listener: LoginSucListener? = null
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        super.initView()
        mBtnLogin.setOnClickListener(this)
        mTvRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.mBtnLogin -> {
                mViewModel.getLoginData(mTieAccount.str(), mTiePassword.str())
            }
            R.id.mTvRegister -> {
                toast(getString(R.string.register))
            }
        }
    }

    // 监听数据变化
    override fun dataObserver() {

        // 处理 repository 回调的数据
        mViewModel.getLoginData().observe(this, Observer {
            it?.data?.let {
                // 加载进度
                loadService.showCallback(LoadingCallback::class.java)
                // 标记 已登录状态
                LoginContext.instance.mState = LoginState()
                listener?.success(it.collectIds, it.username)
                toast(getString(R.string.login_suc))
                finish()
            }
        })
    }

    override fun onBackPressed() = finish()
}
