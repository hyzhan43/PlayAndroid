package zqx.rj.com.mvvm.state;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/18 9:57
 * desc：    用户状态
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\r"}, d2 = {"Lzqx/rj/com/mvvm/state/UserState;", "", "collect", "", "context", "Landroid/content/Context;", "position", "", "listener", "Lzqx/rj/com/mvvm/state/callback/collect/CollectListener;", "goCollectActivity", "goTodoActivity", "login", "mvvm_debug"})
public abstract interface UserState {
    
    public abstract void collect(@org.jetbrains.annotations.Nullable()
    android.content.Context context, int position, @org.jetbrains.annotations.NotNull()
    zqx.rj.com.mvvm.state.callback.collect.CollectListener listener);
    
    public abstract void login(@org.jetbrains.annotations.Nullable()
    android.content.Context context);
    
    public abstract void goTodoActivity(@org.jetbrains.annotations.Nullable()
    android.content.Context context);
    
    public abstract void goCollectActivity(@org.jetbrains.annotations.Nullable()
    android.content.Context context);
}