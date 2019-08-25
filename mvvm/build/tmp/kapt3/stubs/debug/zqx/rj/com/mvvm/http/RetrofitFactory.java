package zqx.rj.com.mvvm.http;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/10 16:35
 * desc：    retrofit 封装
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lzqx/rj/com/mvvm/http/RetrofitFactory;", "", "()V", "retrofit", "Lretrofit2/Retrofit;", "create", "T", "clz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "encodeCookie", "", "cookies", "", "initCommonIntercept", "Lokhttp3/Interceptor;", "initCookieIntercept", "initLogInterceptor", "initLoginIntercept", "initOkHttpClient", "Lokhttp3/OkHttpClient;", "saveCookie", "", "domain", "cookie", "Companion", "mvvm_debug"})
public final class RetrofitFactory {
    private final retrofit2.Retrofit retrofit = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy instance$delegate = null;
    public static final zqx.rj.com.mvvm.http.RetrofitFactory.Companion Companion = null;
    
    private final okhttp3.OkHttpClient initOkHttpClient() {
        return null;
    }
    
    private final okhttp3.Interceptor initLogInterceptor() {
        return null;
    }
    
    public final <T extends java.lang.Object>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> clz) {
        return null;
    }
    
    private final okhttp3.Interceptor initCookieIntercept() {
        return null;
    }
    
    private final okhttp3.Interceptor initLoginIntercept() {
        return null;
    }
    
    private final okhttp3.Interceptor initCommonIntercept() {
        return null;
    }
    
    private final java.lang.String encodeCookie(java.util.List<java.lang.String> cookies) {
        return null;
    }
    
    private final void saveCookie(java.lang.String domain, java.lang.String cookie) {
    }
    
    private RetrofitFactory() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lzqx/rj/com/mvvm/http/RetrofitFactory$Companion;", "", "()V", "instance", "Lzqx/rj/com/mvvm/http/RetrofitFactory;", "getInstance", "()Lzqx/rj/com/mvvm/http/RetrofitFactory;", "instance$delegate", "Lkotlin/Lazy;", "mvvm_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final zqx.rj.com.mvvm.http.RetrofitFactory getInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}