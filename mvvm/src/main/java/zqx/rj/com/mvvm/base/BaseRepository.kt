package zqx.rj.com.mvvm.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * author：  HyZhan
 * created： 2018/10/11 13:49
 * desc：    控制所有 订阅事件
 */
abstract class BaseRepository {

    private val mCompositeDisposable by lazy { CompositeDisposable() }

    fun addSubscribe(disposable: Disposable) = mCompositeDisposable.add(disposable)

    fun unSubscribe() = mCompositeDisposable.dispose()
}