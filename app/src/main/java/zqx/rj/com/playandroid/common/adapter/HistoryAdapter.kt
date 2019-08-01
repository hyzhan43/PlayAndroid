package zqx.rj.com.playandroid.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * created： 2018/10/28 20:12
 * desc：    TODO
 */
class HistoryAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.history_item, null) {

    override fun convert(helper: BaseViewHolder?, item: String?) {

        helper?.run {
            setText(R.id.mTvName, item ?: "")
            addOnClickListener(R.id.mIvDelete)
        }
    }
}