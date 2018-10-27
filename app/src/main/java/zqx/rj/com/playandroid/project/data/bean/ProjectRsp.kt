package zqx.rj.com.playandroid.project.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/27 18:42
 * desc：    TODO
 */
class ProjectRsp(
        var datas: List<Project>,
        var curPage: Int,
        var size: Int,
        var total: Int,
        var pageCount: Int
)
