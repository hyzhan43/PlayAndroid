package zqx.rj.com.playandroid.todo.data.bean

/**
 * author：  HyZhan
 * create：  2019/1/2 17:37
 * desc：    TODO
 */
data class PageRsp<T>(
        val curPage: Int,
        val datas: List<T>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)