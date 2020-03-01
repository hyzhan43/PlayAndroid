package zqx.rj.com.playandroid.common.search.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.bean.SharedData
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.common.search.data.SearchRepository
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyData
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp
import zqx.rj.com.playandroid.common.search.data.db.bean.Record

/**
 * author：  HyZhan
 * created： 2018/11/6 15:27
 * desc：    TODO
 */
class SearchViewModel : ArticleViewModel<SearchRepository>() {

    val hotKeyLiveData = MutableLiveData<HotKeyData>()
    val searchResultData = MutableLiveData<SearchResultRsp>()
    val deleteRecord = MutableLiveData<Int>()
    val records = MutableLiveData<List<Record>>()
    val newRecord = MutableLiveData<Boolean>()
    val clearRecord = MutableLiveData<Int>()

    fun getHotKey() {
        quickLaunch<List<HotKeyRsp>> {

            request { repository.getHotKey() }

            onSuccess { hotKeyList ->
                hotKeyList?.let { updateHotKeyData(it) }
            }
        }
    }

    private fun updateHotKeyData(hotKeyRspList: List<HotKeyRsp>) {
        val tags = hotKeyRspList.map { it.name }.toList()

        hotKeyLiveData.value = HotKeyData(tags)
    }

    fun search(page: Int, str: String) {
        if (page < 0 || str.isEmpty()) {
            showToast(R.string.input_tips)
            return
        }

        launchUI({
            repository.search(page, str).execute({ searchResultRsp ->
                searchResultRsp?.let { searchResultData.value = it }
            })
        })
    }

    fun clearRecords() {
        clearRecord.value = repository.clearRecords()
    }

    fun deleteOneRecord(name: String) {
        // 正常返回
        deleteRecord.value = repository.deleteOneRecord(name)
    }

    fun getRecords() {
        records.value = repository.getRecords()
    }

    fun addRecord(keyword: String) {

        /**
         *  1、如果查询结果有相同关键词, 则获取List 第0个, 并 delete()
         *  2、若没有相同记录, 就先判断是否达到 5条记录, 达到则返回最后最后一条记录并 delete()
         *  3、没有达到5条记录, 就返回空的记录 Record() -> delete()
         */
        val records = repository.getRecords()

        records.filter { record ->
            return@filter record.name == keyword
        }.getOrElse(0) {
            return@getOrElse if (records.size >= Record.MAX_RECORDS) records[4] else Record()
        }.delete()

        newRecord.value = repository.newRecord(keyword)
    }
}