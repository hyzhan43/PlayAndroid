package zqx.rj.com.playandroid.main.project.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/27 18:42
 * desc：    TODO
 */
class ProjectRsp(
        val datas: List<Project>,
        val curPage: Int,
        val size: Int,
        val total: Int,
        val pageCount: Int
)
