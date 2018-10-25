package zqx.rj.com.playandroid.account.data.context

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.context.UserState
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.mine.view.activity.CollectActivity

/**
 * author：  HyZhan
 * created： 2018/10/18 9:59
 * desc：    登录状态
 */
class LoginState : UserState {

    override fun collect(context: Context?, image: ImageView) {
        context?.toast("收藏")

        // 设置选中 图标
        // image.setImageResource(R.drawable.ic_collected)
    }

    // 已登录状态 无须登录 不做任何操作
    override fun login(context: Context?) {}

    override fun toCollectActivity(context: Context?) {
        context?.startActivity<CollectActivity>()
    }
}