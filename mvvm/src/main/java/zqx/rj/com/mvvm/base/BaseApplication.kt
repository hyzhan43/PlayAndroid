package zqx.rj.com.mvvm.base

import android.app.Application
import com.kingja.loadsir.callback.HintCallback
import com.kingja.loadsir.callback.ProgressCallback
import com.kingja.loadsir.core.LoadSir
import zqx.rj.com.mvvm.R
import zqx.rj.com.mvvm.common.callback.ErrorCallback
import zqx.rj.com.mvvm.common.callback.LoadingCallback

/**
 * author：  HyZhan
 * created： 2018/10/10 12:22
 * desc：    TODO
 */
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        configLoadSir()
    }

    private fun configLoadSir() {

        LoadSir.beginBuilder()
                .addCallback(ErrorCallback())
                .addCallback(LoadingCallback())
                .commit()
    }

}