package zqx.rj.com.playandroid.account.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.common.SharedData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.RegisterRsp
import zqx.rj.com.playandroid.account.data.repository.AccountRepository

/**
 * author：  HyZhan
 * created： 2018/10/11 14:39
 * desc：    账户 ViewModel
 */
class AccountViewModel : BaseViewModel<AccountRepository>() {

    val loginData = MutableLiveData<LoginRsp>()
    val registerData = MutableLiveData<RegisterRsp>()

    fun login(username: String, password: String) {
        if (username.isEmpty()) {
            sharedData.value = SharedData(strRes = R.string.account_empty)
            return
        }

        if (password.isEmpty()) {
            sharedData.value = SharedData(strRes = R.string.password_empty)
            return
        }

        launchUI({
            repository.login(username, password).execute({ loginData.value = it })
        })
    }

    fun register(username: String, password: String, rePassword: String) {

        if (username.isEmpty()) {
            sharedData.value = SharedData(strRes = R.string.account_empty)
            return
        }

        if (password.isEmpty()) {
            sharedData.value = SharedData(strRes = R.string.password_empty)
            return
        }

        if (rePassword != password) {
            sharedData.value = SharedData(strRes = R.string.repassword_error)
            return
        }

        launchUI({
            repository.register(username, password, rePassword).execute({
                registerData.value = it
            })
        })
    }
}