package zqx.rj.com.playandroid.mine.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.net.ApiRepository

/**
 * author：  HyZhan
 * created： 2018/10/23 14:46
 * desc：    TODO
 */
class MineRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun logout(liveData: MutableLiveData<BaseResponse<*>>) {
        addSubscribe(apiService.getLogout()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<*>>(liveData, loadState) {}))
    }
}