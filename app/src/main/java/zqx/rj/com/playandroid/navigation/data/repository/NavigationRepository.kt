package zqx.rj.com.playandroid.navigation.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp
import zqx.rj.com.playandroid.net.ApiRepository

/**
 * author：  HyZhan
 * created： 2018/10/21 18:52
 * desc：    TODO
 */
class NavigationRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getCategory(liveData: MutableLiveData<BaseResponse<List<NaviCategoryRsp>>>) {
        addSubscribe(apiService.getCategory()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<NaviCategoryRsp>>>(liveData, loadState) {}))
    }

}