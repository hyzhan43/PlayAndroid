package zqx.rj.com.playandroid.system.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.system.data.bean.TreeArticleRsp

/**
 * author：  HyZhan
 * created： 2018/10/22 19:34
 * desc：    TODO
 */
class SystemRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getTree(liveData: MutableLiveData<BaseResponse<List<TopTreeRsp>>>) {
        apiService.getTree()
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun getArticle(page: Int, cid: Int, liveData: MutableLiveData<BaseResponse<TreeArticleRsp>>) {
        apiService.getArticleTree(page, cid)
                .execute(BaseObserver(liveData, loadState, this))
    }
}