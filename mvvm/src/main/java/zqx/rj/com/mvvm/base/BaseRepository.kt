package zqx.rj.com.mvvm.base

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * author：  HyZhan
 * created： 2018/10/11 13:49
 * desc：    TODO
 */
abstract class BaseRepository {

    private val mCompositeSubscription by lazy { CompositeSubscription() }

    protected fun addSubscribe(subscription: Subscription) {
        mCompositeSubscription.add(subscription)
    }

    fun unSubscribe() {
        mCompositeSubscription.hasSubscriptions().let { mCompositeSubscription.clear() }
    }
}