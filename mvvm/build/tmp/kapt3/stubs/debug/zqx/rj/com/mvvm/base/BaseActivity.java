package zqx.rj.com.mvvm.base;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/10 10:47
 * desc：    TODO
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0014H\u0014J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\u0016\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001f\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n8DX\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lzqx/rj/com/mvvm/base/BaseActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "disposable", "Lio/reactivex/disposables/Disposable;", "getDisposable", "()Lio/reactivex/disposables/Disposable;", "setDisposable", "(Lio/reactivex/disposables/Disposable;)V", "loadService", "Lcom/kingja/loadsir/core/LoadService;", "getLoadService", "()Lcom/kingja/loadsir/core/LoadService;", "loadService$delegate", "Lkotlin/Lazy;", "pressTime", "", "getLayoutId", "", "initData", "", "initView", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "reLoad", "setToolBar", "toolbar", "Landroid/support/v7/widget/Toolbar;", "title", "", "mvvm_debug"})
public abstract class BaseActivity extends android.support.v7.app.AppCompatActivity {
    private long pressTime;
    @org.jetbrains.annotations.Nullable()
    private io.reactivex.disposables.Disposable disposable;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy loadService$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.Nullable()
    protected final io.reactivex.disposables.Disposable getDisposable() {
        return null;
    }
    
    protected final void setDisposable(@org.jetbrains.annotations.Nullable()
    io.reactivex.disposables.Disposable p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.kingja.loadsir.core.LoadService<?> getLoadService() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public abstract int getLayoutId();
    
    public void initView() {
    }
    
    public void initData() {
    }
    
    public void reLoad() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    /**
     * 设置 toolbar 标题
     */
    public final void setToolBar(@org.jetbrains.annotations.NotNull()
    android.support.v7.widget.Toolbar toolbar, @org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    /**
     * 设置toolbar 通用 back 返回为 finish
     */
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.Nullable()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public BaseActivity() {
        super();
    }
}