package zqx.rj.com.playandroid.other.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import zqx.rj.com.playandroid.other.api.API
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.other.constant.Key
import zqx.rj.com.playandroid.other.persistence.Account

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
            val requestUrl = request.url().toString()

            // 如果 是(登录请求 或者 注册请求) 并且 请求头包含 cookie
            if (isLoginRequest(requestUrl) || isRegisterRequest(requestUrl)) {
                return@Interceptor parserCookie(chain, request)
            }

            val builder = request.newBuilder()
            if (Account.cookie.isNotEmpty()) {
                builder.addHeader(Const.COOKIE_NAME, Account.cookie)
            }

            return@Interceptor chain.proceed(builder.build())
        }
    }

    private fun parserCookie(chain: Interceptor.Chain, request: Request): Response {
        val response = chain.proceed(request)
        // 获取 全部 cookie
        val cookies = response.headers(Key.SET_COOKIE)
        // 解析 cookie, 并存储
        Account.cookie = cookies.joinToString(";")
        return response
    }

    private fun isRegisterRequest(requestUrl: String) = requestUrl.contains(API.REGISTER)

    private fun isLoginRequest(requestUrl: String) = requestUrl.contains(API.LOGIN)
}