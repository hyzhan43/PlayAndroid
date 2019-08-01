package zqx.rj.com.playandroid.todo.adapter

import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.ext.toHtml
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
                    ?.addOnClickListener(R.id.mTvDelete)
                    ?.addOnClickListener(R.id.mTvImportant)
                    ?.addOnClickListener(R.id.content)
                    ?.setText(R.id.mTvType, getType(it.type))
                    ?.setBackgroundRes(R.id.mTvType, isImportant(it.priority))
                    ?.setText(R.id.mTvImportant, isShowImportant(it.priority))
                    ?.setBackgroundRes(R.id.mTvImportant, isShowImportantBg(it.priority))
        }
    }

    // 判断是否是重要的todo
    private fun isImportant(priority: Int): Int {
        return if (priority == 1) R.drawable.red_round_bg else R.drawable.blue_round_bg
    }

    // 标记为 重要/普通
    private fun isShowImportant(priority: Int): String {
        return if (priority == 1) "普通" else "重要"
    }

    private fun isShowImportantBg(priority: Int): Int {
        return if (priority == 1) R.color.gray else R.color.orange
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