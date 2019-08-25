package zqx.rj.com.playandroid.common.article.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.bean.EmptyRsp

/**
 * author：  HyZhan
 * created： 2018/11/2 19:45
 * desc：    TODO
 */
abstract class ArticleViewModel<T : ArticleRepository> : BaseViewModel<T>() {

    val collectData = MutableLiveData<EmptyRsp>()

    fun collect(id: Int) {
        launchUI({
            repository.collect(id).execute({
                collectData.value = it
            })
        })
    }

    // 取消 我的收藏   (文章列表 取消收藏不一样)
    fun unCollect(id: Int) {
        launchUI({
            repository.unCollect(id).execute({
                collectData.value = it
            })
        })
    }
}