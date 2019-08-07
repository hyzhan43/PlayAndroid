package zqx.rj.com.playandroid.other.widget.adapter

import android.text.Editable
import android.text.TextWatcher

/**
 * author：  HyZhan
 * created： 2018/10/29 14:50
 * desc：    EditText 输入监听
 */
open class TextWatcherAdapter : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}