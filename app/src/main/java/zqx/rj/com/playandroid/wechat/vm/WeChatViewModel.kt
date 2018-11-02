package zqx.rj.com.playandroid.wechat.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.wechat.data.bean.WxArticleRsp
import zqx.rj.com.playandroid.wechat.data.repository.WeChatRepository

/**
 * author：  HyZhan
 * created： 2018/11/2 16:28
 * desc：    TODO
 */
class WeChatViewModel(application: Application) : ArticleViewModel<WeChatRepository>(application) {

    var mWeChatNameData: MutableLiveData<BaseResponse<List<WeChatNameRsp>>> = MutableLiveData()
    var mWxArticleData: MutableLiveData<BaseResponse<WxArticleRsp>> = MutableLiveData()


    fun getWeChatName() {
        mRepository.getWeChatName(mWeChatNameData)
    }

    fun getWxArticle(id: Int, page: Int) {
        mRepository.getWxArticle(id, page, mWxArticleData)
    }

}