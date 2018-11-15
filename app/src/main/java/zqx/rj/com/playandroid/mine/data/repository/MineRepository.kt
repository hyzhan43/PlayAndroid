package zqx.rj.com.playandroid.mine.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp

/**
 * author：  HyZhan
 * created： 2018/10/23 14:46
 * desc：    TODO
 */
class MineRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    // 获取我的收藏
    fun getCollectArticle(liveData: MutableLiveData<BaseResponse<CollectRsp>>) {
        apiService.getCollectAtricle()
                .execute(BaseObserver(liveData, loadState, this))
    }

    // 取消 我的收藏
    fun unCollect(id: Int, originId: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.unMyCollect(id, originId)
                .execute(BaseObserver(liveData, loadState, this))
    }
}