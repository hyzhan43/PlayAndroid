package zqx.rj.com.playandroid.common

import com.zhan.mvvm.base.BaseActivity
import com.zhan.mvvm.ext.startActivity
import kotlinx.coroutines.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.MainActivity

/**
 * author：  HyZhan
 * created： 2018/10/16 9:48
 * desc：    闪屏页
 */
class SplashActivity : BaseActivity() {

    private lateinit var job: Job

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        // 延迟 3s 进入 主页面
        job = CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}