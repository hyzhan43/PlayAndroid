package zqx.rj.com.playandroid.home.data.adapter

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.home_article_item.view.*
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
            it.setText(R.id.mTvAuthor, article?.author)
            it.setText(R.id.mTvCategory, "${article?.chapterName}/${article?.superChapterName}")
            it.setText(R.id.mTvTitle, article?.title)
            it.setText(R.id.mTvTime, article?.niceDate)
            it.addOnClickListener(R.id.mIvLove)
        }
    }
}