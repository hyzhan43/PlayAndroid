package zqx.rj.com.playandroid.wechat.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.wechat.data.bean.WxArticleRsp

/**
 * author：  HyZhan
 * created： 2018/11/2 16:29
 * desc：    TODO
 */
class WeChatRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getWeChatName(liveData: MutableLiveData<BaseResponse<List<WeChatNameRsp>>>) {
        apiService.getWeChatName()
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun getWxArticle(id: Int, page: Int, liveData: MutableLiveData<BaseResponse<WxArticleRsp>>) {
        apiService.getWxArticle(id, page)
                .execute(BaseObserver(liveData, loadState, this))
    }
}