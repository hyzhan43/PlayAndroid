package zqx.rj.com.mvvm.common.callback

import com.kingja.loadsir.callback.Callback
import zqx.rj.com.mvvm.R

/**
 * author：  HyZhan
 * created： 2018/10/10 15:43
 * desc：    TODO
 */
class ErrorCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_error
}