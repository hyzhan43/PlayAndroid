package zqx.rj.com.playandroid.other.widget

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * author：  HyZhan
 * create：  2018/12/14 19:14
 * desc：    可以控制 smoothScrollToPosition  速度的 manager
 */
class SpeedLayoutManager(val context: Context?, val speed: Float = 25f) : LinearLayoutManager(context) {

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        val smoothScroller = object : androidx.recyclerview.widget.LinearSmoothScroller(context) {
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                // 返回：滑过1px时经历的时间(ms)。
                return speed / (displayMetrics?.densityDpi ?: 1)
            }
        }

        // 设置要 移动的位置
        smoothScroller.targetPosition = position
        // 开始移动
        startSmoothScroll(smoothScroller)
    }
}