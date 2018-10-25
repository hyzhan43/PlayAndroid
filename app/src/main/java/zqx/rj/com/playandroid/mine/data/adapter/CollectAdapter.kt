package zqx.rj.com.playandroid.mine.data.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.mine.data.bean.Article

/**
 * author：  HyZhan
 * created： 2018/10/24 19:50
 * desc：    TODO
 */
class CollectAdapter(layoutId: Int, listData: List<Article>?)
    : BaseQuickAdapter<Article, BaseViewHolder>(layoutId, listData) {

    override fun convert(helper: BaseViewHolder?, article: Article?) {
        helper?.let {
            it.setText(R.id.mTvAuthor, article?.author)
            it.setText(R.id.mTvCategory, "${article?.chapterName}")
            it.setText(R.id.mTvTitle, article?.title)
            it.setText(R.id.mTvTime, article?.niceDate)
            it.setImageResource(R.id.mIvLove, R.drawable.ic_collected)
            it.addOnClickListener(R.id.mIvLove)
        }
    }
}