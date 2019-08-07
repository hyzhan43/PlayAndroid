package zqx.rj.com.playandroid.main.system.data.repository

import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.main.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.main.system.data.bean.TreeArticleRsp

/**
 * author：  HyZhan
 * created： 2018/10/22 19:34
 * desc：    TODO
 */
class SystemRepository : ArticleRepository() {

    suspend fun getTree(): BaseResponse<List<TopTreeRsp>> {
        return launchIO { apiService.getTreeAsync().await() }
    }

    suspend fun getArticle(page: Int, cid: Int): BaseResponse<TreeArticleRsp> {
        return launchIO { apiService.getArticleTreeAsync(page, cid).await() }
    }
}