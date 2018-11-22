package zqx.rj.com.playandroid.home.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.home.data.bean.HomeArticleRsp

/**
 * author：  HyZhan
 * created： 2018/10/14 19:09
 * desc：    首页仓库
 */
class HomeRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getBanner(liveData: MutableLiveData<BaseResponse<List<BannerRsp>>>) {
        apiService.getBanner()
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun getArticle(page: Int, liveData: MutableLiveData<BaseResponse<HomeArticleRsp>>) {
        apiService.getHomeArticle(page)
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun getCommonWeb(liveData: MutableLiveData<BaseResponse<List<CommonWebRsp>>>) {
        apiService.getCommonWeb()
                .execute(BaseObserver(liveData, loadState, this))
    }
}