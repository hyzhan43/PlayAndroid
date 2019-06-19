package zqx.rj.com.playandroid.common.search.data

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.ext.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp

/**
 * author：  HyZhan
 * created： 2018/11/6 15:28
 * desc：    搜索仓库
 */
class SearchRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getHotKey(liveData: MutableLiveData<BaseResponse<List<HotKeyRsp>>>) {
        apiService.getHotKey().execute(BaseObserver(liveData, loadState, this))
    }

    fun search(page: Int, str: String, liveData: MutableLiveData<BaseResponse<SearchResultRsp>>) {
        apiService.search(page, str).execute(BaseObserver(liveData, loadState, this))
    }

    fun clearRecords(){
        HistoryDao.clearHistory()
    }

    fun deleteOneRecord(name: String){
        HistoryDao.deleteHistoryByName(name)
    }

    fun getRecords(){
        val records = HistoryDao.getRecords()

    }
}