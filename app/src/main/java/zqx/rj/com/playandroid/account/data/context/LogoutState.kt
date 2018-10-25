package zqx.rj.com.playandroid.account.data.context

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.context.UserState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.view.LoginActivity

/**
 * author：  HyZhan
 * created： 2018/10/18 10:00
 * desc：    未登录状态
 */
class LogoutState : UserState {

    override fun collect(context: Context?, image: ImageView) {
        jumpToLoginActivity(context)
    }

    override fun login(context: Context?) {
        jumpToLoginActivity(context)
    }

    override fun toCollectActivity(context: Context?) {
        jumpToLoginActivity(context)
    }

    // 跳转到登录
    private fun jumpToLoginActivity(context: Context?) {
        context?.let {
            it.toast(it.getString(R.string.please_login))
            it.startActivity<LoginActivity>()
        }
    }
}