package zqx.rj.com.playandroid.mine.collect.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/24 16:56
 * desc：    TODO
 */
data class CollectRsp(
        val curPage: Int,
        val datas: List<Article>,
        val total: Int
)