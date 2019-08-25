package zqx.rj.com.mvvm.common;

import java.lang.System;

/**
 * author：  HyZhan
 * create：  2018/12/14 19:14
 * desc：    可以控制 smoothScrollToPosition  速度的 manager
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lzqx/rj/com/mvvm/common/SpeedLayoutManager;", "Landroid/support/v7/widget/LinearLayoutManager;", "context", "Landroid/content/Context;", "speed", "", "(Landroid/content/Context;F)V", "getContext", "()Landroid/content/Context;", "getSpeed", "()F", "smoothScrollToPosition", "", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "state", "Landroid/support/v7/widget/RecyclerView$State;", "position", "", "mvvm_debug"})
public final class SpeedLayoutManager extends android.support.v7.widget.LinearLayoutManager {
    @org.jetbrains.annotations.Nullable()
    private final android.content.Context context = null;
    private final float speed = 0.0F;
    
    @java.lang.Override()
    public void smoothScrollToPosition(@org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView recyclerView, @org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView.State state, int position) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final float getSpeed() {
        return 0.0F;
    }
    
    public SpeedLayoutManager(@org.jetbrains.annotations.Nullable()
    android.content.Context context, float speed) {
        super(null);
    }
}