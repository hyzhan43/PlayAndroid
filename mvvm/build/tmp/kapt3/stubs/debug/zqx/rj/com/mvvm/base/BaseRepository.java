package zqx.rj.com.mvvm.base;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/11 13:49
 * desc：    控制所有 订阅事件
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lzqx/rj/com/mvvm/base/BaseRepository;", "", "()V", "mCompositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getMCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "mCompositeDisposable$delegate", "Lkotlin/Lazy;", "addSubscribe", "", "disposable", "Lio/reactivex/disposables/Disposable;", "unSubscribe", "", "mvvm_debug"})
public abstract class BaseRepository {
    private final kotlin.Lazy mCompositeDisposable$delegate = null;
    
    private final io.reactivex.disposables.CompositeDisposable getMCompositeDisposable() {
        return null;
    }
    
    public final boolean addSubscribe(@org.jetbrains.annotations.NotNull()
    io.reactivex.disposables.Disposable disposable) {
        return false;
    }
    
    public final void unSubscribe() {
    }
    
    public BaseRepository() {
        super();
    }
}