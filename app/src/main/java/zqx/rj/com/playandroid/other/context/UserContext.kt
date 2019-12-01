package zqx.rj.com.playandroid.other.context

import android.app.Activity
import android.content.Context
import com.zhan.ktwing.common.Preference
import zqx.rj.com.playandroid.account.data.bean.UserInfoRsp
import zqx.rj.com.playandroid.other.constant.Key
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener
import zqx.rj.com.playandroid.other.context.callback.login.LoginSucState
import zqx.rj.com.playandroid.other.context.state.LoginState
import zqx.rj.com.playandroid.other.context.state.LogoutState
import zqx.rj.com.playandroid.other.context.state.UserState

/**
 * author：  HyZhan
 * created： 2018/10/18 9:56
 * desc：    用户状态管理(登录/未登录) -> (状态设计模式)
 */
object UserContext{

    private var isLogin: Boolean by Preference(Key.LOGIN, false)

    // 设置默认状态
    var mState: UserState = if (isLogin) LoginState() else LogoutState()

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

    fun logout(activity: Activity){
        mState.logout(activity)
    }

    fun setLoginState(){
        // 改变 sharedPreferences   isLogin值
        isLogin = true
        mState = LoginState()
    }

    fun setLogoutState(){
        // 改变 sharedPreferences   isLogin值
        isLogin = false
        mState = LogoutState()
    }


    fun loginSuccess(userInfoRsp: UserInfoRsp) {
        // 改变 sharedPreferences   isLogin值
        isLogin = true
        mState = LoginState()

        // 登录成功 回调 -> DrawerLayout -> 个人信息更新状态
        LoginSucState.notifyLoginState(userInfoRsp)
    }


}