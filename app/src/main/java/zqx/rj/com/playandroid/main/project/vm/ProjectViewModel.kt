package zqx.rj.com.playandroid.main.project.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.main.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeData
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.main.project.data.repository.ProjectRepository
import zqx.rj.com.playandroid.main.project.view.fragment.ProjectTabFragment

/**
 * author：  HyZhan
 * created： 2018/10/27 16:13
 * desc：    TODO
 */
class ProjectViewModel : BaseViewModel<ProjectRepository>() {

    val projectTreeLiveData = MutableLiveData<ProjectTreeData>()
    val projectsData = MutableLiveData<ProjectRsp>()

    fun getProjectTree() {
        quickLaunch<List<ProjectTreeRsp>> {
            request { repository.getProjectTree() }

            onSuccess {
                it?.let { updateProjectTreeData(it) }
            }
        }
    }

    private fun updateProjectTreeData(projectTreeRspList: List<ProjectTreeRsp>) {

        val titles = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()

        projectTreeRspList.forEach {
            titles.add(it.name)
            fragments.add(ProjectTabFragment.getNewInstance(it.id))
        }

        projectTreeLiveData.value = ProjectTreeData(titles, fragments)
    }

    fun getProjects(page: Int, id: Int) {
        launchUI({
            repository.getProjects(page, id).execute({ projectRsp ->
                projectRsp?.let { projectsData.value = it }
            })
        })
    }
}