package zqx.rj.com.playandroid.common.search.data.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * created： 2018/10/28 20:12
 * desc：    TODO
 */
class HistoryAdapter(layoutId: Int, listData: List<String>?)
    : BaseQuickAdapter<String, BaseViewHolder>(layoutId, listData) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.mTvName, item ?: "")
        helper?.addOnClickListener(R.id.mIvDelete)
    }
}