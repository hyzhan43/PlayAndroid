package zqx.rj.com.playandroid.navigation.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.common.net.ApiRepository
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp

/**
 * author：  HyZhan
 * created： 2018/10/21 18:52
 * desc：    TODO
 */
class NavigationRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getCategory(liveData: MutableLiveData<BaseResponse<List<NaviCategoryRsp>>>) {
        apiService.getCategory()
                .execute(BaseObserver(liveData, loadState, this))
    }

}