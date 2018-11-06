package zqx.rj.com.playandroid.common.search.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/19 19:31
 * desc：    TODO
 */
data class SearchResultRsp(
        var curPage: Int,
        var datas: List<Article>,
        var offset: Int,
        var over: Boolean,
        var pageCount: Int,
        var size: Int,
        var total: Int
)