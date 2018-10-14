package zqx.rj.com.playandroid.account.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.account.data.bean.AccountData
import zqx.rj.com.playandroid.net.ApiRepository


/**
 * author：  HyZhan
 * created： 2018/10/11 14:56
 * desc：    账户仓库
 */
class AccountRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    private val LOGIN_SUC = 0

    fun login(username: String, password: String, liveData: MutableLiveData<AccountData>) {
        addSubscribe(
                apiService.getLogin(username, password)
                        .compose(RxSchedulers.ioToMain())
                        .subscribe(object : BaseObserver<AccountData>() {
                            override fun onNext(response: AccountData) {
                                if (response.errorCode == LOGIN_SUC) {
                                    liveData.postValue(response)
                                } else {
                                    loadState.postValue(State(StateType.ERROR, response.errorMsg))
                                }
                            }
                            override fun onError(e: Throwable?) {
                                loadState.postValue(State(StateType.NETWORK))
                            }
                        }))

    }
}