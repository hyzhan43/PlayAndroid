package zqx.rj.com.playandroid.other.widget

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * author：  HyZhan
 * created： 2018/10/14 18:31
 * desc：    banner 图片加载器
 */
class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }
}