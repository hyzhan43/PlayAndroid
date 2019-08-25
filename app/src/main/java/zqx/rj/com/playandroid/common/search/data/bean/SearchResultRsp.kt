package zqx.rj.com.playandroid.common.search.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/19 19:31
 * desc：    TODO
 */
data class SearchResultRsp(
        val curPage: Int,
        val datas: List<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)