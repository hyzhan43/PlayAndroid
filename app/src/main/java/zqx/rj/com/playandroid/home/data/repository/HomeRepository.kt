package zqx.rj.com.playandroid.home.data.repository

import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.home.data.bean.HomeArticleRsp

/**
 * author：  HyZhan
 * created： 2018/10/14 19:09
 * desc：    首页仓库
 */
class HomeRepository : ArticleRepository() {

    suspend fun getBanner(): BaseResponse<List<BannerRsp>> {
        return launchIO { apiService.getBannerAsync().await() }
    }

    suspend fun getArticle(page: Int): BaseResponse<HomeArticleRsp> {
        return launchIO { apiService.getHomeArticleAsync(page).await() }
    }

    suspend fun getCommonWeb(): BaseResponse<List<CommonWebRsp>> {
        return launchIO { apiService.getCommonWebAsync().await() }
    }
}