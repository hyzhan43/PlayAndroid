package zqx.rj.com.playandroid.account.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.str
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmActivity
import kotlinx.android.synthetic.main.activity_register.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.LoginIdlingResource
import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp
import zqx.rj.com.playandroid.other.context.UserContext
import zqx.rj.com.playandroid.account.vm.AccountViewModel

class RegisterActivity : AppCompatActivity(), IMvmActivity {

    val mIdlingResource by lazy { LoginIdlingResource() }

    @BindViewModel
    lateinit var viewModel: AccountViewModel

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        super.initView()

        //toolbarTitle = getString(R.string.register)
    }

    override fun initListener() {
        mBtnRegister.setOnClickListener {
            mIdlingResource.isNotIdleState()
            viewModel.register(mTieAccount.str(), mTiePassword.str(), mTiePasswordAg.str())
        }
    }

    override fun dataObserver() {
        viewModel.userInfoData.observe(this, Observer { registerSuccess(it) })
    }

    private fun registerSuccess(userInfoRsp: UserInfoRsp) {
        toast(getString(R.string.register_suc))
        UserContext.loginSuccess(userInfoRsp)
        finish()
        mIdlingResource.isIdleState()
    }

    override fun onBackPressed() = finish()
}
