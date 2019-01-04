package zqx.rj.com.playandroid.todo.data.adapter

import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.common.toHtml
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp


/**
 * author：  HyZhan
 * create：  2019/1/2 12:46
 * desc：    TODO
 */
class TodoAdapter(layoutId: Int, data: List<TodoRsp>?) :
        BaseItemDraggableAdapter<TodoRsp, BaseViewHolder>(layoutId, data) {

    override fun convert(helper: BaseViewHolder?, item: TodoRsp?) {
        item?.let {
            helper?.setText(R.id.mTvTitle, it.title.toHtml())
                    ?.setText(R.id.mTvContent, it.content.toHtml())
                    ?.addOnClickListener(R.id.right)
                    ?.setText(R.id.mTvType, getType(it.type))
        }
    }

    private fun getType(type: Int): String {
        return when (type) {
            Constant.ALL -> "杂"
            Constant.WORK -> "工"
            Constant.STUDY -> "学"
            Constant.LIFE -> "生"
            else -> "杂"
        }
    }
}