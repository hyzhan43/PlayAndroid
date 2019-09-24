package zqx.rj.com.playandroid.other.context.callback.login

import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp

object LoginSucState {

    var listeners = ArrayList<LoginSucListener>()

    fun addListener(listener: LoginSucListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: LoginSucListener) {
        listeners.remove(listener)
    }

    fun notifyLoginState(userInfoRsp: UserInfoRsp?) {
        for (listener in listeners) {
            listener.loginSuccess(userInfoRsp)
        }
    }
}