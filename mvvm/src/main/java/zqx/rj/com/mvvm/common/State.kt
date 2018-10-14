package zqx.rj.com.mvvm.common

import android.support.annotation.StringRes
import zqx.rj.com.mvvm.common.constant.StateType

/**
 * author：  HyZhan
 * created： 2018/10/12 20:50
 * desc：    状态类
 */
data class State(var code: StateType, var msg: String = "", @StringRes var tip: Int = 0)