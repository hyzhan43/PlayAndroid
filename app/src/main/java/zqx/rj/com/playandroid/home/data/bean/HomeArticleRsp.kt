package zqx.rj.com.playandroid.home.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/16 10:51
 * desc：    首页 文章rsp
 */
data class HomeArticleRsp(
        var curPage: Int,
        var datas: List<Article>,
        var offset: Int,
        var over: Boolean,
        var pageCount: Int,
        var size: Int,
        var total: Int
)