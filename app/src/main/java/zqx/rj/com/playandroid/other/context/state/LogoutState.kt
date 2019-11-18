package zqx.rj.com.playandroid.other.context.state

import android.app.Activity
import android.content.Context
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.startActivity
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.view.LoginActivity

/**
 * author：  HyZhan
 * created： 2018/10/18 10:00
 * desc：    未登录状态
 */
class LogoutState : UserState {
    override fun goTodoActivity(context: Context?) {
        goLoginActivity(context)
    }

    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        goLoginActivity(context)
    }

    override fun login(context: Context?) {
        goLoginActivity(context)
    }

    override fun logout(activity: Activity) {

    }

    override fun goCollectActivity(context: Context?) {
        goLoginActivity(context)
    }

    // 跳转到登录
    private fun goLoginActivity(context: Context?) {
        context?.run {
            toast(getString(R.string.please_login))
            startActivity<LoginActivity>()
        }
    }
}
