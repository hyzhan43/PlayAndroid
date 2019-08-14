package zqx.rj.com.playandroid.other.interceptor

import okhttp3.Interceptor
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.other.persistence.Account

/**
 * author：  HyZhan
 * create：  2019/8/13
 * desc：    设置 请求 cookie (自动登录)
 */
object LoginInterceptor {

    fun create(): Interceptor {
        return Interceptor {
            val request = it.request()
            val builder = request.newBuilder()

            if (Account.cookie.isNotEmpty()) {
                builder.addHeader(Const.COOKIE_NAME, Account.cookie)
            }

            it.proceed(builder.build())
        }
    }
}