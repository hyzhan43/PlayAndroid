package zqx.rj.com.playandroid.project.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.net.ApiRepository
import zqx.rj.com.playandroid.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.project.data.bean.ProjectTreeRsp

/**
 * author：  HyZhan
 * created： 2018/10/27 16:14
 * desc：    TODO
 */
class ProjectRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getProjectTree(liveData: MutableLiveData<BaseResponse<List<ProjectTreeRsp>>>) {
        addSubscribe(apiService.getProjectTree()
                .compose(RxSchedulers.ioToMain())
                .subscribe(
                        object : BaseObserver<BaseResponse<List<ProjectTreeRsp>>>(liveData, loadState) {}))
    }

    fun getProjects(page: Int, id: Int, liveData: MutableLiveData<BaseResponse<ProjectRsp>>) {
        addSubscribe(apiService.getProjects(page, id)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<ProjectRsp>>(liveData, loadState) {}))
    }
}