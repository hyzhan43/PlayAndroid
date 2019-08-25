package zqx.rj.com.mvvm.common;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/13 16:30
 * desc：    activity 管理类
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u0007H\u0002J\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lzqx/rj/com/mvvm/common/AppManager;", "", "()V", "activityStack", "Ljava/util/Stack;", "Landroid/app/Activity;", "addActivity", "", "activity", "exitApp", "context", "Landroid/content/Context;", "finishAllActivity", "removeActivity", "Companion", "mvvm_debug"})
public final class AppManager {
    private final java.util.Stack<android.app.Activity> activityStack = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy instance$delegate = null;
    public static final zqx.rj.com.mvvm.common.AppManager.Companion Companion = null;
    
    public final void addActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void removeActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    private final void finishAllActivity() {
    }
    
    public final void exitApp(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public AppManager() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lzqx/rj/com/mvvm/common/AppManager$Companion;", "", "()V", "instance", "Lzqx/rj/com/mvvm/common/AppManager;", "getInstance", "()Lzqx/rj/com/mvvm/common/AppManager;", "instance$delegate", "Lkotlin/Lazy;", "mvvm_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final zqx.rj.com.mvvm.common.AppManager getInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}