package zqx.rj.com.playandroid.home.vm

import androidx.lifecycle.MutableLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.home.data.bean.HomeArticleRsp
import zqx.rj.com.playandroid.home.data.repository.HomeRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:06
 * desc：    TODO
 */
class HomeViewModel : ArticleViewModel<HomeRepository>() {

    val bannerData = MutableLiveData<List<BannerRsp>>()
    val homeArticleData = MutableLiveData<HomeArticleRsp>()
    val commonWebData = MutableLiveData<List<CommonWebRsp>>()

    fun getBanner() {
        launchUI({
            repository.getBanner().execute({ bannerData.value = it })
        })
    }

    fun getArticle(page: Int) {
        launchUI({
            repository.getArticle(page).execute({ homeArticleData.value = it })
        })

    }

    fun getCommonWeb() {
        launchUI({
            repository.getCommonWeb().execute({ commonWebData.value = it })
        })
    }
}