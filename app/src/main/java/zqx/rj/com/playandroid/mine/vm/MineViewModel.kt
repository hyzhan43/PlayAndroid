package zqx.rj.com.playandroid.mine.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp
import zqx.rj.com.playandroid.mine.data.repository.MineRepository

/**
 * author：  HyZhan
 * created： 2018/10/23 14:45
 * desc：    TODO
 */
class MineViewModel(application: Application) : BaseViewModel<MineRepository>(application) {

    var mCollectData: MutableLiveData<BaseResponse<CollectRsp>> = MutableLiveData()

    fun getCollectArticle() {
        mRepository.getCollectArticle(mCollectData)
    }
}