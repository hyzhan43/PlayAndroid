package zqx.rj.com.mvvm.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.kingja.loadsir.callback.SuccessCallback
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.R
import zqx.rj.com.mvvm.common.Util
import zqx.rj.com.mvvm.common.callback.LoadingCallback
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.callback.ErrorCallback

/**
 * author：  HyZhan
 * created： 2018/10/11 13:44
 * desc：    ViewModel 基类
 */
abstract class LifecycleActivity<T : BaseViewModel<*>> : BaseActivity() {

    protected lateinit var mViewModel: T

    override fun initView() {

        showLoading()

        mViewModel = ViewModelProviders.of(this).get(Util.getClass(this))
        // 设置 通用状态
        mViewModel.loadState.observe(this, observer)

        // 设置  view 的 observer
        dataObserver()
    }

    open fun showError(msg: String) {
        toast("error: $msg")
    }

    open fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    private fun showNetWork() {
        toast(getString(R.string.network_fail))
        loadService.showCallback(ErrorCallback::class.java)
    }

    open fun showLoading() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    private fun showTips(tips: Int) {
        toast(getString(tips))
    }

    abstract fun dataObserver()

    // 分发状态
    private val observer by lazy {
        Observer<State> { state ->
            state?.let {
                when (it.code) {
                    StateType.SUCCESS -> showSuccess()
                    StateType.ERROR -> showError(it.msg)
                    StateType.LOADING -> showLoading()
                    StateType.NETWORK -> showNetWork()
                    StateType.TIPS -> showTips(it.tip)
                }
            }
        }
    }
}