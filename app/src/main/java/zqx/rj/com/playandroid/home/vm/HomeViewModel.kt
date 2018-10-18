package zqx.rj.com.playandroid.home.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.bean.HomeArticleRsp
import zqx.rj.com.playandroid.home.data.repository.HomeRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:06
 * desc：    TODO
 */
class HomeViewModel(application: Application) : BaseViewModel<HomeRepository>(application) {

    var mBannerData: MutableLiveData<BaseResponse<List<BannerRsp>>> = MutableLiveData()
    var mHomeArticleData: MutableLiveData<BaseResponse<HomeArticleRsp>> = MutableLiveData()

    fun getBanner() {
        mRepository.getBanner(mBannerData)
    }

    fun getArticle(page: Int) {
        mRepository.getArticle(page, mHomeArticleData)
    }
}