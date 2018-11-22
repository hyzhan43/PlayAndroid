package zqx.rj.com.mvvm.state.callback.login

/**
 * author：  HyZhan
 * created： 2018/10/23 17:27
 * desc：    登录成功的回调
 */
interface LoginSucListener {
    fun success(username: String, collectIds: List<Int>?)
}