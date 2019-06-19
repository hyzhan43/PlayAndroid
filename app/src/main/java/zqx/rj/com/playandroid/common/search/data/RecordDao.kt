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
object RecordDao {

    fun clearHistory(): Int {
        return LitePal.deleteAll<Record>()
    }

    fun deleteHistoryByName(name: String): Int {
        return LitePal.deleteAll(Record::class.java, "name = ?", name)
    }

    fun getRecords(): List<Record> {
        return LitePal.select("name").order("id desc").find()
    }

    fun getRecordByName(name: String): List<Record> {
        return LitePal.where("name = ?", name).find()
    }

    fun newRecord(name: String): Boolean {
        // 添加新纪录
        return Record().also { it.name = name }.save()
    }
}