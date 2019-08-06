package zqx.rj.com.playandroid.project.data.repository

import com.zhan.mvvm.mvvm.BaseRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.project.data.bean.ProjectTreeRsp

/**
 * author：  HyZhan
 * created： 2018/10/27 16:14
 * desc：    TODO
 */
class ProjectRepository : BaseRepository() {

    suspend fun getProjectTree(): BaseResponse<List<ProjectTreeRsp>> {
        return launchIO { apiService.getProjectTreeAsync().await() }
    }

    suspend fun getProjects(page: Int, id: Int): BaseResponse<ProjectRsp> {
        return launchIO { apiService.getProjectsAsync(page, id).await() }
    }
}