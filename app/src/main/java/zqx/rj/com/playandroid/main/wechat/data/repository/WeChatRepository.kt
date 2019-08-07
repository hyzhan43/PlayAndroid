package zqx.rj.com.playandroid.main.wechat.data.repository

import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.main.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.main.wechat.data.bean.WxArticleRsp

/**
 * author：  HyZhan
 * created： 2018/11/2 16:29
 * desc：    TODO
 */
class WeChatRepository : ArticleRepository() {

    suspend fun getWeChatName():BaseResponse<List<WeChatNameRsp>> {
        return launchIO { apiService.getWeChatNameAsync().await() }
    }

    suspend fun getWxArticle(id: Int, page: Int):BaseResponse<WxArticleRsp> {
        return launchIO { apiService.getWxArticleAsync(id, page).await() }
    }
}