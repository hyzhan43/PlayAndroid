package zqx.rj.com.playandroid.other.context.state

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.zhan.ktwing.common.Preference
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.startActivity
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener
import zqx.rj.com.playandroid.mine.collect.view.activity.CollectActivity
import zqx.rj.com.playandroid.mine.todo.view.activity.TodoActivity
import zqx.rj.com.playandroid.other.context.UserContext
import zqx.rj.com.playandroid.other.context.callback.login.LoginSucState

/**
 * author：  HyZhan
 * created： 2018/10/18 9:59
 * desc：    登录状态
 */
class LoginState : UserState {

    // 登录状态 直接跳转到todo 界面
    override fun goTodoActivity(context: Context?) {
        context?.startActivity<TodoActivity>()
    }

    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        // 发起收藏
        listener.collect(position)
    }

    // 已登录状态 无须登录 不做任何操作
    override fun login(context: Context?) {}

    override fun logout(activity: Activity) {
        AlertDialog.Builder(activity)
            .setTitle(R.string.logout)
            .setMessage(R.string.logout_tips)
            .setPositiveButton(R.string.cancel, null)
            .setNegativeButton(R.string.right) { _, _ -> clearLoginData(activity) }.show()
    }

    private fun clearLoginData(activity: Activity) {
        UserContext.setLogoutState()

        // 清除 cookie、登录缓存
        Preference.clear()

        LoginSucState.notifyLoginState(null)
        activity.toast(R.string.logout_success)
    }

    override fun goCollectActivity(context: Context?) {
        context?.startActivity<CollectActivity>()
    }
}