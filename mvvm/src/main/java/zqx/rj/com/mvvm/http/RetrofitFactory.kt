package zqx.rj.com.mvvm.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import zqx.rj.com.mvvm.common.constant.Constant
import java.util.concurrent.TimeUnit

/**
 * author：  HyZhan
 * created： 2018/10/10 16:35
 * desc：    retrofit 封装
 */
class RetrofitFactory private constructor() {

    private val retrofit: Retrofit
    private val intereceptor: Interceptor

    companion object {
        val instance by lazy { RetrofitFactory() }
    }

    init {

        intereceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "UTF-8")
//                    .addHeader("Token")
                    .build()

            chain.proceed(request)
        }


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
                .addInterceptor(intereceptor)           // 添加请求头 token 相关信息
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
}