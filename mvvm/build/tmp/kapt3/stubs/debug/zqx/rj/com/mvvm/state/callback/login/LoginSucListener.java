package zqx.rj.com.mvvm.state.callback.login;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/23 17:27
 * desc：    登录成功的回调
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&\u00a8\u0006\t"}, d2 = {"Lzqx/rj/com/mvvm/state/callback/login/LoginSucListener;", "", "success", "", "username", "", "collectIds", "", "", "mvvm_debug"})
public abstract interface LoginSucListener {
    
    public abstract void success(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> collectIds);
}