package zqx.rj.com.playandroid.common.search.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp

/**
 * author：  HyZhan
 * created： 2018/11/6 15:28
 * desc：    搜索仓库
 */
class SearchRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getHotKey(liveData: MutableLiveData<BaseResponse<List<HotKeyRsp>>>) {
        addSubscribe(apiService.getHotKey()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<HotKeyRsp>>>(liveData, loadState) {}))
    }

    fun search(page: Int, str: String, liveData: MutableLiveData<BaseResponse<SearchResultRsp>>) {
        addSubscribe(apiService.search(page, str)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<SearchResultRsp>>(liveData, loadState) {}))
    }
}