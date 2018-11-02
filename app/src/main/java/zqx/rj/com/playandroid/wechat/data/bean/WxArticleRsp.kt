package zqx.rj.com.playandroid.wechat.data.bean

/**
 * author：  HyZhan
 * created： 2018/11/2 17:14
 * desc：    TODO
 */
data class WxArticleRsp(
        var curPage: Int,
        var datas: List<WxArticle>,
        var offset: Int,
        var pageCount: Int,
        var size: Int,
        var total: Int
)