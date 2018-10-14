package zqx.rj.com.mvvm.http.rx

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * author：  HyZhan
 * created： 2018/10/11 19:14
 * desc：    封装 Schedulers.io()  -> AndroidSchedulers.mainThread()
 */
object RxSchedulers {
    fun <T> ioToMain(): Observable.Transformer<T, T> {
        return Observable.Transformer {
            it.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}