package zqx.rj.com.playandroid.common.search

/**
 *  @author: hyzhan
 *  @date:   2019/6/12
 *  @desc:   TODO
 */
class PageHelper<T> {

    private val dataList by lazy { ArrayList<T>() }
    // 当前起始页
    var page = 0
    //总数据
    var total = 0

    fun init() {
        page = 0
        total = 0
    }

    fun setMoreData(newDataList: ArrayList<T>) {
        this.dataList.addAll(newDataList)
    }

    fun hasMore(): Boolean {
        return this.dataList.size >= total
    }
}