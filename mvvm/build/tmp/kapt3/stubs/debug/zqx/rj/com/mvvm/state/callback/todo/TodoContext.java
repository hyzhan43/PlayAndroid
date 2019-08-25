package zqx.rj.com.mvvm.state.callback.todo;

import java.lang.System;

/**
 * author：  HyZhan
 * create：  2019/1/3 19:33
 * desc：    TODO
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lzqx/rj/com/mvvm/state/callback/todo/TodoContext;", "", "()V", "listeners", "Ljava/util/ArrayList;", "Lzqx/rj/com/mvvm/state/callback/todo/TypeChangeListener;", "addListener", "", "listener", "notifyTodoRefresh", "notifyTodoTypeChange", "type", "", "removeListener", "mvvm_debug"})
public final class TodoContext {
    private static final java.util.ArrayList<zqx.rj.com.mvvm.state.callback.todo.TypeChangeListener> listeners = null;
    public static final zqx.rj.com.mvvm.state.callback.todo.TodoContext INSTANCE = null;
    
    public final void addListener(@org.jetbrains.annotations.NotNull()
    zqx.rj.com.mvvm.state.callback.todo.TypeChangeListener listener) {
    }
    
    public final void removeListener(@org.jetbrains.annotations.NotNull()
    zqx.rj.com.mvvm.state.callback.todo.TypeChangeListener listener) {
    }
    
    public final void notifyTodoTypeChange(int type) {
    }
    
    public final void notifyTodoRefresh() {
    }
    
    private TodoContext() {
        super();
    }
}