package zqx.rj.com.playandroid.navigation.data.repository

import com.zhan.mvvm.mvvm.BaseRepository
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp

/**
 * author：  HyZhan
 * created： 2018/10/21 18:52
 * desc：    TODO
 */
class NavigationRepository : BaseRepository() {

    suspend fun getCategory(): BaseResponse<List<NaviCategoryRsp>> {
        return launchIO { apiService.getCategoryAsync().await() }
    }
}