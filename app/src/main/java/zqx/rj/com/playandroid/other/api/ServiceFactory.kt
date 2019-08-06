package zqx.rj.com.playandroid.other.api

import com.zhan.mvvm.http.RetrofitFactory

/**
 * author：  HyZhan
 * create：  2019/8/1
 * desc：    TODO
 */
object ServiceFactory {

    val apiService by lazy { RetrofitFactory.instance.create(ApiService::class.java) }
}