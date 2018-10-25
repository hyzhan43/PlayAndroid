package zqx.rj.com.mvvm.http.rx

import android.arch.lifecycle.MutableLiveData
import rx.Observer
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse


/**
 * author：  HyZhan
 * created： 2018/10/11 18:42
 * desc：    封装 Obaserver -> 基础状态分发 -> 若 成功 直接返回到 view
 */
abstract class BaseObserver<T : BaseResponse<*>>(val liveData: MutableLiveData<T>,
                                                 val loadState: MutableLiveData<State>) : Observer<T> {
    private val LOGIN_SUC = 0

    override fun onNext(response: T) {
        if (response.errorCode == LOGIN_SUC) {
            liveData.postValue(response)
            // 隐藏 loading
            loadState.postValue(State(StateType.SUCCESS))
        } else {
            loadState.postValue(State(StateType.ERROR, msg = response.errorMsg))
        }
    }

    override fun onError(e: Throwable?) {
        loadState.postValue(State(StateType.NETWORK))
    }

    override fun onCompleted() {}
}