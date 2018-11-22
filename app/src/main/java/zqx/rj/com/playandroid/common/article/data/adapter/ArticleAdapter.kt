package zqx.rj.com.playandroid.common.article.data.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.mvvm.common.toHtml
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.common.article.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/22 20:39
 * desc：    TODO
 */
class ArticleAdapter(layoutId: Int, listData: List<Article>?)
    : BaseQuickAdapter<Article, BaseViewHolder>(layoutId, listData) {

    override fun convert(viewHolder: BaseViewHolder?, article: Article?) {
        viewHolder?.let { holder ->
            article?.let {
                holder.setText(R.id.mTvAuthor, it.author)
                holder.setText(R.id.mTvTitle, it.title.toHtml())
                holder.setText(R.id.mTvCategory, category(it))
                holder.setText(R.id.mTvTime, it.niceDate)
                holder.setImageResource(R.id.mIvLove, isCollect(it))
                holder.addOnClickListener(R.id.mIvLove)
            }
        }
    }

    private fun category(article: Article): String {
        article.let {
            return when {
                it.superChapterName.isNullOrEmpty() and it.chapterName.isNullOrEmpty() -> ""
                it.superChapterName.isNullOrEmpty() -> it.chapterName ?: ""
                it.chapterName.isNullOrEmpty() -> it.superChapterName ?: ""
                else -> "${it.superChapterName}/${it.chapterName}"
            }
        }
    }

    private fun isCollect(article: Article): Int = if (article.collect) R.drawable.ic_collected else R.drawable.ic_collect
}