package zqx.rj.com.playandroid.wechat.data.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.wechat.data.bean.WxArticle

/**
 * author：  HyZhan
 * created： 2018/10/22 20:39
 * desc：    TODO
 */
class ArticleAdapter(layoutId: Int, listData: List<WxArticle>?)
    : BaseQuickAdapter<WxArticle, BaseViewHolder>(layoutId, listData) {

    override fun convert(viewHolder: BaseViewHolder?, article: WxArticle?) {
        viewHolder?.let {
            it.setText(R.id.mTvAuthor, article?.author)
            it.setText(R.id.mTvTitle, article?.title)
            it.setText(R.id.mTvCategory, "${article?.superChapterName}/${article?.chapterName}")
            it.setText(R.id.mTvTime, article?.niceDate)
            it.addOnClickListener(R.id.mIvLove)
        }
    }
}