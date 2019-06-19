package zqx.rj.com.playandroid.common.search.data

import org.litepal.LitePal
import org.litepal.extension.deleteAll
import org.litepal.extension.find
import zqx.rj.com.playandroid.common.search.data.db.bean.Record

/**
 * author：  HyZhan
 * create：  2019/6/18
 * desc：    TODO
 */
object HistoryDao {

    fun clearHistory() {
        LitePal.deleteAll<Record>()
    }

    fun deleteHistoryByName(name: String) {
        LitePal.deleteAll(Record::class.java, "name = ?", name)
    }

    fun getRecords(): List<Record> {
        return LitePal.select("name").order("id desc").find()
    }
}