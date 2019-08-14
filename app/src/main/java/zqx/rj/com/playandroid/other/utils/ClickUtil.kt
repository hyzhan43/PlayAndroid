package zqx.rj.com.playandroid.other.utils

import android.view.View

/**
 * author：  HyZhan
 * create：  2019/8/13
 * desc：    TODO
 */
object ClickUtil {

    private var clickTime: Long = 0

    /**
     *  双击事件
     *  @param duation 两次间隔时间
     */
    fun interval(block: () -> Unit, duration: Int = 1000) {
        val nowTime = System.currentTimeMillis()
        if (nowTime - clickTime > duration) {
            clickTime = nowTime
        } else {
            block()
        }
    }
}