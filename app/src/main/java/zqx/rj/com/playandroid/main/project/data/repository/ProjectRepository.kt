package zqx.rj.com.playandroid.main.project.data.repository

import zqx.rj.com.playandroid.main.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.other.bean.BaseResponse

/**
 * author：  HyZhan
 * created： 2018/10/27 16:14
 * desc：    TODO
 */
class ProjectRepository {

    suspend fun getProjectTree(): BaseResponse<List<ProjectTreeRsp>> {
        return apiService.getProjectTreeAsync()
    }

    suspend fun getProjects(page: Int, id: Int): BaseResponse<ProjectRsp> {
        return apiService.getProjectsAsync(page, id)
    }
}