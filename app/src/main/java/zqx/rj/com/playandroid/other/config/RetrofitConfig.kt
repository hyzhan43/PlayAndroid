package zqx.rj.com.playandroid.other.config

import com.zhan.mvvm.http.BaseRetrofitConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import zqx.rj.com.playandroid.other.api.API
import zqx.rj.com.playandroid.other.interceptor.CookieInterceptor

/**
 * author：  HyZhan
 * create：  2019/8/6
 * desc：    TODO
 */
class RetrofitConfig : BaseRetrofitConfig() {
    override val baseUrl: String
        get() = API.BASE_URL

    override fun initOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        return super.initOkHttpClient(CookieInterceptor.create())
    }
}