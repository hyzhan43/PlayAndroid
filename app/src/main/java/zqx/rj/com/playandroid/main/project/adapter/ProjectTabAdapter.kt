package zqx.rj.com.playandroid.main.project.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.project_item.view.*
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.main.project.data.bean.Project
import zqx.rj.com.playandroid.other.ext.loadUrl

/**
 * author：  HyZhan
 * created： 2018/10/27 16:40
 * desc：    TODO
 */
class ProjectTabAdapter
    : BaseQuickAdapter<Project, BaseViewHolder>(R.layout.project_item, null) {


    override fun convert(helper: BaseViewHolder, item: Project?) {

        helper.run {
            item?.let {
                setText(R.id.mTvTitle, it.title)
                setText(R.id.mTvContent, it.desc)
                setText(R.id.mTvTime, it.niceDate)
                setText(R.id.mTvAuthor, it.author)

                itemView.mIvIcon.loadUrl(mContext, it.envelopePic)
            }
        }
    }
}