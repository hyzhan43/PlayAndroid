package zqx.rj.com.playandroid.net

import zqx.rj.com.mvvm.base.BaseRepository
import zqx.rj.com.mvvm.http.RetrofitFactory

/**
 * author：  HyZhan
 * created： 2018/10/11 14:22
 * desc：    TODO
 */
abstract class ApiRepository : BaseRepository() {

    protected val apiService: ApiService by lazy {
        RetrofitFactory.instance.create(ApiService::class.java)
    }

}