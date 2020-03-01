package zqx.rj.com.playandroid.mine.collect.data.repository

import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.mine.collect.data.bean.CollectRsp

/**
 * author：  HyZhan
 * created： 2018/10/23 14:46
 * desc：    TODO
 */
class CollectRepository : ArticleRepository() {

    // 获取我的收藏
    suspend fun getCollectArticle(page: Int): BaseResponse<CollectRsp> {
        return  apiService.getCollectArticleAsync(page)
    }

    // 取消 我的收藏
    suspend fun unCollect(id: Int, originId: Int): BaseResponse<EmptyRsp> {
        return apiService.unMyCollectAsync(id, originId)
    }
}