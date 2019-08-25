package zqx.rj.com.mvvm.common;

import java.lang.System;

/**
 * author：  HyZhan
 * create：  2019/1/2 15:10
 * desc：    侧滑适配器
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J4\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001a\u0010\u0012\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\u0013"}, d2 = {"Lzqx/rj/com/mvvm/common/OnItemSwipeListenerAdapter;", "Lcom/chad/library/adapter/base/listener/OnItemSwipeListener;", "()V", "clearView", "", "viewHolder", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "pos", "", "onItemSwipeMoving", "canvas", "Landroid/graphics/Canvas;", "dX", "", "dY", "isCurrentlyActive", "", "onItemSwipeStart", "onItemSwiped", "mvvm_debug"})
public class OnItemSwipeListenerAdapter implements com.chad.library.adapter.base.listener.OnItemSwipeListener {
    
    @java.lang.Override()
    public void clearView(@org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int pos) {
    }
    
    @java.lang.Override()
    public void onItemSwiped(@org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int pos) {
    }
    
    @java.lang.Override()
    public void onItemSwipeStart(@org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int pos) {
    }
    
    @java.lang.Override()
    public void onItemSwipeMoving(@org.jetbrains.annotations.Nullable()
    android.graphics.Canvas canvas, @org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
    }
    
    public OnItemSwipeListenerAdapter() {
        super();
    }
}