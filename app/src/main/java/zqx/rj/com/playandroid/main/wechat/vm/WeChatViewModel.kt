package zqx.rj.com.playandroid.main.wechat.vm

import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.main.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.main.wechat.data.bean.WxArticleRsp
import zqx.rj.com.playandroid.main.wechat.data.repository.WeChatRepository

/**
 * author：  HyZhan
 * created： 2018/11/2 16:28
 * desc：    TODO
 */
class WeChatViewModel : ArticleViewModel<WeChatRepository>() {

    var weChatNameData = MutableLiveData<List<WeChatNameRsp>>()
    var wxArticleData = MutableLiveData<WxArticleRsp>()


    fun getWeChatName() {
        launchUI({
            repository.getWeChatName().execute({
                weChatNameData.value = it
            })
        })
    }

    fun getWxArticle(id: Int, page: Int) {
        launchUI({
            repository.getWxArticle(id, page).execute({
                wxArticleData.value = it
            })
        })
    }

}