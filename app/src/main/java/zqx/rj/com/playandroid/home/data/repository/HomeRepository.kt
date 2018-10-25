package zqx.rj.com.playandroid.home.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.home.data.bean.*
import zqx.rj.com.playandroid.net.ApiRepository
import java.util.*

/**
 * author：  HyZhan
 * created： 2018/10/14 19:09
 * desc：    首页仓库
 */
class HomeRepository(val loadState: MutableLiveData<State>) : ApiRepository() {


    fun getBanner(liveData: MutableLiveData<BaseResponse<List<BannerRsp>>>) {
        addSubscribe(apiService.getBanner()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<BannerRsp>>>(liveData, loadState) {}))
    }

    fun getArticle(page: Int, liveData: MutableLiveData<BaseResponse<HomeArticleRsp>>) {
        addSubscribe(apiService.getHomeArticle(page)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<HomeArticleRsp>>(liveData, loadState) {}))
    }

    fun getHotKey(liveData: MutableLiveData<BaseResponse<List<HomeHotKeyRsp>>>) {
        addSubscribe(apiService.getHotKey()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<HomeHotKeyRsp>>>(liveData, loadState) {}))
    }

    fun search(page: Int, str: String, liveData: MutableLiveData<BaseResponse<HomeSearchRsp>>) {
        addSubscribe(apiService.search(page, str)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<HomeSearchRsp>>(liveData, loadState) {}))
    }

    fun getCommonWeb(liveData: MutableLiveData<BaseResponse<List<CommonWebRsp>>>) {
        addSubscribe(apiService.getCommonWeb()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<CommonWebRsp>>>(liveData, loadState) {}))
    }

    fun collect(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        addSubscribe(apiService.collect(id)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<EmptyRsp>>(liveData, loadState) {}))
    }

    fun unCollect(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        addSubscribe(apiService.unCollect(id)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<EmptyRsp>>(liveData, loadState) {}))
    }
}