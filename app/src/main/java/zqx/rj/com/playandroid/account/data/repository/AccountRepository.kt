package zqx.rj.com.playandroid.account.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.RegisterRsp
import zqx.rj.com.playandroid.common.net.ApiRepository


/**
 * author：  HyZhan
 * created： 2018/10/11 14:56
 * desc：    账户仓库
 */
class AccountRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun login(username: String, password: String, liveData: MutableLiveData<BaseResponse<LoginRsp>>) {
        addSubscribe(
                apiService.getLogin(username, password)
                        .compose(RxSchedulers.ioToMain())
                        // 交给  observer 进行结果分发
                        .subscribe(object : BaseObserver<BaseResponse<LoginRsp>>(liveData, loadState) {}))
    }

    fun register(username: String, password: String, repassword: String,
                 liveData: MutableLiveData<BaseResponse<RegisterRsp>>) {

        addSubscribe(apiService.getRegister(username, password, repassword)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<RegisterRsp>>(liveData, loadState) {}))
    }
}