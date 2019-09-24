package zqx.rj.com.playandroid.account.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp
import zqx.rj.com.playandroid.account.data.repository.AccountRepository

/**
 * author：  HyZhan
 * created： 2018/10/11 14:39
 * desc：    账户 ViewModel
 */
class AccountViewModel : BaseViewModel<AccountRepository>() {

    val userInfoData = MutableLiveData<UserInfoRsp>()

    fun login(username: String, password: String) {
        if (username.isEmpty()) {
            showToast(R.string.account_empty)
            return
        }

        if (password.isEmpty()) {
            showToast(R.string.password_empty)
            return
        }


        launchUI({
            showLoading()
            repository.login(username, password).execute({ loginRsp ->
                loginRsp?.let { getUserScoreInfo(it) }
            })
        })
    }

    fun register(username: String, password: String, rePassword: String) {

        if (username.isEmpty()) {
            showToast(R.string.account_empty)
            return
        }

        if (password.isEmpty()) {
            showToast(R.string.password_empty)
            return
        }

        if (rePassword != password) {
            showToast(R.string.repassword_error)
            return
        }

        launchUI({
            showLoading()
            repository.register(username, password, rePassword).execute({ loginRsp ->
                loginRsp?.let { getUserScoreInfo(it) }
            })
        })
    }

    private fun getUserScoreInfo(loginRsp: LoginRsp) {
        launchUI({
            repository.getUserInfo().execute({ scoreInfoRsp ->
                scoreInfoRsp?.let { userInfoData.value = UserInfoRsp(loginRsp, it) }
            })
        })
    }
}