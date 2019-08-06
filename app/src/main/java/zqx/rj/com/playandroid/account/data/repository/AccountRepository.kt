package zqx.rj.com.playandroid.account.data.repository

import com.zhan.mvvm.mvvm.BaseRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.RegisterRsp
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService


/**
 * author：  HyZhan
 * created： 2018/10/11 14:56
 * desc：    账户仓库
 */
class AccountRepository: BaseRepository() {

    suspend fun login(username: String, password: String): BaseResponse<LoginRsp> {
        return apiService.loginAsync(username, password).await()
    }

    suspend fun register(username: String, password: String, rePassword: String): BaseResponse<RegisterRsp> {
        return apiService.registerAsync(username, password, rePassword).await()
    }
}