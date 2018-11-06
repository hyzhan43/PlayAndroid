package zqx.rj.com.playandroid.mine.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp
import zqx.rj.com.playandroid.mine.data.repository.MineRepository

/**
 * author：  HyZhan
 * created： 2018/10/23 14:45
 * desc：    TODO
 */
class MineViewModel(application: Application) : ArticleViewModel<MineRepository>(application) {

    var mCollectArticleData: MutableLiveData<BaseResponse<CollectRsp>> = MutableLiveData()
    var mRequestCollectData: MutableLiveData<BaseResponse<EmptyRsp>> = MutableLiveData()

    fun getCollectArticle() {
        mRepository.getCollectArticle(mCollectArticleData)
    }

    fun unMyCollect(id: Int, originId: Int){
        mRepository.unCollect(id, originId, mRequestCollectData)
    }
}