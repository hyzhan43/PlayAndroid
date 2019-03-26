package zqx.rj.com.playandroid.common.adapter

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
                        .setText(R.id.mTvTitle, it.title.toHtml())
                        .setText(R.id.mTvCategory, category(it))
                        .setText(R.id.mTvTime, it.niceDate)
                        .setImageResource(R.id.mIvLove, isCollect(it))
                        .addOnClickListener(R.id.mIvLove)
                        .setVisible(R.id.mTvNews, isNews(it.niceDate))
            }
        }
    }

    // 判断是否是最新文章
    private fun isNews(niceDate: String): Boolean {
        return niceDate.contains("分钟") or niceDate.contains("刚刚") or niceDate.contains("小时")
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