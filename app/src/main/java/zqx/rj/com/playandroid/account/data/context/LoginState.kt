package zqx.rj.com.playandroid.account.data.context

import android.content.Context
import com.zhan.mvvm.ext.startActivity
import zqx.rj.com.mvvm.state.UserState
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.playandroid.mine.view.activity.CollectActivity
import zqx.rj.com.playandroid.todo.view.activity.TodoActivity

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

    override fun goCollectActivity(context: Context?) {
        context?.startActivity<CollectActivity>()
    }
}