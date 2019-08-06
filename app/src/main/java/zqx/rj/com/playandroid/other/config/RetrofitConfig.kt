package zqx.rj.com.playandroid.other.config

import com.zhan.mvvm.http.BaseRetrofitConfig
import zqx.rj.com.playandroid.other.api.API

/**
 * author：  HyZhan
 * create：  2019/8/6
 * desc：    TODO
 */
class RetrofitConfig : BaseRetrofitConfig() {
    override val baseUrl: String
        get() = API.BASE_URL
}