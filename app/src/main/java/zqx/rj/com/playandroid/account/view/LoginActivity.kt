package zqx.rj.com.playandroid.account.view

import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.logd
import com.zhan.ktwing.ext.startActivity
import com.zhan.ktwing.ext.str
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_login.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.LoginIdlingResource
import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp
import zqx.rj.com.playandroid.other.context.UserContext
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class LoginActivity : LifecycleActivity<AccountViewModel>(){

    val mIdlingResource by lazy { LoginIdlingResource() }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initListener() {
        super.initListener()

        mBtnLogin.setOnClickListener {
            //耗时操作开始，设置空闲状态为false，阻塞测试线程
            mIdlingResource.setIdleState(false)
            viewModel.login(mTieAccount.str(), mTiePassword.str())
        }

        mTvRegister.setOnClickListener {
            startActivity<RegisterActivity>()
            finish()
        }
    }

    // 监听数据变化
    override fun dataObserver() {
        // 处理 repository 回调的数据
        viewModel.userInfoData.observe(this, Observer { loginSuccess(it) })
    }

    override fun showToast(msg: String) {
        super.showToast(msg)
        mIdlingResource.setIdleState(true)
    }

    private fun loginSuccess(userInfoRsp: UserInfoRsp) {
        UserContext.loginSuccess(userInfoRsp)

        hideLoading()
        toast(getString(R.string.login_suc))
        finish()

        mIdlingResource.setIdleState(true)
    }

    override fun onBackPressed() = finish()
}
