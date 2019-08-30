package zqx.rj.com.playandroid.other.ext

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import zqx.rj.com.playandroid.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * author：  HyZhan
 * created： 2018/10/11 16:33
 * desc：    扩展方法
 */
// 将 html 代码转化 为 string
// Android N（API level 24.）废弃了Html.fromHtml(String)
fun String.toHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun ImageView.loadUrl(context: Context, url: String) {

    val options = RequestOptions()
            .placeholder(R.drawable.ic_logo)
            .error(R.drawable.ic_logo)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(150, 200)

    Glide.with(context)
            .load(url)
            .apply(options)
            .into(this)
}


fun Date.format(pattern: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}


