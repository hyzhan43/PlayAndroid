package zqx.rj.com.playandroid.home.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.repository.HomeRepository

/**
 * author：  HyZhan
 * created： 2018/10/14 19:06
 * desc：    TODO
 */
class HomeViewModel(application: Application) : BaseViewModel<HomeRepository>(application) {

    private val mBannerData: MutableLiveData<BaseResponse<List<BannerRsp>>> by lazy {
        MutableLiveData<BaseResponse<List<BannerRsp>>>()
    }

    fun getBannerData(): MutableLiveData<BaseResponse<List<BannerRsp>>> {
        return mBannerData
    }


    fun getBanner() {
        mRepository.getBanner(mBannerData)
    }
}