package zqx.rj.com.playandroid.system.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/22 20:44
 * desc：    TODO
 */
class TreeArticleRsp(
        val curPage: Int,
        val datas: List<Article>,
        val pageCount: Int,
        val total: Int
)
