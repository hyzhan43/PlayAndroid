package zqx.rj.com.mvvm.base;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/14 18:59
 * desc：    TODO
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b&\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0016J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u001c\u0010\u0005\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006 "}, d2 = {"Lzqx/rj/com/mvvm/base/LifecycleFragment;", "T", "Lzqx/rj/com/mvvm/base/BaseViewModel;", "Lzqx/rj/com/mvvm/base/BaseFragment;", "()V", "mViewModel", "getMViewModel", "()Lzqx/rj/com/mvvm/base/BaseViewModel;", "setMViewModel", "(Lzqx/rj/com/mvvm/base/BaseViewModel;)V", "Lzqx/rj/com/mvvm/base/BaseViewModel;", "observer", "Landroid/arch/lifecycle/Observer;", "Lzqx/rj/com/mvvm/common/State;", "getObserver", "()Landroid/arch/lifecycle/Observer;", "observer$delegate", "Lkotlin/Lazy;", "dataObserver", "", "initView", "reLoad", "showEmpty", "showError", "msg", "", "showLoading", "showNetWork", "showSuccess", "showTips", "tips", "", "mvvm_debug"})
public abstract class LifecycleFragment<T extends zqx.rj.com.mvvm.base.BaseViewModel<?>> extends zqx.rj.com.mvvm.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    protected T mViewModel;
    private final kotlin.Lazy observer$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final T getMViewModel() {
        return null;
    }
    
    protected final void setMViewModel(@org.jetbrains.annotations.NotNull()
    T p0) {
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    public void showError(@org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    public void showSuccess() {
    }
    
    private final void showNetWork() {
    }
    
    public void showLoading() {
    }
    
    private final void showTips(int tips) {
    }
    
    public void showEmpty() {
    }
    
    @java.lang.Override()
    public void reLoad() {
    }
    
    public abstract void dataObserver();
    
    private final android.arch.lifecycle.Observer<zqx.rj.com.mvvm.common.State> getObserver() {
        return null;
    }
    
    public LifecycleFragment() {
        super();
    }
}