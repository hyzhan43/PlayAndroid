package zqx.rj.com.mvvm.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.R
import zqx.rj.com.mvvm.common.AppManager

/**
 * author：  HyZhan
 * created： 2018/10/10 10:47
 * desc：    TODO
 */
abstract class BaseActivity : AppCompatActivity() {

    private var pressTime: Long = 0

    protected val loadService: LoadService<*> by lazy {
        LoadSir.getDefault().register(this) {
            reLoad()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        AppManager.instance.addActivity(this)

        initView()
        initData()
    }

    abstract fun getLayoutId(): Int

    open fun initView() {}
    open fun initData() {}
    // 重新加载
    open fun reLoad() {}

    // 设置 防错误操作 退出 activity
    override fun onBackPressed() {

        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast(getString(R.string.exit_app_tips))
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }
}