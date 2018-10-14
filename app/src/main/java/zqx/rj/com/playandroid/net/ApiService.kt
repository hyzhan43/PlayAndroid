package zqx.rj.com.playandroid.net

import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable
import zqx.rj.com.playandroid.account.data.bean.AccountData

/**
 * author：  HyZhan
 * created： 2018/10/10 16:43
 * desc：    Api  (来自 hongyang大神的玩Android Api -> http://www.wanandroid.com/blog/show/2 )
 */
interface ApiService {

    @POST("/user/login")
    fun getLogin(@Query("username") username: String,
                 @Query("password") password: String): Observable<AccountData>
}