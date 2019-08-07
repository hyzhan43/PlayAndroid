package zqx.rj.com.playandroid.mine.todo.data.bean

/**
 * author：  HyZhan
 * create：  2019/1/2 17:36
 * desc：    TODO
 */
data class TodoRsp(
        val completeDate: String,
        val content: String,
        val dateStr: String,
        val id: Int,
        var priority: Int,
        val status: Int,
        val type: Int,
        val title: String
)