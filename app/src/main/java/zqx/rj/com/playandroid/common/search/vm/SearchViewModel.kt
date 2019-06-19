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
import zqx.rj.com.playandroid.common.search.data.SearchRepository
import zqx.rj.com.playandroid.common.search.data.db.bean.Record

/**
 * author：  HyZhan
 * created： 2018/11/6 15:27
 * desc：    TODO
 */
class SearchViewModel(application: Application) : ArticleViewModel<SearchRepository>(application) {

    val mHotKeyData = MutableLiveData<BaseResponse<List<HotKeyRsp>>>()
    val mSearchResultData = MutableLiveData<BaseResponse<SearchResultRsp>>()
    val deleteRecord = MutableLiveData<Int>()
    val records = MutableLiveData<List<Record>>()
    val newRecord = MutableLiveData<Boolean>()
    val clearRecord = MutableLiveData<Int>()

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

    fun clearRecords() {
        clearRecord.value = mRepository.clearRecords()
    }

    fun deleteOneRecord(name: String) {
        // 正常返回
        deleteRecord.value = mRepository.deleteOneRecord(name)
    }

    fun getRecords() {
        records.value = mRepository.getRecords()
    }

    fun addRecord(keyword: String) {

        /**
         *  1、如果查询结果有相同关键词, 则获取List 第0个, 并 delete()
         *  2、若没有相同记录, 就先判断是否达到 5条记录, 达到则返回最后最后一条记录并 delete()
         *  3、没有达到5条记录, 就返回空的记录 Record() -> delete()
         */
        val records = mRepository.getRecords()

        records.filter { record ->
            return@filter record.name == keyword
        }.getOrElse(0) {
            return@getOrElse if (records.size >= Record.MAX_RECORDS) records[4] else Record()
        }.delete()

        newRecord.value = mRepository.newRecord(keyword)
    }
}