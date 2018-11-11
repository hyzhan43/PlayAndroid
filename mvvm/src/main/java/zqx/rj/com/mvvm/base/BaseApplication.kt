package zqx.rj.com.mvvm.base

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import org.litepal.LitePal
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.callback.EmptyCallback
import zqx.rj.com.mvvm.common.callback.ErrorCallback
import zqx.rj.com.mvvm.common.callback.LoadingCallback

/**
 * author：  HyZhan
 * created： 2018/10/10 12:22
 * desc：    TODO
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        configLoadSir()

        // 初始化数据库
        LitePal.initialize(this)

        // 初始化 SharedPreference
        Preference.setContext(applicationContext)
    }

    private fun configLoadSir() {

        LoadSir.beginBuilder()
                .addCallback(ErrorCallback())
                .addCallback(LoadingCallback())
                .addCallback(EmptyCallback())
                .commit()
    }

}