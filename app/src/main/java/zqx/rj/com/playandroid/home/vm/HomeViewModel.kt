package zqx.rj.com.playandroid.home.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.bean.HomeArticleRsp
import zqx.rj.com.playandroid.home.data.bean.HomeHotKeyRsp
import zqx.rj.com.playandroid.home.data.bean.HomeSearchRsp
import zqx.rj.com.playandroid.home.data.repository.HomeRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:06
 * desc：    TODO
 */
class HomeViewModel(application: Application) : BaseViewModel<HomeRepository>(application) {

    var mBannerData: MutableLiveData<BaseResponse<List<BannerRsp>>> = MutableLiveData()
    var mHomeArticleData: MutableLiveData<BaseResponse<HomeArticleRsp>> = MutableLiveData()
    var mHotKeyData: MutableLiveData<BaseResponse<List<HomeHotKeyRsp>>> = MutableLiveData()
    var mSearchResultData: MutableLiveData<BaseResponse<HomeSearchRsp>> = MutableLiveData()

    fun getBanner() {
        mRepository.getBanner(mBannerData)
    }

    fun getArticle(page: Int) {
        mRepository.getArticle(page, mHomeArticleData)
    }

    fun getHotKey() {
        mRepository.getHotKey(mHotKeyData)
    }

    fun search(page: Int, str: String) {
        if (page >= 0 && str.isNotEmpty()) {
            mRepository.search(page, str, mSearchResultData)
        } else {
            loadState.postValue(State(StateType.TIPS, tip = R.string.input_tips))
        }
    }
}