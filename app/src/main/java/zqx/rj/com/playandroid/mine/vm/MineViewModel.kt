package zqx.rj.com.playandroid.mine.vm

import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp
import zqx.rj.com.playandroid.mine.data.repository.MineRepository

/**
 * author：  HyZhan
 * created： 2018/10/23 14:45
 * desc：    TODO
 */
class MineViewModel : ArticleViewModel<MineRepository>() {

    val collectArticleData = MutableLiveData<CollectRsp>()
    val requestCollectData = MutableLiveData<EmptyRsp>()

    fun getCollectArticle(page: Int) {
        launchUI({
            repository.getCollectArticle(page).execute({
                collectArticleData.value = it
            })
        })
    }

    fun unMyCollect(id: Int, originId: Int) {
        launchUI({
            repository.unCollect(id, originId).execute({
                requestCollectData.value = it
            })
        })
    }
}