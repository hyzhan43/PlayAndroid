package zqx.rj.com.mvvm.http.rx

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import zqx.rj.com.mvvm.base.BaseRepository
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse


/**
 * author：  HyZhan
 * created： 2018/10/11 18:42
 * desc：    封装 Obaserver -> 基础状态分发 -> 若 成功 直接返回到 view
 */
class BaseObserver<T : BaseResponse<*>>(val liveData: MutableLiveData<T>,
                                        val loadState: MutableLiveData<State>,
                                        val repository: BaseRepository) : Observer<T> {

    private val SUCCESS = 0

    override fun onNext(response: T) {
        if (response.errorCode == SUCCESS) {

            if (response.data is List<*>) {
                // 处理 如果 T 返回的是 List<*> 且是 Empty, 显示空白页面
                if ((response.data as List<*>).isEmpty()) {
                    loadState.postValue(State(StateType.EMPTY))
                    return
                }
            }

            // 加载成功
            loadState.postValue(State(StateType.SUCCESS))

            // 正常返回
            liveData.postValue(response)
        } else {
            loadState.postValue(State(StateType.ERROR, msg = response.errorMsg))
        }
    }

    override fun onError(e: Throwable) {
        loadState.postValue(State(StateType.NETWORK))
    }

    override fun onComplete() {}

    // 管理所有订阅
    override fun onSubscribe(d: Disposable) { repository.addSubscribe(d) }
}