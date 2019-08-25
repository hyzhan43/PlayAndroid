package zqx.rj.com.playandroid.common.search.data.db.bean

import org.litepal.crud.LitePalSupport

/**
 * author：  HyZhan
 * created： 2018/10/28 21:12
 * desc：    TODO
 */
class Record(val id: Long = -1, var name: String = "") : LitePalSupport(){

    companion object{
        const val MAX_RECORDS = 5
    }

}