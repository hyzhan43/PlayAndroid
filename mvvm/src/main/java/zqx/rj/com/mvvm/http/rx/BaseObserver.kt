package zqx.rj.com.mvvm.http.rx

import rx.Observer


/**
 * author：  HyZhan
 * created： 2018/10/11 18:42
 * desc：    TODO
 */
open class BaseObserver<T> : Observer<T> {
    override fun onError(e: Throwable?) {}

    override fun onNext(response: T) {}

    override fun onCompleted() {}

}