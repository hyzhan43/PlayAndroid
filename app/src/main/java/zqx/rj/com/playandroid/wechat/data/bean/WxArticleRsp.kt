package zqx.rj.com.playandroid.wechat.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/11/2 17:14
 * desc：    TODO
 */
data class WxArticleRsp(
        var curPage: Int,
        var datas: List<Article>,
        var offset: Int,
        var over: Boolean,
        var pageCount: Int,
        var size: Int,
        var total: Int
)