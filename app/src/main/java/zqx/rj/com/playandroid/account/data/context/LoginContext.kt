package zqx.rj.com.playandroid.account.data.context

import android.content.Context
import android.widget.ImageView
import zqx.rj.com.mvvm.context.UserState

/**
 * author：  HyZhan
 * created： 2018/10/18 9:56
 * desc：    登陆状态管理
 */
class LoginContext private constructor() {

    // 默认 未登录状态  logout
    var mState: UserState = LogoutState()

    companion object {
        val instance = Holder.INSTANCE
    }

    // 内部类 单利
    private object Holder {
        val INSTANCE = LoginContext()
    }

    fun collect(context: Context?, image: ImageView) {
        mState.collect(context, image)
    }
}