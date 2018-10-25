package zqx.rj.com.mvvm.common.callback

import com.kingja.loadsir.callback.Callback
import zqx.rj.com.mvvm.R

/**
 * author：  HyZhan
 * created： 2018/10/25 14:02
 * desc：    TODO
 */
class EmptyCallback: Callback(){
    override fun onCreateView(): Int = R.layout.layout_empty
}