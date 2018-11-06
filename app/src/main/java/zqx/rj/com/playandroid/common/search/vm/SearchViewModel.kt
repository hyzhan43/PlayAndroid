package zqx.rj.com.playandroid.common.search.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp
import zqx.rj.com.playandroid.common.search.data.repository.SearchRepository

/**
 * author：  HyZhan
 * created： 2018/11/6 15:27
 * desc：    TODO
 */
class SearchViewModel(application: Application) : ArticleViewModel<SearchRepository>(application) {

    var mHotKeyData: MutableLiveData<BaseResponse<List<HotKeyRsp>>> = MutableLiveData()
    var mSearchResultData: MutableLiveData<BaseResponse<SearchResultRsp>> = MutableLiveData()


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