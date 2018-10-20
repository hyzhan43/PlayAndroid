package zqx.rj.com.mvvm.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.reflect.ParameterizedType

/**
 * author：  HyZhan
 * created： 2018/10/11 16:04
 * desc：    工具类
 */
object Util {
    @Suppress("UNCHECKED_CAST")
    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取父类泛型 (T) 对应 Class类
        return (t.javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0]
                as Class<T>
    }

    // 显示/隐藏软键盘
    fun showKeyboard(context: Context, view: View, isShow: Boolean) {
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (isShow) {
            view.requestFocus()
            imm.showSoftInput(view, 0)
        } else {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}