package zqx.rj.com.playandroid

import android.app.Application
import com.zhan.mvvm.KtArmor
import org.litepal.LitePal
import zqx.rj.com.playandroid.other.config.RetrofitConfig

/**
 * author：  HyZhan
 * created： 2018/10/10 12:22
 * desc：    TODO
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LitePal.initialize(this)

        KtArmor.initRetrofitConfig(RetrofitConfig())
    }
}