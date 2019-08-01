package zqx.rj.com.mvvm.common

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * author：  HyZhan
 * created： 2018/10/24 15:48
 * desc：    封装 SharedPreferences
 */
class Preference<T>(private val name: String, private val default: T) : ReadWriteProperty<Any?, T> {

        companion object {
            lateinit var preference: SharedPreferences

            // 初始化 application 传入
            fun setContext(context: Context) {
                preference = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            }

        // 清除数据
        fun clear() = preference.edit().clear().apply()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(name, value)

    // 查找 对于 name 的数据
    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T): T = with(preference) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be get from Preferences")
        }
        res as T
    }

    // 存储 key 为name  值为 value
    private fun <T> putPreference(name: String, value: T) = with(preference.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}