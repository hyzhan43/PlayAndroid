package zqx.rj.com.playandroid.todo.data.bean

/**
 * author：  HyZhan
 * create：  2019/1/2 17:37
 * desc：    TODO
 */
data class PageRsp<T>(
        var curPage: Int,
        var datas: List<T>,
        var offset: Int,
        var over: Boolean,
        var pageCount: Int,
        var size: Int,
        var total: Int
)