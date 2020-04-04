package zqx.rj.com.playandroid.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.livedata.CommonLiveData
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.main.home.data.bean.*
import zqx.rj.com.playandroid.main.home.data.repository.HomeRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:06
 * desc：    TODO
 */
class HomeViewModel : ArticleViewModel<HomeRepository>() {

    val bannerLiveData = MutableLiveData<BannerData>()
    val homeArticleData = CommonLiveData<HomeArticleRsp>()

    val commonWebLiveData = MutableLiveData<CommonWebData>()


    fun getBanner() {
        quickLaunch<List<BannerRsp>> {
            request { repository.getBanner() }

            onSuccess { bannerRspList ->
                bannerRspList?.let { updateBannerData(it) }
            }
        }
    }

    private fun updateBannerData(bannerList: List<BannerRsp>) {

        val images = arrayListOf<String>()
        val titles = arrayListOf<String>()
        val urls = arrayListOf<String>()

        for (bannerItem in bannerList) {
            images.add(bannerItem.imagePath)
            titles.add(bannerItem.title)
            urls.add(bannerItem.url)
        }

        bannerLiveData.value = BannerData(images, titles, urls)
    }

    fun getArticle(page: Int) {
        superLaunchRequest(homeArticleData) { repository.getArticle(page) }
    }

    fun getCommonWeb() {
        quickLaunch<List<CommonWebRsp>> {

            request { repository.getCommonWeb() }

            onSuccess { it?.let { updateCommonData(it) } }
        }
    }

    private fun updateCommonData(commonWebRspList: List<CommonWebRsp>) {
        val tags = arrayListOf<String>()
        val links = arrayListOf<String>()


        for (commonWebRsp in commonWebRspList) {
            tags.add(commonWebRsp.name)
            links.add(commonWebRsp.link)
        }

        commonWebLiveData.value = CommonWebData(tags, links)
    }
}