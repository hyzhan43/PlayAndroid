package zqx.rj.com.playandroid.common.article.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/22 20:47
 * desc：    TODO
 */
data class Article(
        val id: Int,
        val author: String,
        val chapterName: String?,
        val desc: String,
        val link: String,
        val originId: Int,
        val title: String,
        var collect: Boolean,
        val superChapterName: String?,
        val niceDate: String
)