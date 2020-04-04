package zqx.rj.com.playandroid.common

import androidx.appcompat.app.AppCompatActivity
import com.zhan.ktwing.ext.startActivity
import com.zhan.mvvm.base.IActivity
import kotlinx.coroutines.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.MainActivity

/**
 * author：  HyZhan
 * created： 2018/10/16 9:48
 * desc：    闪屏页
 */
class SplashActivity : AppCompatActivity(), IActivity {

    private lateinit var job: Job

    override fun getLayoutId(): Int = R.layout.activity_splash

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