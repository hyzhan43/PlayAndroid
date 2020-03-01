package zqx.rj.com.playandroid.main.navigation.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.main.navigation.data.bean.ArticleData
import zqx.rj.com.playandroid.main.navigation.data.bean.NavigationData
import zqx.rj.com.playandroid.main.navigation.data.bean.NavigationRsp
import zqx.rj.com.playandroid.main.navigation.data.repository.NavigationRepository

/**
 * author：  HyZhan
 * created： 2018/10/21 18:51
 * desc：    TODO
 */
class NavigationViewModel : BaseViewModel<NavigationRepository>() {

    val categoryLiveData = MutableLiveData<NavigationData>()

    fun getCategory() {
        quickLaunch<List<NavigationRsp>> {
            request { repository.getNavigation() }

            onSuccess { it?.let { updateCategoryData(it) } }
        }
    }

    private fun updateCategoryData(navigationRspList: List<NavigationRsp>) {

        val categories = arrayListOf<String>()

        val articleList = arrayListOf<ArticleData>()

        for (navigationCategoryRsp in navigationRspList) {
            categories.add(navigationCategoryRsp.name)


            val titles = arrayListOf<String>()
            val links = arrayListOf<String>()

            for (article in navigationCategoryRsp.articles) {
                titles.add(article.title)
                links.add(article.link)
            }

            articleList.add(ArticleData(titles, links))
        }

        categoryLiveData.value = NavigationData(categories, articleList)
    }
}