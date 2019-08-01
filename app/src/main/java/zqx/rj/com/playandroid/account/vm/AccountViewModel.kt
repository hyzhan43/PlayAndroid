package zqx.rj.com.playandroid.account.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.RegisterRsp
import zqx.rj.com.playandroid.account.data.repository.AccountRepository

/**
 * author：  HyZhan
 * created： 2018/10/11 14:39
 * desc：    账户 ViewModel
 */
class AccountViewModel(application: Application) : BaseViewModel<AccountRepository>(application) {

    val mLoginData = MutableLiveData<BaseResponse<LoginRsp>>()
    val mRegisterData = MutableLiveData<BaseResponse<RegisterRsp>>()

    fun getLoginData(username: String, password: String) {
        if (checkNotNull(username, password)) {
            mRepository.login(username, password, mLoginData)
        } else {
            loadState.postValue(State(StateType.TIPS, tip = R.string.accountOrPassword_empty))
        }
    }

    fun getRegister(username: String, password: String, repassword: String) {
        if (checkNotNull(username, password) && repassword == password) {
            mRepository.register(username, password, repassword, mRegisterData)
        } else {
            loadState.postValue(State(StateType.TIPS, tip = R.string.accountOrPassword_empty))
        }
    }

    // 非空判断
    private fun checkNotNull(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}