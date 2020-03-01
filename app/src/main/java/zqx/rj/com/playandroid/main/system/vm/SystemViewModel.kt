package zqx.rj.com.playandroid.main.system.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.main.system.data.bean.TopTreeData
import zqx.rj.com.playandroid.main.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.main.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.main.system.data.repository.SystemRepository
import zqx.rj.com.playandroid.main.system.view.fragment.SysArticleFragment

/**
 * author：  HyZhan
 * created： 2018/10/22 19:33
 * desc：    TODO
 */
class SystemViewModel : ArticleViewModel<SystemRepository>() {

    val topTreeLiveData = MutableLiveData<TopTreeData>()
    val treeArticleData = MutableLiveData<TreeArticleRsp>()

    fun getTree() {
        quickLaunch<List<TopTreeRsp>> {

            request { repository.getTree() }

            onSuccess { topTreeRspList ->
                topTreeRspList?.let { updateTopTreeData(it) }
            }
        }
    }

    private fun updateTopTreeData(topTreeRspList: List<TopTreeRsp>) {

        val titles = topTreeRspList.map { it.name }.toList()

        val fragments = initFragment(topTreeRspList)

        topTreeLiveData.value = TopTreeData(titles, fragments)
    }

    private fun initFragment(topTreeList: List<TopTreeRsp>): List<Fragment> {

        return topTreeList.map { topTreeRsp ->
            val ids = arrayListOf<Int>()
            val secondTreeTitles = arrayListOf<String>()

            // 二级菜单
            topTreeRsp.children.forEach {
                ids.add(it.id)
                secondTreeTitles.add(it.name)
            }

            SysArticleFragment.newInstance(ids, secondTreeTitles)
        }.toList()
    }

    fun getArticle(cid: Int, page: Int) = launchUI({
        repository.getArticle(page, cid).execute({ treeArticleRsp ->
            treeArticleRsp?.let { treeArticleData.value = it }
        })
    })
}