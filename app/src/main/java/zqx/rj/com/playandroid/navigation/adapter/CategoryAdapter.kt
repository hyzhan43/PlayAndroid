package zqx.rj.com.playandroid.navigation.adapter

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.navigation_category_item.view.*
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * created： 2018/10/21 19:06
 * desc：    TODO
 */
class CategoryAdapter(layoutId: Int, categories: List<String>?)
    : BaseQuickAdapter<String, BaseViewHolder>(layoutId, categories) {

    var selectedPosition: Int = -1

    override fun convert(helper: BaseViewHolder?, item: String?) {

        helper?.let {
            with(it) {
                setText(R.id.mTvName, item)
                addOnClickListener(R.id.mTvName)

                if (adapterPosition == selectedPosition) {
                    itemView.mTvName.setTextColor(getColor(R.color.colorPrimaryDark))
                    itemView.mTvName.setBackgroundColor(getColor(R.color.lightGray))
                } else {
                    // 默认 不设置颜色
                    itemView.mTvName.setTextColor(Color.GRAY)
                    // 默认背景
                    itemView.mTvName.setBackgroundColor(0)
                }
            }
        }
    }

    private fun getColor(color: Int): Int {
        return ContextCompat.getColor(mContext, color)
    }
}