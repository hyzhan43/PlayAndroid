package zqx.rj.com.mvvm.context

import android.content.Context
import android.widget.ImageView

/**
 * author：  HyZhan
 * created： 2018/10/18 9:57
 * desc：    用户状态
 */
interface UserState {

    fun collect(context: Context?, image: ImageView)
}