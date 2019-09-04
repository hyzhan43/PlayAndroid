package zqx.rj.com.playandroid.main.system.vm

import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.main.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.main.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.main.system.data.repository.SystemRepository

/**
 * author：  HyZhan
 * created： 2018/10/22 19:33
 * desc：    TODO
 */
class SystemViewModel : ArticleViewModel<SystemRepository>() {

    val topTreeData = MutableLiveData<List<TopTreeRsp>>()
    val treeArticleData = MutableLiveData<TreeArticleRsp>()

    fun getTree() = launchUI({
        repository.getTree().execute({ topTreeRspList ->
            topTreeRspList?.let { topTreeData.value = it }
        })
    })

    fun getArticle(cid: Int, page: Int) = launchUI({
        repository.getArticle(page, cid).execute({ treeArticleRsp ->
            treeArticleRsp?.let { treeArticleData.value = it }
        })
    })
}