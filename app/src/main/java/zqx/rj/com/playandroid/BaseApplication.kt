package zqx.rj.com.playandroid

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.zhan.mvvm.KtArmor
import org.litepal.LitePal
import zqx.rj.com.playandroid.other.callback.EmptyCallback
import zqx.rj.com.playandroid.other.callback.ErrorCallback
import zqx.rj.com.playandroid.other.callback.LoadingCallback
import zqx.rj.com.playandroid.other.config.RetrofitConfig

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
        KtArmor.init(applicationContext, RetrofitConfig())
    }

    private fun configLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(ErrorCallback())
                .addCallback(LoadingCallback())
                .addCallback(EmptyCallback())
                .commit()
    }
}