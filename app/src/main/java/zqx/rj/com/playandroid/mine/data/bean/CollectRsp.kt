package zqx.rj.com.playandroid.mine.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/24 16:56
 * desc：    TODO
 */
data class CollectRsp(
        var curPage: Int,
        var datas: List<Article>,
        var total: Int
)