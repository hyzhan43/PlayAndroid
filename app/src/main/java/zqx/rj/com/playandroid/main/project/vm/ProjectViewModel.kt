package zqx.rj.com.playandroid.main.project.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.main.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.main.project.data.repository.ProjectRepository

/**
 * author：  HyZhan
 * created： 2018/10/27 16:13
 * desc：    TODO
 */
class ProjectViewModel : BaseViewModel<ProjectRepository>() {

    val projectTreeData = MutableLiveData<List<ProjectTreeRsp>>()
    val projectsData = MutableLiveData<ProjectRsp>()

    fun getProjectTree() {
        launchUI({
            repository.getProjectTree().execute({
                projectTreeData.value = it
            })
        })
    }

    fun getProjects(page: Int, id: Int) {
        launchUI({
            repository.getProjects(page, id).execute({
                projectsData.value = it
            })
        })
    }
}