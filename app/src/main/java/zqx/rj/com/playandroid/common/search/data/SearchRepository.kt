package zqx.rj.com.playandroid.common.search.data

import zqx.rj.com.playandroid.common.article.data.repository.ArticleRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp
import zqx.rj.com.playandroid.common.search.data.db.bean.Record

/**
 * author：  HyZhan
 * created： 2018/11/6 15:28
 * desc：    搜索仓库
 */
class SearchRepository : ArticleRepository() {

    suspend fun getHotKey(): BaseResponse<List<HotKeyRsp>> {
        return launchIO { apiService.getHotKeyAsync().await() }
    }

    suspend fun search(page: Int, str: String): BaseResponse<SearchResultRsp> {
        return launchIO { apiService.searchAsync(page, str).await() }
    }

    fun clearRecords(): Int {
        return RecordDao.clearHistory()
    }

    fun deleteOneRecord(name: String): Int {
        return RecordDao.deleteHistoryByName(name)
    }

    fun getRecords(): List<Record> {
        return RecordDao.getRecords()
    }

    fun getRecordByName(name: String): List<Record> {
        return RecordDao.getRecordByName(name)
    }

    fun newRecord(name: String): Boolean {
        return RecordDao.newRecord(name)
    }
}