package zqx.rj.com.playandroid.main.wechat.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.main.wechat.data.bean.WeChatData
import zqx.rj.com.playandroid.main.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.main.wechat.data.bean.WxArticleRsp
import zqx.rj.com.playandroid.main.wechat.data.repository.WeChatRepository
import zqx.rj.com.playandroid.main.wechat.view.fragment.WxArticleFragment

/**
 * author：  HyZhan
 * created： 2018/11/2 16:28
 * desc：    TODO
 */
class WeChatViewModel : ArticleViewModel<WeChatRepository>() {

    var weChatLiveData = MutableLiveData<WeChatData>()
    var wxArticleData = MutableLiveData<WxArticleRsp>()

    fun getWeChatName() {
        quickLaunch<List<WeChatNameRsp>> {

            request { repository.getWeChatArticle() }

            onSuccess { weChatNameList ->
                weChatNameList?.let { updateWeChatData(it) }
            }
        }
    }

    private fun updateWeChatData(dataList: List<WeChatNameRsp>) {

        val titles = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()

        dataList.forEach {
            titles.add(it.name)
            fragments.add(WxArticleFragment.newInstance(it.id))
        }

        weChatLiveData.value = WeChatData(titles, fragments)
    }


    fun getWxArticle(id: Int, page: Int) {
        launchUI({
            repository.getWxArticle(id, page).execute({ wxArticleRsp ->
                wxArticleRsp?.let { wxArticleData.value = it }
            })
        })
    }

}