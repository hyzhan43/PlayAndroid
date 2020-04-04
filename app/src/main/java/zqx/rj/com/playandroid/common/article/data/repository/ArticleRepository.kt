package zqx.rj.com.playandroid.common.article.data.repository

import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.bean.EmptyRsp

/**
 * author：  HyZhan
 * created： 2018/11/2 19:24
 * desc：    文章 收藏仓库
 */
abstract class ArticleRepository {

    suspend fun collect(id: Int): BaseResponse<EmptyRsp> {
        return apiService.collectAsync(id)
    }

    suspend fun unCollect(id: Int): BaseResponse<EmptyRsp> {
        return apiService.unCollectAsync(id)
    }
}