package zqx.rj.com.playandroid.todo.data.bean

/**
 * author：  HyZhan
 * create：  2019/1/2 17:36
 * desc：    TODO
 */
data class TodoRsp(
        var completeDate: String,
        var content: String,
        var dateStr: String,
        var id: Int,
        var priority: Int,
        var status: Int,
        var type: Int,
        var title: String
)