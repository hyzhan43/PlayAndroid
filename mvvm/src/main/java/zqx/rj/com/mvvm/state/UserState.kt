package zqx.rj.com.mvvm.state

import android.content.Context
import zqx.rj.com.mvvm.state.callback.collect.CollectListener

/**
 * author：  HyZhan
 * created： 2018/10/18 9:57
 * desc：    用户状态
 */
interface UserState {

    fun collect(context: Context?, position: Int, listener: CollectListener)

    fun login(context: Context?)

    fun toCollectActivity(context: Context?)
}