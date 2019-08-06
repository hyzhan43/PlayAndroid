package zqx.rj.com.playandroid.common

import com.zhan.mvvm.base.BaseActivity
import com.zhan.mvvm.ext.startActivity
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.view.activity.MainActivity

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
        // 延迟 3s 进入 loginAsync
//        disposable = Observable.timer(3, TimeUnit.SECONDS)
//                .subscribe { startActivity<MainActivity>() }

        startActivity<MainActivity>()
    }
}