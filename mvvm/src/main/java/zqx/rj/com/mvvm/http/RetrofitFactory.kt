package zqx.rj.com.mvvm.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import java.util.concurrent.TimeUnit

/**
 * author：  HyZhan
 * created： 2018/10/10 16:35
 * desc：    retrofit 封装
 */
class RetrofitFactory private constructor() {

    private val retrofit: Retrofit

    private val mCookieItp: Interceptor by lazy { initCookieIntercept() }
    private val mLoginItp: Interceptor by lazy { initLoginIntercept() }
    private val mCommonItp: Interceptor by lazy { initCommonIntercept() }

    companion object {
        val instance by lazy { RetrofitFactory() }
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(Constant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initOkHttpClient())
                .build()
    }

    // 初始化 okHttp
    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(mCookieItp)
                .addInterceptor(mLoginItp)
                .addInterceptor(mCommonItp)
                .addInterceptor(initLogInterceptor())
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    // 初始化日志
    private fun initLogInterceptor(): Interceptor? {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun <T> create(clz: Class<T>): T {
        checkNotNull(clz)
        checkNotNull(retrofit)
        return retrofit.create(clz)
    }

    // cookie 拦截器 获取 cookie (自动登录时候需要用到)
    private fun initCookieIntercept(): Interceptor {
        return Interceptor {
            // 获取 请求
            val request = it.request()
            val response = it.proceed(request)
            val requestUrl = request.url().toString()
            // 获取 域名  ( wanandroid.com )
            val domain = request.url().host()

            // 如果 是(登录请求 或者 注册请求) 并且 请求头包含 cookie
            if ((requestUrl.contains(Constant.SAVE_USER_LOGIN_KEY)
                            || requestUrl.contains(Constant.SAVE_USER_REGISTER_KEY))
                    && response.header(Constant.SET_COOKIE_KEY).isNotEmpty()) {

                // 获取 全部 cookie
                val cookies = response.headers(Constant.SET_COOKIE_KEY)
                // 解析 cookie
                val cookie = encodeCookie(cookies)
                saveCookie(domain, cookie)
            }
            response
        }
    }


    // 设置 请求 cookie (自动登录)
    private fun initLoginIntercept(): Interceptor {
        return Interceptor {
            val request = it.request()
            val builder = request.newBuilder()
            val domain = request.url().host()

            if (domain.isNotEmpty()) {
                val cookie: String by Preference(domain, "")
                if (cookie.isNotEmpty()) {
                    builder.addHeader(Constant.COOKIE_NAME, cookie)
                }
            }

            it.proceed(builder.build())
        }
    }

    private fun initCommonIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()

            chain.proceed(request)
        }
    }

    // 解析 cookie
    private fun encodeCookie(cookies: List<String>): String {

        val sb = StringBuilder()
        val set = HashSet<String>()

        // dropLastWhile -> 返回从最后一项起，去掉满足条件的元素，直到不满足条件的一项为止
        cookies.map {
            it.split(";").dropLastWhile { it.isEmpty() }.toTypedArray()
        }.forEach {
            it.forEach { set.add(it) }
        }

        // 拼接 cookie ——> 如 JSESSIONID=1C42A0A65F4FFEB70023E5ED7BCA69; Path=/;
        set.map { sb.append(it).append(";") }

        return sb.deleteCharAt(sb.length - 1).toString()
    }

    // 持久化存储
    private fun saveCookie(domain: String?, cookie: String) {

        // 根据 domain  保存 cookie
        domain?.let {
            var spDomain: String by Preference(it, cookie)
            spDomain = cookie
        }
    }
}