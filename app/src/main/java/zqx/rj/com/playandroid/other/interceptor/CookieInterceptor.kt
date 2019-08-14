package zqx.rj.com.playandroid.other.interceptor

import com.zhan.mvvm.common.Preference
import okhttp3.Interceptor
import zqx.rj.com.playandroid.other.api.API
import zqx.rj.com.playandroid.other.constant.Key
import zqx.rj.com.playandroid.other.persistence.Account
import java.util.stream.Collectors

/**
 * author：  HyZhan
 * create：  2019/8/14
 * desc：    TODO
 */
object CookieInterceptor {

    // cookie 拦截器 获取 cookie (自动登录时候需要用到)
    fun create(): Interceptor {
        return Interceptor { chain ->
            // 获取 请求
            val request = chain.request()
            val response = chain.proceed(request)
            val requestUrl = request.url().toString()

            // 如果 是(登录请求 或者 注册请求) 并且 请求头包含 cookie
            if ((requestUrl.contains(API.LOGIN) || requestUrl.contains(API.REGISTER))) {
                // 获取 全部 cookie
                val cookies = response.headers(Key.SET_COOKIE)
                // 解析 cookie, 并存储
                Account.cookie = cookies.joinToString(";")
            }
            response
        }
    }
}