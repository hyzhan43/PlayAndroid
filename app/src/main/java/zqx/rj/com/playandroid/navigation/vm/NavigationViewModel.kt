package zqx.rj.com.playandroid.navigation.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseApplication
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp
import zqx.rj.com.playandroid.navigation.data.repository.NavigationRepository

/**
 * author：  HyZhan
 * created： 2018/10/21 18:51
 * desc：    TODO
 */
class NavigationViewModel(application: Application) : BaseViewModel<NavigationRepository>(application) {

    var mCategory: MutableLiveData<BaseResponse<List<NaviCategoryRsp>>> = MutableLiveData()

    fun getCategory() {
        mRepository.getCategory(mCategory)
    }

}