package zqx.rj.com.playandroid.home.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.net.ApiRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:09
 * desc：    TODO
 */
class HomeRepository(val loadstate: MutableLiveData<State>) : ApiRepository() {

    fun getBanner(liveData: MutableLiveData<BaseResponse<List<BannerRsp>>>) {
        addSubscribe(apiService.getBanner()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<BannerRsp>>>(liveData, loadstate) {}))
    }
}