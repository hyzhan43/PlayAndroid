package zqx.rj.com.playandroid.account.data.callback

/**
 * author：  HyZhan
 * created： 2018/10/23 17:27
 * desc：    TODO
 */
interface LoginSucListener {
    fun success(collectIds: List<Int>, username: String)
}