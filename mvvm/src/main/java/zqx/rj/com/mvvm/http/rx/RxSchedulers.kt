package zqx.rj.com.mvvm.http.rx

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * author：  HyZhan
 * created： 2018/10/11 19:14
 * desc：    封装 Schedulers.io()  -> AndroidSchedulers.mainThread()
 */
object RxSchedulers {

    fun <T> ioToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

}