package zqx.rj.com.playandroid.main.navigation.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhan.ktwing.ext.getColorRef
import kotlinx.android.synthetic.main.navigation_category_item.view.*
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * created： 2018/10/21 19:06
 * desc：    TODO
 */
class CategoryAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.navigation_category_item, null) {

    var selectedPosition: Int = -1

    override fun convert(helper: BaseViewHolder, item: String?) {

        helper.run {
            setText(R.id.mTvName, item ?: "")
            addOnClickListener(R.id.mTvName)

            itemView.run {
                if (adapterPosition == selectedPosition) {
                    mTvName.setTextColor(mContext.getColorRef(R.color.colorPrimaryDark))
                    mTvName.setBackgroundColor(mContext.getColorRef(R.color.lightGray))
                } else {
                    // 默认 不设置颜色
                    mTvName.setTextColor(Color.GRAY)
                    // 默认背景
                    mTvName.setBackgroundColor(Color.WHITE)
                }
            }
        }
    }
}