package zqx.rj.com.playandroid.wechat.data.bean

import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/11/2 17:14
 * desc：    TODO
 */
data class WxArticleRsp(
        val curPage: Int,
        val datas: List<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)