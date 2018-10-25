package zqx.rj.com.playandroid.mine.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp
import zqx.rj.com.playandroid.net.ApiRepository

/**
 * author：  HyZhan
 * created： 2018/10/23 14:46
 * desc：    TODO
 */
class MineRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getCollectArticle(liveData: MutableLiveData<BaseResponse<CollectRsp>>) {
        addSubscribe(apiService.getCollectAtricle()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<CollectRsp>>(liveData, loadState) {}))
    }

    fun collect(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        addSubscribe(apiService.collect(id)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<EmptyRsp>>(liveData, loadState) {}))
    }

    fun unCollect(id: Int, originId: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        addSubscribe(apiService.unMyCollect(id, originId)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<EmptyRsp>>(liveData, loadState) {}))
    }
}