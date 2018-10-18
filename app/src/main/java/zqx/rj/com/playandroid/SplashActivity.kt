package zqx.rj.com.playandroid

import org.jetbrains.anko.startActivity
import rx.Observable
import rx.Subscription
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.account.view.LoginActivity
import java.util.concurrent.TimeUnit

/**
 * author：  HyZhan
 * created： 2018/10/16 9:48
 * desc：    闪屏页
 */
class SplashActivity : BaseActivity() {

    private lateinit var subscription: Subscription

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        // 延迟 3s 进入 login
        subscription = Observable.timer(3, TimeUnit.SECONDS)
                .subscribe({
                    startActivity<MainActivity>()
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription.unsubscribe()
    }
}