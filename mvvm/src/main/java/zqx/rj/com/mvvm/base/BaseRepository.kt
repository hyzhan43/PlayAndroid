package zqx.rj.com.mvvm.base

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * author：  HyZhan
 * created： 2018/10/11 13:49
 * desc：    控制所有 订阅事件
 */
abstract class BaseRepository {

    private val mCompositeSubscription by lazy { CompositeSubscription() }

    fun addSubscribe(subscription: Subscription) = mCompositeSubscription.add(subscription)

    fun unSubscribe() = mCompositeSubscription.hasSubscriptions().let { mCompositeSubscription.clear() }
}