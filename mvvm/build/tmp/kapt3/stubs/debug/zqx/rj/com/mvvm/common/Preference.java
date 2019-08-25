package zqx.rj.com.mvvm.common;

import java.lang.System;

/**
 * author：  HyZhan
 * created： 2018/10/24 15:48
 * desc：    封装 SharedPreferences
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u0015*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0007J#\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0001H\u0002\u00a2\u0006\u0002\u0010\nJ$\u0010\u000b\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0096\u0002\u00a2\u0006\u0002\u0010\u000fJ#\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0001H\u0002\u00a2\u0006\u0002\u0010\u0007J,\u0010\u0013\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0012\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010\u0014R\u0010\u0010\u0006\u001a\u00028\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lzqx/rj/com/mvvm/common/Preference;", "T", "Lkotlin/properties/ReadWriteProperty;", "", "name", "", "default", "(Ljava/lang/String;Ljava/lang/Object;)V", "Ljava/lang/Object;", "findPreference", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "putPreference", "", "value", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "Companion", "mvvm_debug"})
public final class Preference<T extends java.lang.Object> implements kotlin.properties.ReadWriteProperty<java.lang.Object, T> {
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    public static android.content.SharedPreferences preference;
    public static final zqx.rj.com.mvvm.common.Preference.Companion Companion = null;
    
    @java.lang.Override()
    public T getValue(@org.jetbrains.annotations.Nullable()
    java.lang.Object thisRef, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KProperty<?> property) {
        return null;
    }
    
    @java.lang.Override()
    public void setValue(@org.jetbrains.annotations.Nullable()
    java.lang.Object thisRef, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KProperty<?> property, T value) {
    }
    
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    private final <T extends java.lang.Object>T findPreference(java.lang.String name, T p1_772401952) {
        return null;
    }
    
    private final <T extends java.lang.Object>void putPreference(java.lang.String name, T value) {
    }
    
    public Preference(@org.jetbrains.annotations.NotNull()
    java.lang.String name, T p1_772401952) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lzqx/rj/com/mvvm/common/Preference$Companion;", "", "()V", "preference", "Landroid/content/SharedPreferences;", "getPreference", "()Landroid/content/SharedPreferences;", "setPreference", "(Landroid/content/SharedPreferences;)V", "clear", "", "setContext", "context", "Landroid/content/Context;", "mvvm_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.SharedPreferences getPreference() {
            return null;
        }
        
        public final void setPreference(@org.jetbrains.annotations.NotNull()
        android.content.SharedPreferences p0) {
        }
        
        public final void setContext(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        public final void clear() {
        }
        
        private Companion() {
            super();
        }
    }
}