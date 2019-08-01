package zqx.rj.com.playandroid.project.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.project.data.repository.ProjectRepository

/**
 * author：  HyZhan
 * created： 2018/10/27 16:13
 * desc：    TODO
 */
class ProjectViewModel(application: Application) : BaseViewModel<ProjectRepository>(application) {

    val mProjectTreeData: MutableLiveData<BaseResponse<List<ProjectTreeRsp>>> = MutableLiveData()
    val mProjectsData: MutableLiveData<BaseResponse<ProjectRsp>> = MutableLiveData()

    fun getProjectTree() {
        mRepository.getProjectTree(mProjectTreeData)
    }

    fun getProjects(page: Int, id: Int) {
        mRepository.getProjects(page, id, mProjectsData)
    }
}