package zqx.rj.com.mvvm.state.callback.todo;

import java.lang.System;

/**
 * author：  HyZhan
 * create：  2019/1/3 19:09
 * desc：    type 监听器
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lzqx/rj/com/mvvm/state/callback/todo/TypeChangeListener;", "", "refreshTodoList", "", "typeChange", "type", "", "mvvm_debug"})
public abstract interface TypeChangeListener {
    
    public abstract void typeChange(int type);
    
    public abstract void refreshTodoList();
}