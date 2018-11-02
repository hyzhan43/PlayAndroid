package zqx.rj.com.playandroid.article.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.playandroid.article.data.repository.ArticleRepository

/**
 * author：  HyZhan
 * created： 2018/11/2 19:45
 * desc：    TODO
 */
abstract class ArticleViewModel<T : ArticleRepository>(application: Application)
    : BaseViewModel<T>(application) {

    var mCollectData: MutableLiveData<BaseResponse<EmptyRsp>> = MutableLiveData()

    fun collect(id: Int) {
        mRepository.collect(id, mCollectData)
    }

    fun unCollect(id: Int) {
        mRepository.unCollect(id, mCollectData)
    }
}