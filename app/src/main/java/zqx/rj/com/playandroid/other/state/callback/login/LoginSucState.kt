package zqx.rj.com.playandroid.other.state.callback.login

import zqx.rj.com.mvvm.state.callback.login.LoginSucListener

object LoginSucState {

    var listeners = ArrayList<LoginSucListener>()

    fun addListener(listener: LoginSucListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: LoginSucListener) {
        listeners.remove(listener)
    }

    fun notifyLoginState(name: String, collectIds: List<Int>?) {
        for (listener in listeners) {
            listener.success(name, collectIds)
        }
    }
}