package zqx.rj.com.playandroid

import io.reactivex.Observable
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.BaseActivity
import java.util.concurrent.TimeUnit

/**
 * author：  HyZhan
 * created： 2018/10/16 9:48
 * desc：    闪屏页
 */
class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        // 延迟 3s 进入 login
        disposable = Observable.timer(3, TimeUnit.SECONDS)
                .subscribe { startActivity<MainActivity>() }
    }
}