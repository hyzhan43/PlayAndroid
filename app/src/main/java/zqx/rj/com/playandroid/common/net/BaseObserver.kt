package zqx.rj.com.playandroid.common.net

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import zqx.rj.com.mvvm.base.BaseRepository
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.account.data.context.UserContext


/**
 * author：  HyZhan
 * created： 2018/10/11 18:42
 * desc：    封装 Obaserver -> 基础状态分发 -> 若 成功 直接返回到 view
 */
class BaseObserver<T : BaseResponse<*>>(val liveData: MutableLiveData<T>,
                                        val loadState: MutableLiveData<State>,
                                        val repository: BaseRepository) : Observer<T> {

    override fun onNext(response: T) {

        when (response.errorCode) {
            Constant.SUCCESS -> {
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
            }
            Constant.NOT_LOGIN -> {
                // 如果 cookie 失效的话，直接标志退出
                UserContext.instance.logoutSuccess()
                loadState.postValue(State(StateType.ERROR, msg = "登录已过期,请重新登录"))
            }
            else -> {
                loadState.postValue(State(StateType.ERROR, msg = response.errorMsg))
            }
        }

    }

    override fun onError(e: Throwable) {
        loadState.postValue(State(StateType.NETWORK))
    }

    override fun onComplete() {}

    // 管理所有订阅
    override fun onSubscribe(d: Disposable) {
        repository.addSubscribe(d)
    }
}