package zqx.rj.com.playandroid.common.article.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/22 20:47
 * desc：    TODO
 */
data class Article(
        var id: Int,
        var author: String,
        var chapterName: String?,
        var desc: String,
        var link: String,
        var originId: Int,
        var title: String,
        var collect: Boolean,
        var superChapterName: String?,
        var niceDate: String
)