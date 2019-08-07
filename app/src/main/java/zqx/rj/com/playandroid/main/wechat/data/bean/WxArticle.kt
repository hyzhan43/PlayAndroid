package zqx.rj.com.playandroid.main.wechat.data.bean

/**
 * author：  HyZhan
 * created： 2018/11/2 17:14
 * desc：    TODO
 */
data class WxArticle(
        val apkLink: String,
        val author: String,
        val chapterName: String,
        val link: String,
        val niceDate: String,
        val collect: Boolean,
        val superChapterName: String,
//        var publishTime: Int,
        val id: Int,
        val title: String,
        val superChapterId: Int
)