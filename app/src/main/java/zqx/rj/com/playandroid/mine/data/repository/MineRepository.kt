package zqx.rj.com.playandroid.mine.data.repository

import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp

/**
 * author：  HyZhan
 * created： 2018/10/23 14:46
 * desc：    TODO
 */
class MineRepository : ArticleRepository() {

    // 获取我的收藏
    suspend fun getCollectArticle(page: Int): BaseResponse<CollectRsp> {
        return launchIO { apiService.getCollectArticleAsync(page).await() }
    }

    // 取消 我的收藏
    suspend fun unCollect(id: Int, originId: Int): BaseResponse<EmptyRsp> {
        return launchIO { apiService.unMyCollectAsync(id, originId).await() }
    }
}