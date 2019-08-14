package zqx.rj.com.playandroid.other.ext

import android.view.View
import zqx.rj.com.playandroid.other.utils.ClickUtil

/**
 * author：  HyZhan
 * create：  2019/8/13
 * desc：    TODO
 */

fun View.setDoubleClickListener(block: () -> Unit) {
    this.setOnClickListener { ClickUtil.interval(block) }
}