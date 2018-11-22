package zqx.rj.com.playandroid.account.data.context

import android.app.Activity
import android.content.Context
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.state.UserState
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.mvvm.state.callback.login.LoginSucState

/**
 * author：  HyZhan
 * created： 2018/10/18 9:56
 * desc：    登陆状态管理
 */
class LoginContext private constructor() {

    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    // 设置默认状态
    var mState: UserState = if (isLogin) LoginState() else LogoutState()

    companion object {
        val instance = Holder.INSTANCE
    }

    // 内部类 单利
    private object Holder {
        val INSTANCE = LoginContext()
    }

    // 收藏
    fun collect(context: Context?, position: Int, listener: CollectListener) {
        mState.collect(context, position, listener)
    }

    fun toCollectActivity(context: Context?) {
        mState.toCollectActivity(context)
    }


    fun login(context: Activity?) {
        mState.login(context)
    }

    fun loginSuccess(username: String, collectIds: List<Int>?) {
        // 改变 sharedPreferences   isLogin值
        isLogin = true
        LoginContext.instance.mState = LoginState()

        // 登录成功 回调 -> DrawerLayout -> 个人信息更新状态
        LoginSucState.notifyLoginState(username, collectIds)
    }

    fun logoutSuccess() {
        LoginContext.instance.mState = LogoutState()

        // 清除 cookie、登录缓存
        Preference.clear()

        LoginSucState.notifyLoginState("未登录", null)
    }
}