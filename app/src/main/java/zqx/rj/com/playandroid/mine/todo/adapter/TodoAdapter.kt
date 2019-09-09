package zqx.rj.com.playandroid.mine.todo.adapter

import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.playandroid.other.ext.toHtml
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.mine.todo.data.bean.TodoRsp


/**
 * author：  HyZhan
 * create：  2019/1/2 12:46
 * desc：    TODO
 */
class TodoAdapter : BaseItemDraggableAdapter<TodoRsp, BaseViewHolder>(R.layout.todo_item, null) {

    private val import: Int = 1

    override fun convert(helper: BaseViewHolder, item: TodoRsp?) {

        item?.let {
            helper.run {
                setText(R.id.mTvTitle, it.title?.toHtml() ?: "")
                setText(R.id.mTvContent, it.content?.toHtml() ?: "")
                addOnClickListener(R.id.mTvDelete)
                addOnClickListener(R.id.mTvImportant)
                addOnClickListener(R.id.mContent)
                setText(R.id.mTvType, getType(it.type ?: 0))
                setBackgroundRes(R.id.mTvType, isImportant(it.priority ?: 0))
                setText(R.id.mTvImportant, isShowImportant(it.priority ?: 0))
                setBackgroundRes(R.id.mTvImportant, isShowImportantBg(it.priority ?: 0))
            }
        }
    }

    // 判断是否是重要的todo
    private fun isImportant(priority: Int): Int {
        return if (priority == import) R.drawable.red_round_bg else R.drawable.blue_round_bg
    }

    // 标记为 重要/普通
    private fun isShowImportant(priority: Int): String {
        return if (priority == import) "普通" else "重要"
    }

    private fun isShowImportantBg(priority: Int): Int {
        return if (priority == import) R.color.gray else R.color.orange
    }

    private fun getType(type: Int): String {
        return when (type) {
            Const.ALL -> "杂"
            Const.WORK -> "工"
            Const.STUDY -> "学"
            Const.LIFE -> "生"
            else -> "杂"
        }
    }
}