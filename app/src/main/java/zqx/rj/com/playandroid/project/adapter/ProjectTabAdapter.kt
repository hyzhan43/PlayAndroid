package zqx.rj.com.playandroid.project.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.mvvm.ext.loadUrl
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.project.data.bean.Project

/**
 * author：  HyZhan
 * created： 2018/10/27 16:40
 * desc：    TODO
 */
class ProjectTabAdapter(layoutId: Int, listData: List<Project>?)
    : BaseQuickAdapter<Project, BaseViewHolder>(layoutId, listData) {


    override fun convert(helper: BaseViewHolder?, item: Project?) {

        helper?.let {
            with(it) {
                item?.let {
                    setText(R.id.mTvTitle, it.title)
                    setText(R.id.mTvContent, it.desc)
                    setText(R.id.mTvTime, it.niceDate)
                    setText(R.id.mTvAuthor, it.author)

                    (getView(R.id.mIvIcon) as ImageView).loadUrl(mContext, it.envelopePic)
                }
            }
        }
    }
}