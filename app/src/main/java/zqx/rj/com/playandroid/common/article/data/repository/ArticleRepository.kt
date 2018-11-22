package zqx.rj.com.playandroid.common.article.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.common.net.ApiRepository

/**
 * author：  HyZhan
 * created： 2018/11/2 19:24
 * desc：    文章 收藏仓库
 */
abstract class ArticleRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun collect(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.collect(id).execute(BaseObserver(liveData, loadState, this))
    }

    fun unCollect(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.unCollect(id).execute(BaseObserver(liveData, loadState, this))
    }
}