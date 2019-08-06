package zqx.rj.com.playandroid.account.data.context

import android.app.Activity
import android.content.Context
import com.zhan.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.state.UserState
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.mvvm.state.callback.login.LoginSucState

/**
 * author：  HyZhan
 * created： 2018/10/18 9:56
 * desc：    用户状态管理(登录/未登录) -> (状态设计模式)
 */
class UserContext private constructor() {

    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    // 设置默认状态
    var mState: UserState = if (isLogin) LoginState() else LogoutState()

    companion object {
        val instance = Holder.INSTANCE
    }

    // 内部类 单利
    private object Holder {
        val INSTANCE = UserContext()
    }

    // 收藏
    fun collect(context: Context?, position: Int, listener: CollectListener) {
        mState.collect(context, position, listener)
    }

    fun goCollectActivity(context: Context?) {
        mState.goCollectActivity(context)
    }

    // 跳转到todo
    fun goTodoActivity(context: Context?) {
        mState.goTodoActivity(context)
    }

    // 跳转去登录
    fun login(context: Activity?) {
        mState.login(context)
    }

    fun loginSuccess(username: String, collectIds: List<Int>?) {
        // 改变 sharedPreferences   isLogin值
        isLogin = true
        UserContext.instance.mState = LoginState()

        // 登录成功 回调 -> DrawerLayout -> 个人信息更新状态
        LoginSucState.notifyLoginState(username, collectIds)
    }

    fun logoutSuccess() {
        UserContext.instance.mState = LogoutState()

        // 清除 cookie、登录缓存
        Preference.clear()

        LoginSucState.notifyLoginState("未登录", null)
    }
}