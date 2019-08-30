package zqx.rj.com.playandroid.common.article.data.repository

import com.zhan.mvvm.mvvm.BaseRepository
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.other.bean.BaseResponse

/**
 * author：  HyZhan
 * created： 2018/11/2 19:24
 * desc：    文章 收藏仓库
 */
abstract class ArticleRepository : BaseRepository() {

    suspend fun collect(id: Int): BaseResponse<EmptyRsp> {
        return launchIO { apiService.collectAsync(id).await() }
    }

    suspend fun unCollect(id: Int): BaseResponse<EmptyRsp> {
        return launchIO { apiService.unCollectAsync(id).await() }
    }
}