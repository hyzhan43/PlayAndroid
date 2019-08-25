package zqx.rj.com.mvvm.http.rx;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/11 19:14
 * desc：    封装 Schedulers.io()  -> AndroidSchedulers.mainThread()
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lzqx/rj/com/mvvm/http/rx/RxSchedulers;", "", "()V", "ioToMain", "Lio/reactivex/ObservableTransformer;", "T", "mvvm_debug"})
public final class RxSchedulers {
    public static final zqx.rj.com.mvvm.http.rx.RxSchedulers INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>io.reactivex.ObservableTransformer<T, T> ioToMain() {
        return null;
    }
    
    private RxSchedulers() {
        super();
    }
}