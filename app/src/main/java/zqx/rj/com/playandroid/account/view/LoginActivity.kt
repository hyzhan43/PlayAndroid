package zqx.rj.com.playandroid.account.view

import android.arch.lifecycle.Observer
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.common.str
import zqx.rj.com.mvvm.state.callback.login.LoginSucState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.account.data.context.LoginState
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class LoginActivity : LifecycleActivity<AccountViewModel>(), View.OnClickListener {

    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        super.initView()

        mBtnLogin.setOnClickListener(this)
        mTvRegister.setOnClickListener(this)

        showSuccess()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.mBtnLogin -> {
                mViewModel.getLoginData(mTieAccount.str(), mTiePassword.str())
            }
            R.id.mTvRegister -> {
                startActivity<RegisterActivity>()
                finish()
            }
        }
    }

    // 监听数据变化
    override fun dataObserver() {

        // 处理 repository 回调的数据
        mViewModel.mLoginData.observe(this, Observer {
            it?.data?.let { loginRsp ->
                // 标记 已登录状态  ( 改变 sharedPreferences   isLogin值)
                isLogin = true
                LoginContext.instance.mState = LoginState()

                // 登录成功 回调 -> DrawerLayout -> 个人信息更新状态
                LoginSucState.notifyLoginState(loginRsp.username, loginRsp.collectIds)

                toast(getString(R.string.login_suc))

                finish()
            }
        })
    }


    override fun onBackPressed() = finish()
}
