package zqx.rj.com.playandroid.home.data.adapter

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.article_item.view.*
import zqx.rj.com.mvvm.common.toHtml
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.home.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/16 10:50
 * desc：    TODO
 */
class HomeArticleAdapter(layoutId: Int, listData: List<Article>?) :
        BaseQuickAdapter<Article, BaseViewHolder>(layoutId, listData) {

    @SuppressLint("SetTextI18n")
    override fun convert(viewHolder: BaseViewHolder?, article: Article?) {
        viewHolder?.let {
            with(it) {
                setText(R.id.mTvAuthor, article?.author)
                setText(R.id.mTvCategory, "${article?.chapterName}/${article?.superChapterName}")
                setText(R.id.mTvTitle, article?.title?.toHtml())
                setText(R.id.mTvTime, article?.niceDate)
                setImageResource(R.id.mIvLove, isCollect(article))
                addOnClickListener(R.id.mIvLove)
            }
        }
    }

    private fun isCollect(article: Article?): Int {
        return article?.let {
            if (it.collect) R.drawable.ic_collected else R.drawable.ic_collect
        } ?: R.drawable.ic_collect
    }
}