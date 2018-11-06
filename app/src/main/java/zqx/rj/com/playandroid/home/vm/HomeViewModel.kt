package zqx.rj.com.playandroid.home.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp
import zqx.rj.com.playandroid.home.data.bean.*
import zqx.rj.com.playandroid.home.data.repository.HomeRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:06
 * desc：    TODO
 */
class HomeViewModel(application: Application) : ArticleViewModel<HomeRepository>(application) {

    var mBannerData: MutableLiveData<BaseResponse<List<BannerRsp>>> = MutableLiveData()
    var mHomeArticleData: MutableLiveData<BaseResponse<HomeArticleRsp>> = MutableLiveData()
    var mCommonWebData: MutableLiveData<BaseResponse<List<CommonWebRsp>>> = MutableLiveData()

    fun getBanner() {
        mRepository.getBanner(mBannerData)
    }

    fun getArticle(page: Int) {
        mRepository.getArticle(page, mHomeArticleData)
    }

    fun getCommonWeb() {
        mRepository.getCommonWeb(mCommonWebData)
    }
}