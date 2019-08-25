package zqx.rj.com.mvvm.base;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/11 13:47
 * desc：    TODO
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H\u0014R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00028\u00008FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0014"}, d2 = {"Lzqx/rj/com/mvvm/base/BaseViewModel;", "T", "Lzqx/rj/com/mvvm/base/BaseRepository;", "Landroid/arch/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "loadState", "Landroid/arch/lifecycle/MutableLiveData;", "Lzqx/rj/com/mvvm/common/State;", "getLoadState", "()Landroid/arch/lifecycle/MutableLiveData;", "loadState$delegate", "Lkotlin/Lazy;", "mRepository", "getMRepository", "()Lzqx/rj/com/mvvm/base/BaseRepository;", "mRepository$delegate", "onCleared", "", "mvvm_debug"})
public abstract class BaseViewModel<T extends zqx.rj.com.mvvm.base.BaseRepository> extends android.arch.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy loadState$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy mRepository$delegate = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<zqx.rj.com.mvvm.common.State> getLoadState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final T getMRepository() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public BaseViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
}