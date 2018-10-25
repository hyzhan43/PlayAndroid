package zqx.rj.com.playandroid.mine.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/24 19:47
 * desc：    TODO
 */
data class Article(
        var author: String,
        var chapterName: String,
        var link: String,
        var collect: Boolean = true,    // 默认全部 收藏
        var id: Int,
        var originId: Int,
        var niceDate: String,
        var title: String
)