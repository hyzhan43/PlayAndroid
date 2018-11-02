package zqx.rj.com.playandroid.wechat.data.bean

/**
 * author：  HyZhan
 * created： 2018/11/2 17:14
 * desc：    TODO
 */
data class WxArticle(
        var apkLink: String,
        var author: String,
        var chapterName: String,
        var link: String,
        var niceDate: String,
        var collect: Boolean,
        var superChapterName: String,
//        var publishTime: Int,
        var id: Int,
        var title: String,
        var superChapterId: Int
)