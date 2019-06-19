package zqx.rj.com.playandroid.project.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.ext.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.common.net.ApiRepository
import zqx.rj.com.playandroid.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.project.data.bean.ProjectTreeRsp

/**
 * author：  HyZhan
 * created： 2018/10/27 16:14
 * desc：    TODO
 */
class ProjectRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getProjectTree(liveData: MutableLiveData<BaseResponse<List<ProjectTreeRsp>>>) {
        apiService.getProjectTree()
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun getProjects(page: Int, id: Int, liveData: MutableLiveData<BaseResponse<ProjectRsp>>) {
        apiService.getProjects(page, id)
                .execute(BaseObserver(liveData, loadState, this))
    }
}