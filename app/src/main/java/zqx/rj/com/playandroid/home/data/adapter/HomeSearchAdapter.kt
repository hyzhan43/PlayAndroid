package zqx.rj.com.playandroid.home.data.adapter

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.Html.fromHtml
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.mvvm.common.Util
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.data.bean.SearchResult

/**
 * author：  HyZhan
 * created： 2018/10/19 20:23
 * desc：    TODO
 */
class HomeSearchAdapter(layoutId: Int, list: List<SearchResult>?)
    : BaseQuickAdapter<SearchResult, BaseViewHolder>(layoutId, list) {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun convert(holder: BaseViewHolder?, article: SearchResult?) {
        holder?.let {
            it.setText(R.id.mTvAuthor, article?.author)
            it.setText(R.id.mTvCategory, "${article?.chapterName}/${article?.superChapterName}")
            it.setText(R.id.mTvTitle, Util.strToHtml(article?.title))
            it.setText(R.id.mTvTime, article?.niceDate)
            it.addOnClickListener(R.id.mIvLove)
        }
    }
}