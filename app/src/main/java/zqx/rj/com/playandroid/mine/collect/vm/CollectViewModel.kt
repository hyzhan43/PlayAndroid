package zqx.rj.com.playandroid.mine.collect.vm

import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.mine.collect.data.bean.CollectArticleData
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.mine.collect.data.bean.CollectRsp
import zqx.rj.com.playandroid.mine.collect.data.repository.CollectRepository

/**
 * author：  HyZhan
 * created： 2018/10/23 14:45
 * desc：    TODO
 */
class CollectViewModel : ArticleViewModel<CollectRepository>() {

    val collectArticleData = MutableLiveData<CollectArticleData>()
    val requestCollectData = MutableLiveData<EmptyRsp>()

    fun getCollectArticle(page: Int) {
        quickLaunch<CollectRsp> {

            request { repository.getCollectArticle(page) }

            onSuccess { it?.let { updateCollectArticleData(it) } }
        }
    }

    private fun updateCollectArticleData(collectRsp: CollectRsp) {

        // 全部设置为 已收藏 状态
        val collectArticleList = collectRsp.datas
            .onEach { article -> article.collect = true }
            .toList()

        collectArticleData.value = CollectArticleData(collectArticleList)
    }


    fun unMyCollect(id: Int, originId: Int) {
        launchUI({
            repository.unCollect(id, originId).execute({
                requestCollectData.value = it
            })
        })
    }
}