package zqx.rj.com.mvvm.common

import android.content.Context
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.SpannableString
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.reflect.ParameterizedType
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import java.util.regex.Pattern


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

}