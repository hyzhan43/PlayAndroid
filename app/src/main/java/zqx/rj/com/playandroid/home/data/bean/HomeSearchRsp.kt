package zqx.rj.com.playandroid.home.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/19 19:31
 * desc：    TODO
 */
data class HomeSearchRsp(
        var curPage: Int,
        var datas: List<SearchResult>,
        var offset: Int,
        var over: Boolean,
        var pageCount: Int,
        var size: Int,
        var total: Int
)