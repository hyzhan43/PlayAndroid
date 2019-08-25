package zqx.rj.com.mvvm.common;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/12 20:50
 * desc：    状态类
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001e"}, d2 = {"Lzqx/rj/com/mvvm/common/State;", "", "code", "Lzqx/rj/com/mvvm/common/constant/StateType;", "msg", "", "tip", "", "(Lzqx/rj/com/mvvm/common/constant/StateType;Ljava/lang/String;I)V", "getCode", "()Lzqx/rj/com/mvvm/common/constant/StateType;", "setCode", "(Lzqx/rj/com/mvvm/common/constant/StateType;)V", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "getTip", "()I", "setTip", "(I)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "mvvm_debug"})
public final class State {
    @org.jetbrains.annotations.NotNull()
    private zqx.rj.com.mvvm.common.constant.StateType code;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String msg;
    private int tip;
    
    @org.jetbrains.annotations.NotNull()
    public final zqx.rj.com.mvvm.common.constant.StateType getCode() {
        return null;
    }
    
    public final void setCode(@org.jetbrains.annotations.NotNull()
    zqx.rj.com.mvvm.common.constant.StateType p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMsg() {
        return null;
    }
    
    public final void setMsg(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getTip() {
        return 0;
    }
    
    public final void setTip(int p0) {
    }
    
    public State(@org.jetbrains.annotations.NotNull()
    zqx.rj.com.mvvm.common.constant.StateType code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg, @android.support.annotation.StringRes()
    int tip) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final zqx.rj.com.mvvm.common.constant.StateType component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    /**
     * author：  HyZhan
     * created： 2018/10/12 20:50
     * desc：    状态类
     */
    @org.jetbrains.annotations.NotNull()
    public final zqx.rj.com.mvvm.common.State copy(@org.jetbrains.annotations.NotNull()
    zqx.rj.com.mvvm.common.constant.StateType code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg, @android.support.annotation.StringRes()
    int tip) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}