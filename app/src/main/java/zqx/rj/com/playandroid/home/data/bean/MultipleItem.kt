package zqx.rj.com.playandroid.home.data.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * author：  HyZhan
 * created： 2018/10/18 12:41
 * desc：    TODO
 */
class MultipleItem(
        var author: String,
        var chapterName: String,
        var niceDate: String,
        var title: String,
        var superChapterName: String,
        var type: Int
) : MultiItemEntity {

    companion object {
        val TITLE_TYPE = 1
        val SPECIAL_AREA_TYPE = 2
        val ARTICLE_TYPE = 3
    }

    override fun getItemType(): Int = type
}