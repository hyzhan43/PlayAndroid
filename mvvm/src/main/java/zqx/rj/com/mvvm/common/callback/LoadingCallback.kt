package zqx.rj.com.mvvm.common.callback

import com.kingja.loadsir.callback.Callback
import zqx.rj.com.mvvm.R


/**
 * author：  HyZhan
 * created： 2018/10/10 15:19
 * desc：    加载回调
 */
class LoadingCallback: Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }
}