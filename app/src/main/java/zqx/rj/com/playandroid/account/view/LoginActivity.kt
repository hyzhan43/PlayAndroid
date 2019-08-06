package zqx.rj.com.playandroid.account.view

import android.view.View
import androidx.lifecycle.Observer
import com.zhan.mvvm.ext.Toasts.toast
import com.zhan.mvvm.ext.startActivity
import com.zhan.mvvm.ext.str
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_login.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.context.UserContext
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class LoginActivity : LifecycleActivity<AccountViewModel>(), View.OnClickListener {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        super.initView()

        mBtnLogin.setOnClickListener(this)
        mTvRegister.setOnClickListener(this)

        showSuccess()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.mBtnLogin -> viewModel.login(mTieAccount.str(), mTiePassword.str())
            R.id.mTvRegister -> {
                startActivity<RegisterActivity>()
                finish()
            }
        }
    }

    // 监听数据变化
    override fun dataObserver() {
        // 处理 repository 回调的数据
        viewModel.loginData.observe(this, Observer {
            UserContext.instance.loginSuccess(it.username, it.collectIds)
            toast(getString(R.string.login_suc))
            finish()
        })
    }

    override fun onBackPressed() = finish()
}
