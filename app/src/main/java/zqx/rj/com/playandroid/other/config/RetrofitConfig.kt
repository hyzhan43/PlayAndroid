package zqx.rj.com.playandroid.other.config

import com.zhan.mvvm.http.BaseOkHttpClient
import com.zhan.mvvm.http.BaseRetrofitConfig
import okhttp3.OkHttpClient
import zqx.rj.com.playandroid.other.interceptor.CookieInterceptor

/**
 * author：  HyZhan
 * create：  2019/8/6
 * desc：    TODO
 */
class RetrofitConfig : BaseRetrofitConfig() {

    override fun initOkHttpClient(): OkHttpClient {
        return BaseOkHttpClient.create(CookieInterceptor.create())
    }
}