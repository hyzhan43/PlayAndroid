package zqx.rj.com.playandroid.other.context.state

import android.app.Activity
import android.content.Context
import zqx.rj.com.playandroid.other.context.callback.collect.CollectListener

/**
 * author：  HyZhan
 * created： 2018/10/18 9:57
 * desc：    用户状态
 */
interface UserState {

    fun collect(context: Context?, position: Int, listener: CollectListener)

    fun login(context: Context?)

    fun goTodoActivity(context: Context?)

    fun goCollectActivity(context: Context?)

    fun logout(activity: Activity)
}